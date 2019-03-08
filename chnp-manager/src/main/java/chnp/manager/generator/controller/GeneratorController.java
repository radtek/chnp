package chnp.manager.generator.controller;

import chnp.common.mysql.MysqlUtils;
import chnp.manager.common.entity.GeneratorConfig;
import chnp.manager.common.entity.ResponseJson;
import chnp.manager.generator.model.domain.GnrProject;
import chnp.manager.generator.model.query.GnrProjectQuery;
import chnp.manager.generator.model.query.GnrTemplateQuery;
import chnp.manager.generator.service.GnrProjectService;
import chnp.manager.generator.service.GnrTemplateService;
import chnp.manager.mvc.model.domain.TsConfig;
import chnp.manager.mvc.model.query.TemplateInfoQuery;
import chnp.manager.mvc.service.TemplateInfoService;
import chnp.manager.mvc.service.TsConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/generator")
public class GeneratorController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsConfigService tsConfigService;
	@Autowired
	private GnrProjectService gnrProjectService;
	@Autowired
	private GnrTemplateService gnrTemplateService;

	@RequestMapping
	public String index(Model model) {
		GnrProjectQuery query = new GnrProjectQuery();
		query.setAdditionalConstrains("order by create_time desc");
		model.addAttribute("projects", gnrProjectService.findByCondition(query));

		return "generator/index";
	}

	@RequiresPermissions(value = {"generator"})
	@ResponseBody
	@RequestMapping("/test/db")
	public String test(GeneratorConfig config) {
		ResponseJson responseJson = new ResponseJson();

		try {
			config.setDbParams(new HashMap<String, String>() {
				{
					put("useUnicode", "true");
					put("characterEncoding", "utf8");
					put("pinGlobalTxToPhysicalConnection", "true");
					put("useSSL", "false");
					put("serverTimezone", "UTC");
				}
			});

			MysqlUtils mysqlUtils = new MysqlUtils(
					config.getDbHost(),
					config.getDbPort(),
					config.getDbParams(),
					config.getDbUser(),
					config.getDbPswd());
			if (mysqlUtils.isAvailable())
				responseJson.success("连接成功！");
			else responseJson.error("连接失败！");
		}catch (Exception e) {
			log.error("数据库连接失败", e);
			responseJson.error("连接失败：" + e.toString());
		}

		return responseJson.toJSONString();
	}

	@RequiresPermissions(value = {"generator"})
	@ResponseBody
	@RequestMapping("/generate")
	public String generate(@RequestParam(name = "projectId") Integer projectId,
						   @RequestParam(name = "templateIds") String templateIds,
						   GeneratorConfig config) {
		ResponseJson responseJson = new ResponseJson();

		try {
			config.setDbParams(new HashMap<String, String>() {
				{
					put("useUnicode", "true");
					put("characterEncoding", "utf8");
					put("pinGlobalTxToPhysicalConnection", "true");
					put("useSSL", "false");
					put("serverTimezone", "UTC");
				}
			});

			TsConfig cfgTemplatePath = tsConfigService.getByKey("template_base_path");
			config.templateBasePath = cfgTemplatePath.getConfigVal();

			TsConfig cfgGenerationPath = tsConfigService.getByKey("generation_base_path");
			config.generationBasePath = cfgGenerationPath.getConfigVal();

			GnrTemplateQuery templateInfoQuery = new GnrTemplateQuery();
			templateInfoQuery.setProjectId(projectId);
			templateInfoQuery.setAdditionalFilters("id in (" + templateIds + ")");
			config.input(gnrTemplateService.findByAssociation(templateInfoQuery));

			config.generate();

			responseJson.success("代码已生成");
		}catch (Exception e) {
			log.error("数据库连接失败", e);
			responseJson.error("连接失败：" + e.toString());
		}

		return responseJson.toJSONString();
	}

}