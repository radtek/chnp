package chnp.manager.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import chnp.manager.common.entity.DataTables;
import chnp.manager.common.service.UtilService;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsRoleModule;
import chnp.manager.mvc.model.query.TsRoleModuleQuery;
import chnp.manager.mvc.service.TsRoleModuleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/tsrolemodule")
public class TsRoleModuleController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UtilService utilService;
	@Autowired
	private TsRoleModuleService tsRoleModuleService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsrolemodule"})
	@RequestMapping
	public String index() {
		return "tsrolemodule/index";
	}

	@RequiresPermissions(value = "tsrolemodule_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsRoleModuleQuery tsRoleModuleQuery) {
		DataTables dataTables = tsRoleModuleService.findPage(tsRoleModuleQuery);
		utilService.interpret(dataTables, TsRoleModule.class);
		return dataTables;
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsrolemodule_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsRoleModule tsRoleModule = tsRoleModuleService.getById(id);
		if (null == tsRoleModule) tsRoleModule = new TsRoleModule();
		model.addAttribute("tsRoleModule", tsRoleModule);
		return "tsrolemodule/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsRoleModule 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsrolemodule_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsRoleModule tsRoleModule) {
		model.addAttribute("tsRoleModule", tsRoleModule);
		return "tsrolemodule/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsRoleModule 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsrolemodule_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsRoleModule tsRoleModule) {
		ResponseJson responseJson = new ResponseJson();

		if (tsRoleModuleService.save(tsRoleModule) > 0) {
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
	@RequiresPermissions(value = {"tsrolemodule_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		TsRoleModule tsRoleModule = tsRoleModuleService.getById(id);
		if (null == tsRoleModule) tsRoleModule = new TsRoleModule();
		model.addAttribute("tsRoleModule", tsRoleModule);
		return "tsrolemodule/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsRoleModule 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsrolemodule_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsRoleModule tsRoleModule) {
		ResponseJson responseJson = new ResponseJson();

		if (tsRoleModule.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsRoleModuleService.update(tsRoleModule) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsrolemodule_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsRoleModuleService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsRoleModule tsRoleModule = tsRoleModuleService.getById(Integer.valueOf(sId));
			if (null != tsRoleModule) info.append(",").append(tsRoleModule.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}