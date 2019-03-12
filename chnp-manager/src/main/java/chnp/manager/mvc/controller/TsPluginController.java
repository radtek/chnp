package chnp.manager.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import chnp.manager.common.entity.DataTables;
import chnp.manager.common.service.UtilService;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsPlugin;
import chnp.manager.mvc.model.query.TsPluginQuery;
import chnp.manager.mvc.service.TsPluginService;
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
@RequestMapping("/tsplugin")
public class TsPluginController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UtilService utilService;
	@Autowired
	private TsPluginService tsPluginService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsplugin"})
	@RequestMapping
	public String index() {
		return "tsplugin/index";
	}

	@RequiresPermissions(value = "tsplugin_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsPluginQuery tsPluginQuery) {
		DataTables dataTables = tsPluginService.findPage(tsPluginQuery);
		utilService.interpret(dataTables, TsPlugin.class);
		return dataTables;
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsplugin_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsPlugin tsPlugin = tsPluginService.getById(id);
		if (null == tsPlugin) tsPlugin = new TsPlugin();
		model.addAttribute("tsPlugin", tsPlugin);
		return "tsplugin/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsPlugin 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsplugin_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsPlugin tsPlugin) {
		model.addAttribute("tsPlugin", tsPlugin);
		return "tsplugin/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsPlugin 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsplugin_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsPlugin tsPlugin) {
		ResponseJson responseJson = new ResponseJson();

		if (tsPluginService.save(tsPlugin) > 0) {
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
	@RequiresPermissions(value = {"tsplugin_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		TsPlugin tsPlugin = tsPluginService.getById(id);
		if (null == tsPlugin) tsPlugin = new TsPlugin();
		model.addAttribute("tsPlugin", tsPlugin);
		return "tsplugin/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsPlugin 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsplugin_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsPlugin tsPlugin) {
		ResponseJson responseJson = new ResponseJson();

		if (tsPlugin.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsPluginService.update(tsPlugin) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsplugin_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsPluginService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsPlugin tsPlugin = tsPluginService.getById(Integer.valueOf(sId));
			if (null != tsPlugin) info.append(",").append(tsPlugin.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}