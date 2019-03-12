package chnp.manager.mvc.controller;

import chnp.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import chnp.manager.common.entity.DataTables;
import chnp.manager.common.service.UtilService;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;
import chnp.manager.mvc.service.TsUserService;
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
@RequestMapping("/tsuser")
public class TsUserController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UtilService utilService;
	@Autowired
	private TsUserService tsUserService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsuser"})
	@RequestMapping
	public String index() {
		return "tsuser/index";
	}

	@RequiresPermissions(value = "tsuser_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsUserQuery tsUserQuery) {
		DataTables dataTables = tsUserService.findPage(tsUserQuery);
		utilService.interpret(dataTables, TsUser.class);
		return dataTables;
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsuser_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsUser tsUser = tsUserService.getById(id);
		if (null == tsUser) tsUser = new TsUser();
		model.addAttribute("tsUser", tsUser);
		return "tsuser/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsUser 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsuser_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsUser tsUser) {
		model.addAttribute("tsUser", tsUser);
		return "tsuser/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsUser 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsuser_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsUser tsUser) {
		ResponseJson responseJson = new ResponseJson();

		if (StringUtils.isEmpty(tsUser.getUserPswd())) {
			responseJson.error("未提供用户密码");
		}else {
			try {
				tsUser.setUserPswd(StringUtils.MD5Encode(tsUser.getUserPswd()));
				if (tsUserService.save(tsUser) > 0) {
					responseJson.success("添加成功！");
				} else responseJson.error("添加失败！");
			}catch (Exception e) {
				log.error("密码加密失败", e);
				responseJson.error("添加失败");
			}
		}

		return responseJson;
	}

	/**<p>编辑界面</p>
	 *
	 * @param model
	 * @param id 数据主键
	 * @return 编辑界面
	 */
	@RequiresPermissions(value = {"tsuser_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		TsUser tsUser = tsUserService.getById(id);
		if (null == tsUser) tsUser = new TsUser();
		model.addAttribute("tsUser", tsUser);
		return "tsuser/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsUser 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsuser_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsUser tsUser) {
		ResponseJson responseJson = new ResponseJson();

		tsUser.setUserPswd(null);
		tsUser.setRegisterTime(null);
		if (tsUser.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsUserService.update(tsUser) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsuser_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsUserService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsUser tsUser = tsUserService.getById(Integer.valueOf(sId));
			if (null != tsUser) info.append(",").append(tsUser.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}