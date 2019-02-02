package chnp.manager.mvc.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsConfig;
import chnp.manager.mvc.model.query.TsConfigQuery;
import chnp.manager.mvc.service.TsConfigService;
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
@RequestMapping("/tsconfig")
public class TsConfigController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsConfigService tsConfigService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsconfig"})
	@RequestMapping
	public String index() {
		return "tsconfig/index";
	}

	@RequiresPermissions(value = "tsconfig_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsConfigQuery tsConfigQuery) {
		return tsConfigService.findPage(tsConfigQuery);
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsconfig_save"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsConfig tsConfig = tsConfigService.getById(id);
		if (null == tsConfig) tsConfig = new TsConfig();
		model.addAttribute("tsConfig", tsConfig);
		return "tsconfig/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsConfig 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsconfig_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsConfig tsConfig) {
		model.addAttribute("tsConfig", tsConfig);
		return "tsconfig/new";
	}

	/**<p>编辑界面</p>
	 *
	 * @param model
	 * @param id 数据主键
	 * @return 编辑界面
	 */
	@RequiresPermissions(value = {"tsconfig_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id")Integer id) {
		TsConfig tsConfig = tsConfigService.getById(id);
		if (null == tsConfig) tsConfig = new TsConfig();
		model.addAttribute("tsConfig", tsConfig);
		return "tsconfig/edit";
	}

	/**<p>删除</p>
	 *
	 * @param tsConfig 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsconfig_update"})
	@ResponseBody
	@RequestMapping("/update")
	public String update(TsConfig tsConfig) {
		ResponseJson responseJson = new ResponseJson();

		return responseJson.toJSONString();
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsconfig_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public String del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsConfigService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsConfig tsConfig = tsConfigService.getById(Integer.valueOf(sId));
			if (null != tsConfig) info.append(",").append(tsConfig.getConfigName());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson.toJSONString();
	}

}