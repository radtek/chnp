package chnp.manager.mvc.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;
import chnp.manager.mvc.service.TsUserService;
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
@RequestMapping("/tsuser")
public class TsUserController {
	private final Logger log = LoggerFactory.getLogger(getClass());

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
		return tsUserService.findPage(tsUserQuery);
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

	/**<p>编辑界面</p>
	 *
	 * @param model
	 * @param id 数据主键
	 * @return 编辑界面
	 */
	@RequiresPermissions(value = {"tsuser_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id")Integer id) {
		TsUser tsUser = tsUserService.getById(id);
		if (null == tsUser) tsUser = new TsUser();
		model.addAttribute("tsUser", tsUser);
		return "tsuser/edit";
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsuser_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public String del(@RequestParam(name = "ids") String ids) {
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
			if (null != tsUser) info.append(",").append(tsUser.getUserName());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson.toJSONString();
	}

}