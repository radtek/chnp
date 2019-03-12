package chnp.manager.mvc.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.model.query.TsDataItemQuery;
import chnp.manager.mvc.service.TsDataService;
import chnp.manager.mvc.service.TsDataItemService;
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
@RequestMapping("/tsdataitem")
public class TsDataItemController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsDataService tsDataService;
	@Autowired
	private TsDataItemService tsDataItemService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"tsdataitem"})
	@RequestMapping("/rel")
	public String index(Model model, @RequestParam(name = "dataId") Integer dataId) {
		TsDataItemQuery query = new TsDataItemQuery();
		query.setDataId(dataId);
		model.addAttribute("tsDataItemQuery", query);
		return "tsdataitem/index";
	}

	@RequiresPermissions(value = "tsdataitem_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(TsDataItemQuery tsDataItemQuery) {
		return tsDataItemService.findPage(tsDataItemQuery);
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsdataitem_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		TsDataItem tsDataItem = tsDataItemService.view(id);
		if (null == tsDataItem) tsDataItem = new TsDataItem();
		model.addAttribute("tsDataItem", tsDataItem);
		return "tsdataitem/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param tsDataItem 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"tsdataitem_save"})
	@RequestMapping("/new")
	public String _new(Model model, TsDataItem tsDataItem) {
		model.addAttribute("tsDataItem", tsDataItem);
		return "tsdataitem/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param tsDataItem 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsdataitem_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(TsDataItem tsDataItem) {
		ResponseJson responseJson = new ResponseJson();

		if (tsDataItemService.save(tsDataItem) > 0) {
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
	@RequiresPermissions(value = {"tsdataitem_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		TsDataItem tsDataItem = tsDataItemService.getById(id);
		if (null == tsDataItem) tsDataItem = new TsDataItem();
		model.addAttribute("tsDataItem", tsDataItem);
		return "tsdataitem/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param tsDataItem 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsdataitem_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(TsDataItem tsDataItem) {
		ResponseJson responseJson = new ResponseJson();

		if (tsDataItem.getId() == null) responseJson.error("未指定更新对象！");
		else if (tsDataItemService.update(tsDataItem) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"tsdataitem_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				tsDataItemService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			TsDataItem tsDataItem = tsDataItemService.getById(Integer.valueOf(sId));
			if (null != tsDataItem) info.append(",").append(tsDataItem.getItemKey());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}