package chnp.manager.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import chnp.manager.common.entity.DataTables;
import chnp.manager.common.service.UtilService;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsUserRole;
import chnp.manager.mvc.model.query.TsUserRoleQuery;
import chnp.manager.mvc.service.TsUserRoleService;
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
@RequestMapping("/tsuserrole")
public class TsUserRoleController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UtilService utilService;
	@Autowired
	private TsUserRoleService tsUserRoleService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsuserrole"})
	@RequestMapping
	public String index() {
		return "tsuserrole/index";
	}

	@RequiresPermissions(value = "tsuserrole_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsUserRoleQuery tsUserRoleQuery) {
		DataTables dataTables = tsUserRoleService.findPage(tsUserRoleQuery);
		utilService.interpret(dataTables, TsUserRole.class);
		return dataTables;
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsuserrole_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsUserRole tsUserRole = tsUserRoleService.getById(id);
		if (null == tsUserRole) tsUserRole = new TsUserRole();
		model.addAttribute("tsUserRole", tsUserRole);
		return "tsuserrole/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsUserRole 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsuserrole_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsUserRole tsUserRole) {
		model.addAttribute("tsUserRole", tsUserRole);
		return "tsuserrole/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsUserRole 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsuserrole_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsUserRole tsUserRole) {
		ResponseJson responseJson = new ResponseJson();

		if (tsUserRoleService.save(tsUserRole) > 0) {
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
	@RequiresPermissions(value = {"tsuserrole_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		TsUserRole tsUserRole = tsUserRoleService.getById(id);
		if (null == tsUserRole) tsUserRole = new TsUserRole();
		model.addAttribute("tsUserRole", tsUserRole);
		return "tsuserrole/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsUserRole 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsuserrole_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsUserRole tsUserRole) {
		ResponseJson responseJson = new ResponseJson();

		if (tsUserRole.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsUserRoleService.update(tsUserRole) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsuserrole_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsUserRoleService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsUserRole tsUserRole = tsUserRoleService.getById(Integer.valueOf(sId));
			if (null != tsUserRole) info.append(",").append(tsUserRole.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}