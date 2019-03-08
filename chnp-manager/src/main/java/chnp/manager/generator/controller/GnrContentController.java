package chnp.manager.generator.controller;

import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.generator.model.domain.GnrContent;
import chnp.manager.generator.model.query.GnrContentQuery;
import chnp.manager.generator.service.GnrContentService;
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
@RequestMapping("/gnrcontent")
public class GnrContentController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GnrContentService gnrContentService;

	/**<p>首页</p>
	 *
	 * @return 首页
	 */
	@RequiresPermissions(value = {"gnrcontent"})
	@RequestMapping
	public String index() {
		return "gnrcontent/index";
	}

	@RequiresPermissions(value = "gnrcontent_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(GnrContentQuery gnrContentQuery) {
		return gnrContentService.findPage(gnrContentQuery);
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrcontent_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		GnrContent gnrContent = gnrContentService.getById(id);
		if (null == gnrContent) gnrContent = new GnrContent();
		model.addAttribute("gnrContent", gnrContent);
		return "gnrcontent/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param gnrContent 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrcontent_save"})
	@RequestMapping("/new")
	public String _new(Model model, GnrContent gnrContent) {
		model.addAttribute("gnrContent", gnrContent);
		return "gnrcontent/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param gnrContent 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrcontent_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(GnrContent gnrContent) {
		ResponseJson responseJson = new ResponseJson();

		if (gnrContentService.save(gnrContent) > 0) {
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
	@RequiresPermissions(value = {"gnrcontent_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		GnrContent gnrContent = gnrContentService.getById(id);
		if (null == gnrContent) gnrContent = new GnrContent();
		model.addAttribute("gnrContent", gnrContent);
		return "gnrcontent/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param gnrContent 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrcontent_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(GnrContent gnrContent) {
		ResponseJson responseJson = new ResponseJson();

		if (gnrContent.getId() == null) responseJson.error("未指定更新对象！");
		else if (gnrContentService.update(gnrContent) > 0) {
			responseJson.success("更新成功！");
		}else responseJson.error("更新失败！");

		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrcontent_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				gnrContentService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			GnrContent gnrContent = gnrContentService.getById(Integer.valueOf(sId));
			if (null != gnrContent) info.append(",").append(gnrContent.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}