package chnp.manager.generator.controller;

import chnp.common.utils.StringUtils;
import chnp.manager.common.entity.DataTables;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.generator.model.domain.GnrContent;
import chnp.manager.generator.model.domain.GnrProject;
import chnp.manager.generator.model.domain.GnrTemplate;
import chnp.manager.generator.model.query.GnrTemplateQuery;
import chnp.manager.generator.service.GnrProjectService;
import chnp.manager.generator.service.GnrTemplateService;
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
@RequestMapping("/gnrtemplate")
public class GnrTemplateController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GnrProjectService gnrProjectService;
	@Autowired
	private GnrTemplateService gnrTemplateService;

	/**<p>关联页</p>
	 *
	 * @return 关联页
	 */
	@RequiresPermissions(value = {"gnrtemplate"})
	@RequestMapping("/rel")
	public String index(Model model, @RequestParam(name = "projectId") Integer projectId) {
		GnrTemplateQuery query = new GnrTemplateQuery();
		query.setProjectId(projectId);
		model.addAttribute("templateQuery", query);
		return "gnrtemplate/index";
	}

	@RequiresPermissions(value = "gnrtemplate_query")
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataTables page(GnrTemplateQuery gnrTemplateQuery) {
		DataTables dataTables;
		if (null == gnrTemplateQuery.getProjectId()) {
			dataTables = new DataTables();
			dataTables.setDraw(gnrTemplateQuery.getDraw());
			dataTables.setData(new ArrayList());
		}else {
			dataTables = gnrTemplateService.findPage(gnrTemplateQuery);
		}
		return dataTables;
	}

	/**<p>查看界面</p>
	 *
	 * @param model
	 * @param id 配置主键
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrtemplate_query"})
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(name = "id") Integer id) {
		GnrTemplate gnrTemplate = gnrTemplateService.view(id);
		if (null == gnrTemplate) gnrTemplate = new GnrTemplate();
		model.addAttribute("gnrTemplate", gnrTemplate);
		return "gnrtemplate/view";
	}

	/**<p>新增界面</p>
	 *
	 * @param model
	 * @param gnrTemplate 初始化数据
	 * @return 新增界面
	 */
	@RequiresPermissions(value = {"gnrtemplate_save"})
	@RequestMapping("/new")
	public String _new(Model model, GnrTemplate gnrTemplate) {
		if (null == gnrTemplate.getProjectId()) {
			throw new NullPointerException("未指定项目ID");
		}
		model.addAttribute("gnrTemplate", gnrTemplate);
		return "gnrtemplate/new";
	}

	/**<p>保存数据</p>
	 *
	 * @param gnrTemplate 保存对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrtemplate_save"})
	@ResponseBody
	@RequestMapping("/save")
	public ResponseJson save(GnrTemplate gnrTemplate, String content) {
		ResponseJson responseJson = new ResponseJson();

		if (!StringUtils.isEmpty(content)) {
			GnrContent gnrContent = new GnrContent();
			gnrContent.setContent(content);
			gnrTemplate.setGnrContent(gnrContent);
		}
		gnrTemplateService.addTemmplate(gnrTemplate);
		responseJson.success("更新成功！");

		return responseJson;
	}

	/**<p>编辑界面</p>
	 *
	 * @param model
	 * @param id 数据主键
	 * @return 编辑界面
	 */
	@RequiresPermissions(value = {"gnrtemplate_update"})
	@RequestMapping("/edit")
	public String _edit(Model model, @RequestParam(name = "id") Integer id) {
		GnrTemplate gnrTemplate = gnrTemplateService.view(id);
		if (null == gnrTemplate) gnrTemplate = new GnrTemplate();
		model.addAttribute("gnrTemplate", gnrTemplate);
		return "gnrtemplate/edit";
	}

	/**<p>更新数据</p>
	 *
	 * @param gnrTemplate 更新对象
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrtemplate_update"})
	@ResponseBody
	@RequestMapping("/update")
	public ResponseJson update(GnrTemplate gnrTemplate, String content) {
		ResponseJson responseJson = new ResponseJson();

		if (null == gnrTemplate.getId()) {
			responseJson.error("未指定更新对象");
		}else {
			GnrTemplate or = gnrTemplateService.getById(gnrTemplate.getId());
			if (null == or) {
				responseJson.error("数据不存在");
			}else {
				if (!StringUtils.isEmpty(content)) {
					GnrContent gnrContent = new GnrContent();
					gnrContent.setId(or.getContentId());
					gnrContent.setContent(content);
					gnrTemplate.setGnrContent(gnrContent);
				}

				gnrTemplate.setContentId(null);
				gnrTemplate.setProjectId(null);
				gnrTemplateService.modify(gnrTemplate);
				responseJson.success("更新成功！");
			}
		}
		return responseJson;
	}

	/**<p>删除</p>
	 *
	 * @param ids 数据主键列表字符串
	 * @return 操作结果
	 */
	@RequiresPermissions(value = {"gnrtemplate_delete"})
	@ResponseBody
	@RequestMapping("/del")
	public ResponseJson del(@RequestParam(name = "ids") String ids) {
		ResponseJson responseJson = new ResponseJson();

		List<String> fails = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String sId : idList) {
			try {
				gnrTemplateService.deleteById(Integer.valueOf(sId));
			}catch (Exception e) {
				log.error("ID为" + sId + "的用户删除失败", e);
				fails.add(sId);
			}
		}

		StringBuilder info = new StringBuilder();
		for (String sId : fails) {
			GnrTemplate gnrTemplate = gnrTemplateService.getById(Integer.valueOf(sId));
			if (null != gnrTemplate) info.append(",").append(gnrTemplate.getId());
		}

		if (info.length() > 0) {
			responseJson.error(info.substring(1) + " 删除失败！");
		}else responseJson.success("删除成功！");

		return responseJson;
	}

}