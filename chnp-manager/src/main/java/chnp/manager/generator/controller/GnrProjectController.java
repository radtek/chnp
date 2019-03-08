package chnp.manager.generator.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.generator.model.domain.GnrProject;
import chnp.manager.generator.model.query.GnrProjectQuery;
import chnp.manager.generator.service.GnrProjectService;
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
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/gnrproject")
public class GnrProjectController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GnrProjectService gnrProjectService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"gnrproject"})
	@RequestMapping
	public String index() {
		return "gnrproject/index";
	}

	@RequiresPermissions(value = "gnrproject_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(GnrProjectQuery gnrProjectQuery) {
		return gnrProjectService.findPage(gnrProjectQuery);
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrproject_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		GnrProject gnrProject = gnrProjectService.getById(id);
		if (null == gnrProject) gnrProject = new GnrProject();
		model.addAttribute("gnrProject", gnrProject);
		return "gnrproject/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param gnrProject 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrproject_save"})
	@RequestMapping("/new")
	public String _new(Model model, GnrProject gnrProject) {
		model.addAttribute("gnrProject", gnrProject);
		return "gnrproject/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param gnrProject 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrproject_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(GnrProject gnrProject) {
		ResponseJson responseJson = new ResponseJson();

		gnrProject.setCreateUser(1);
		if (gnrProjectService.save(gnrProject) > 0) {
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
	@RequiresPermissions(value = {"gnrproject_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		GnrProject gnrProject = gnrProjectService.getById(id);
		if (null == gnrProject) gnrProject = new GnrProject();
		model.addAttribute("gnrProject", gnrProject);
		return "gnrproject/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param gnrProject 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrproject_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(GnrProject gnrProject) {
		ResponseJson responseJson = new ResponseJson();

		gnrProject.setCreateUser(1);
		gnrProject.setCreateTime(null);
		if (gnrProject.getId() == null) responseJson.error("未指定更新对象！");
		else if (gnrProjectService.update(gnrProject) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrproject_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				gnrProjectService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			GnrProject gnrProject = gnrProjectService.getById(Integer.valueOf(sId));
			if (null != gnrProject) info.append(",").append(gnrProject.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}