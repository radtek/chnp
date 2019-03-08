package chnp.manager.mvc.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsKeyValue;
import chnp.manager.mvc.model.query.TsKeyQuery;
import chnp.manager.mvc.model.query.TsKeyValueQuery;
import chnp.manager.mvc.service.TsKeyService;
import chnp.manager.mvc.service.TsKeyValueService;
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
@RequestMapping("/tskeyvalue")
public class TsKeyValueController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsKeyService tsKeyService;
	@Autowired
	private TsKeyValueService tsKeyValueService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tskeyvalue"})
	@RequestMapping("/rel")
	public String index(Model model, @RequestParam(name = "keyId") Integer keyId) {
		TsKeyValueQuery query = new TsKeyValueQuery();
		query.setKeyId(keyId);
		model.addAttribute("tsKeyValueQuery", query);
		return "tskeyvalue/index";
	}

	@RequiresPermissions(value = "tskeyvalue_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsKeyValueQuery tsKeyValueQuery) {
		return tsKeyValueService.findPage(tsKeyValueQuery);
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tskeyvalue_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsKeyValue tsKeyValue = tsKeyValueService.view(id);
		if (null == tsKeyValue) tsKeyValue = new TsKeyValue();
		model.addAttribute("tsKeyValue", tsKeyValue);
		return "tskeyvalue/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsKeyValue 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tskeyvalue_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsKeyValue tsKeyValue) {
		model.addAttribute("tsKeyValue", tsKeyValue);
		return "tskeyvalue/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsKeyValue 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tskeyvalue_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsKeyValue tsKeyValue) {
		ResponseJson responseJson = new ResponseJson();

		if (tsKeyValueService.save(tsKeyValue) > 0) {
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
	@RequiresPermissions(value = {"tskeyvalue_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		TsKeyValue tsKeyValue = tsKeyValueService.getById(id);
		if (null == tsKeyValue) tsKeyValue = new TsKeyValue();
		model.addAttribute("tsKeyValue", tsKeyValue);
		return "tskeyvalue/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsKeyValue 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tskeyvalue_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsKeyValue tsKeyValue) {
		ResponseJson responseJson = new ResponseJson();

		if (tsKeyValue.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsKeyValueService.update(tsKeyValue) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tskeyvalue_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsKeyValueService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsKeyValue tsKeyValue = tsKeyValueService.getById(Integer.valueOf(sId));
			if (null != tsKeyValue) info.append(",").append(tsKeyValue.getName());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}