package chnp.manager.mvc.controller;

import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsModule;
import chnp.manager.mvc.model.query.TsModuleQuery;
import chnp.manager.mvc.service.TsDataService;
import chnp.manager.mvc.service.TsModuleService;
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
import java.util.List;

@Controller
@RequestMapping("/tsmodule")
public class TsModuleController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsDataService tsDataService;
	@Autowired
	private TsModuleService tsModuleService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsmodule"})
	@RequestMapping
	public String index() {
		return "tsmodule/index";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsModule 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsmodule_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsModule tsModule) {
		model.addAttribute("tsModule", tsModule);

		TsModuleQuery tsModuleQuery = new TsModuleQuery();
		model.addAttribute("parentTree", tsModuleService.getSelectTree(tsModuleQuery));

		model.addAttribute("moduleTypes", tsDataService.findValuesByKey("tsModuleType"));
		return "tsmodule/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsModule 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsmodule_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsModule tsModule) {
		ResponseJson responseJson = new ResponseJson();

		if (tsModuleService.save(tsModule) > 0) {
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
	@RequiresPermissions(value = {"tsmodule_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id")Integer id) {
		TsModule tsModule = tsModuleService.getById(id);
		if (null == tsModule) tsModule = new TsModule();
		model.addAttribute("tsModule", tsModule);

		TsModuleQuery tsModuleQuery = new TsModuleQuery();
		model.addAttribute("parentTree", tsModuleService.getSelectTree(tsModuleQuery));

		model.addAttribute("moduleTypes", tsDataService.findValuesByKey("tsModuleType"));
		return "tsmodule/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsModule 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsmodule_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsModule tsModule) {
		ResponseJson responseJson = new ResponseJson();

		if (tsModule.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsModuleService.update(tsModule) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsmodule_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public String del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsModuleService.deepdelete(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的菜单删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsModule tsModule = tsModuleService.getById(Integer.valueOf(sId));
			if (null != tsModule) info.append(",").append(tsModule.getName());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson.toJSONString();
	}

	/**<p>菜单数据获取接口</p>
	 *
	 * @param tsModuleQuery 查询条件
	 * @return 菜单数据列表
	 */
	@RequiresPermissions(value = {"tsmodule_query"})
	@ResponseBody
	@RequestMapping("/data")
	public List<TsModule> data(TsModuleQuery tsModuleQuery) {
		tsModuleQuery.setAdditionalConstrains("order by parent_id, sort");
		return tsModuleService.findAllByCondition(tsModuleQuery);
	}

}