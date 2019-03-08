package chnp.manager.mvc.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsKey;
import chnp.manager.mvc.model.query.TsKeyQuery;
import chnp.manager.mvc.service.TsKeyService;
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
@RequestMapping("/tskey")
public class TsKeyController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsKeyService tsKeyService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tskey"})
	@RequestMapping
	public String index() {
		return "tskey/index";
	}

	@RequiresPermissions(value = "tskey_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsKeyQuery tsKeyQuery) {
		return tsKeyService.findPage(tsKeyQuery);
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tskey_save"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsKey tsKey = tsKeyService.getById(id);
		if (null == tsKey) tsKey = new TsKey();
		model.addAttribute("tsKey", tsKey);
		return "tskey/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsKey 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tskey_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsKey tsKey) {
		model.addAttribute("tsKey", tsKey);
		return "tskey/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsKey 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tskey_insert"})
	@ResponseBody
	@RequestMapping("/save")
	public String save(TsKey tsKey) {
		ResponseJson responseJson = new ResponseJson();

		if (tsKeyService.save(tsKey) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson.toJSONString();
	}

	/**<p>编辑界面</p>
	 *
	 * @param model
	 * @param id 数据主键
	 * @return 编辑界面
	 */
	@RequiresPermissions(value = {"tskey_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id")Integer id) {
		TsKey tsKey = tsKeyService.getById(id);
		if (null == tsKey) tsKey = new TsKey();
		model.addAttribute("tsKey", tsKey);
		return "tskey/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsKey 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tskey_uopdate"})
	@ResponseBody
	@RequestMapping("/update")
	public String update(TsKey tsKey) {
		ResponseJson responseJson = new ResponseJson();

		if (tsKey.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsKeyService.update(tsKey) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson.toJSONString();
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tskey_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public String del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsKeyService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsKey tsKey = tsKeyService.getById(Integer.valueOf(sId));
			if (null != tsKey) info.append(",").append(tsKey.getName());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson.toJSONString();
	}

}