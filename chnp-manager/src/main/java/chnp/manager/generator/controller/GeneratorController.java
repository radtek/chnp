package chnp.manager.mvc.controller;

import chnp.common.mysql.MysqlUtils;
import chnp.manager.common.entity.GeneratorConfig;
import chnp.manager.common.entity.ResponseJson;
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
	private TemplateInfoService templateInfoService;

	@RequestMapping
	public String index(Model model) {

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
	public String generate(@RequestParam(name = "templateIds") String templateIds, GeneratorConfig config) {
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

			TemplateInfoQuery templateInfoQuery = new TemplateInfoQuery();
			templateInfoQuery.setAdditionalFilters("id in (" + templateIds + ")");
			config.input(templateInfoService.findByCondition(templateInfoQuery));

			config.generate();
		}catch (Exception e) {
			log.error("数据库连接失败", e);
			responseJson.error("连接失败：" + e.toString());
		}

		return responseJson.toJSONString();
	}

}