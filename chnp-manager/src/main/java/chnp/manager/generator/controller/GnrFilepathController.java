package chnp.manager.generator.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.generator.model.domain.GnrFilepath;
import chnp.manager.generator.model.query.GnrFilepathQuery;
import chnp.manager.generator.service.GnrFilepathService;
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
@RequestMapping("/gnrfilepath")
public class GnrFilepathController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GnrFilepathService gnrFilepathService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"gnrfilepath"})
	@RequestMapping
	public String index() {
		return "gnrfilepath/index";
	}

	@RequiresPermissions(value = "gnrfilepath_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(GnrFilepathQuery gnrFilepathQuery) {
		return gnrFilepathService.findPage(gnrFilepathQuery);
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrfilepath_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		GnrFilepath gnrFilepath = gnrFilepathService.getById(id);
		if (null == gnrFilepath) gnrFilepath = new GnrFilepath();
		model.addAttribute("gnrFilepath", gnrFilepath);
		return "gnrfilepath/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param gnrFilepath 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrfilepath_save"})
	@RequestMapping("/new")
	public String _new(Model model, GnrFilepath gnrFilepath) {
		model.addAttribute("gnrFilepath", gnrFilepath);
		return "gnrfilepath/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param gnrFilepath 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrfilepath_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(GnrFilepath gnrFilepath) {
		ResponseJson responseJson = new ResponseJson();

		if (gnrFilepathService.save(gnrFilepath) > 0) {
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
	@RequiresPermissions(value = {"gnrfilepath_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		GnrFilepath gnrFilepath = gnrFilepathService.getById(id);
		if (null == gnrFilepath) gnrFilepath = new GnrFilepath();
		model.addAttribute("gnrFilepath", gnrFilepath);
		return "gnrfilepath/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param gnrFilepath 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrfilepath_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(GnrFilepath gnrFilepath) {
		ResponseJson responseJson = new ResponseJson();

		if (gnrFilepath.getId() == null) responseJson.error("未指定更新对象！");
		else if (gnrFilepathService.update(gnrFilepath) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrfilepath_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				gnrFilepathService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			GnrFilepath gnrFilepath = gnrFilepathService.getById(Integer.valueOf(sId));
			if (null != gnrFilepath) info.append(",").append(gnrFilepath.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}