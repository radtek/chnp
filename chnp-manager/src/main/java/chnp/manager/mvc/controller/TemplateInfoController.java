package chnp.manager.mvc.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TemplateInfo;
import chnp.manager.mvc.model.query.TemplateInfoQuery;
import chnp.manager.mvc.service.TemplateInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/templateinfo")
public class TemplateInfoController {
	private final Logger log = LoggerFactory.getLogger(TemplateInfoController.class);

	@Autowired
	private TemplateInfoService templateInfoService;

	@RequiresPermissions(value = {"templateinfo"})
	@RequestMapping
	public String index(Model model) {
		return "templateinfo/index";
	}

	@RequiresPermissions(value = {"templateinfo_query"})
	@ResponseBody
	@RequestMapping("/page")
	public DataTables page(TemplateInfoQuery templateInfoQuery) {
		return templateInfoService.findPage(templateInfoQuery);
	}


	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"templateinfo_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TemplateInfo templateInfo = templateInfoService.getById(id);
		if (null == templateInfo) templateInfo = new TemplateInfo();
		model.addAttribute("templateInfo", templateInfo);
		return "templateinfo/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param templateInfo 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"templateinfo_save"})
	@RequestMapping("/new")
	public String _new(Model model, TemplateInfo templateInfo) {
		// TODO: 查询可用的模板引擎并在前端以列表的形式显示
		model.addAttribute("templateInfo", templateInfo);
		return "templateinfo/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param templateInfo 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"templateinfo_save"})
	@ResponseBody
	@RequestMapping("/save")
	public String save(TemplateInfo templateInfo) {
		ResponseJson responseJson = new ResponseJson();

		templateInfo.setUpdateTime(new Date());
		if (templateInfoService.save(templateInfo) > 0) {
			responseJson.success("保存成功！");
		}else responseJson.error("保存失败！");

		return responseJson.toJSONString();
	}

	/**<p>编辑界面</p>
	 *
	 * @param model
	 * @param id 数据主键
	 * @return 编辑界面
	 */
	@RequiresPermissions(value = {"templateinfo_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id")Integer id) {
		// TODO: 查询可用的模板引擎并在前端以列表的形式显示
		TemplateInfo templateInfo = templateInfoService.getById(id);
		if (null == templateInfo) templateInfo = new TemplateInfo();
		model.addAttribute("templateInfo", templateInfo);
		return "templateinfo/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param templateInfo 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"templateinfo_update"})
	@ResponseBody
	@RequestMapping("/update")
	public String update(TemplateInfo templateInfo) {
		ResponseJson responseJson = new ResponseJson();

		templateInfo.setUpdateTime(new Date());
		if (templateInfo.getId() == null) responseJson.error("未指定更新对象！");
		else if (templateInfoService.update(templateInfo) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson.toJSONString();
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"templateinfo_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public String del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				templateInfoService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TemplateInfo templateInfo = templateInfoService.getById(Integer.valueOf(sId));
			if (null != templateInfo) info.append(",").append(templateInfo.getName());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson.toJSONString();
	}

}