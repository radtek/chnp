package chnp.manager.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import chnp.manager.common.entity.DataTables;
import chnp.manager.common.service.UtilService;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsRole;
import chnp.manager.mvc.model.query.TsRoleQuery;
import chnp.manager.mvc.service.TsRoleService;
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
@RequestMapping("/tsrole")
public class TsRoleController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UtilService utilService;
	@Autowired
	private TsRoleService tsRoleService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsrole"})
	@RequestMapping
	public String index() {
		return "tsrole/index";
	}

	@RequiresPermissions(value = "tsrole_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsRoleQuery tsRoleQuery) {
		DataTables dataTables = tsRoleService.findPage(tsRoleQuery);
		utilService.interpret(dataTables, TsRole.class);
		return dataTables;
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsrole_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsRole tsRole = tsRoleService.getById(id);
		if (null == tsRole) tsRole = new TsRole();
		model.addAttribute("tsRole", tsRole);
		return "tsrole/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsRole 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsrole_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsRole tsRole) {
		model.addAttribute("tsRole", tsRole);
		return "tsrole/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsRole 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsrole_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsRole tsRole) {
		ResponseJson responseJson = new ResponseJson();

		if (tsRoleService.save(tsRole) > 0) {
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
	@RequiresPermissions(value = {"tsrole_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		TsRole tsRole = tsRoleService.getById(id);
		if (null == tsRole) tsRole = new TsRole();
		model.addAttribute("tsRole", tsRole);
		return "tsrole/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsRole 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsrole_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsRole tsRole) {
		ResponseJson responseJson = new ResponseJson();

		if (tsRole.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsRoleService.update(tsRole) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsrole_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsRoleService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsRole tsRole = tsRoleService.getById(Integer.valueOf(sId));
			if (null != tsRole) info.append(",").append(tsRole.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}