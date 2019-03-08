package chnp.manager.generator.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.generator.model.domain.GnrDatatype;
import chnp.manager.generator.model.query.GnrDatatypeQuery;
import chnp.manager.generator.service.GnrDatatypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/gnrdatatype")
public class GnrDatatypeController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GnrDatatypeService gnrDatatypeService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"gnrdatatype"})
	@RequestMapping("/rel")
	public String index(Model model, @RequestParam(name = "projectId") Integer projectId) {
		GnrDatatypeQuery query = new GnrDatatypeQuery();
		query.setProjectId(projectId);
		model.addAttribute("gnrDatatypeQuery", query);
		return "gnrdatatype/index";
	}

	@RequiresPermissions(value = "gnrdatatype_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(GnrDatatypeQuery gnrDatatypeQuery) {
		return gnrDatatypeService.findPage(gnrDatatypeQuery);
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrdatatype_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		GnrDatatype gnrDatatype = gnrDatatypeService.view(id);
		if (null == gnrDatatype) gnrDatatype = new GnrDatatype();
		model.addAttribute("gnrDatatype", gnrDatatype);
		return "gnrdatatype/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param gnrDatatype 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrdatatype_save"})
	@RequestMapping("/new")
	public String _new(Model model, GnrDatatype gnrDatatype) {
		model.addAttribute("gnrDatatype", gnrDatatype);
		return "gnrdatatype/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param gnrDatatype 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrdatatype_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(GnrDatatype gnrDatatype) {
		ResponseJson responseJson = new ResponseJson();

		if (gnrDatatypeService.save(gnrDatatype) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>编辑界面</p>
	 *
	 * @param model
	 * @param id 数据主键
	 * @return 编辑界面
	 */
	@RequiresPermissions(value = {"gnrdatatype_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		GnrDatatype gnrDatatype = gnrDatatypeService.view(id);
		if (null == gnrDatatype) gnrDatatype = new GnrDatatype();
		model.addAttribute("gnrDatatype", gnrDatatype);
		return "gnrdatatype/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param gnrDatatype 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrdatatype_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(GnrDatatype gnrDatatype) {
		ResponseJson responseJson = new ResponseJson();

		if (gnrDatatype.getId() == null) responseJson.error("未指定更新对象！");
		else if (gnrDatatypeService.update(gnrDatatype) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrdatatype_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				gnrDatatypeService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			GnrDatatype gnrDatatype = gnrDatatypeService.getById(Integer.valueOf(sId));
			if (null != gnrDatatype) info.append(",").append(gnrDatatype.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}