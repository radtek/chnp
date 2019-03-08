package chnp.manager.common.entity;

import chnp.common.mysql.MysqlUtils;
import chnp.common.utils.FileUtils;
import chnp.common.utils.StringUtils;
import chnp.manager.generator.model.domain.GnrTemplate;
import chnp.manager.mvc.model.domain.TemplateInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorConfig {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public static final String PLACEHOLDER_DIR_PAGE = "${AuthName}";
	public static final String PLACEHOLDER_DIR_CLASS = "${BasePackageDir}";
	public static final String PLACEHOLDER_FILE_CLASS = "${ClassName}";
	public static final String PAGE_DIR = "src/resources/pages/${AuthName}/";
	public static final String CODE_DIR = "src/resources/java/${BasePackageDir}/";

	private String orgType;
	private String orgName;
	private String proName;
	private String moduleName;
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	private String dbHost;
	private Integer dbPort;
	private String dbName;
	private String[] tables;
	private Map<String, String> dbParams = new HashMap<>();
	private String dbUser;
	private String dbPswd;

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public Integer getDbPort() {
		return dbPort;
	}

	public void setDbPort(Integer dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String[] getTables() {
		return tables;
	}

	public void setTables(String[] tables) {
		this.tables = tables;
	}

	public Map<String, String> getDbParams() {
		return dbParams;
	}

	public void setDbParams(Map<String, String> dbParams) {
		this.dbParams = dbParams;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPswd() {
		return dbPswd;
	}

	public void setDbPswd(String dbPswd) {
		this.dbPswd = dbPswd;
	}


	public String templateBasePath;
	public String generationBasePath;

	/**<p>获取基本包路径</p>
	 *
	 * @return 基本包路径
	 */
	public String getBasePackage() {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(orgType)) sb.append(".").append(orgType);
		if (!StringUtils.isEmpty(orgName)) sb.append(".").append(orgName);
		if (!StringUtils.isEmpty(proName)) sb.append(".").append(proName);
		return sb.length() > 0 ? sb.substring(1) : "com";
	}

	public String getPackage() {
		String basePackage = getBasePackage();
		if (!StringUtils.isEmpty(moduleName)) {
			return basePackage + "." + moduleName;
		}
		return basePackage;
	}

	public void generate() throws Exception {
		MysqlUtils mysqlUtils = new MysqlUtils(dbHost, dbPort, dbParams, dbUser, dbPswd);

		// 获取渲染数据，并设置基本包路径和命名规则。一个RenderData对象就是一张表
		List<Map<String, Object>> dataSet = mysqlUtils.getMetaData(dbName, tables);

		// 清理目标路径下的文件
		FileUtils.delContent(generationBasePath);

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// 遍历渲染数据集
		for(Map<String, Object> data : dataSet){
			log.info("开始自动生成{}表的代码...", data.get(MysqlUtils.MD_TABLE_NAME));

			data.put("basePackage", getBasePackage());
			data.put("package", getPackage());

			// 模板文件
			List<Map<String, String>> templateFiles = getAllFiles(null, (String) data.get(MysqlUtils.MD_TABLE_CLASS_NAME));
			for (Map<String, String> templateFile : templateFiles) {
				log.info("模板文件路径：{}/{}", templateFile.get("inputFileDir"), templateFile.get("inputFileName"));
				log.info("目标文件路径：{}/{}", templateFile.get("targetFileDir"), templateFile.get("targetFileName"));

				cfg.setDirectoryForTemplateLoading(new File(templateFile.get("inputFileDir")));// 加载文件夹下的模板文件
				Template temp = cfg.getTemplate(templateFile.get("inputFileName"));// 获取指定的模板文件

				File t = new File(templateFile.get("targetFileDir"));
				if (!t.exists()) t.mkdirs();

				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(new File(templateFile.get("targetFileDir")+"/"+templateFile.get("targetFileName"))),"UTF-8"));

				temp.process(data, out);
				out.close();
			}

			mysqlUtils.close();
		}
	}

	/**<p>生成模板</p>
	 * <p>
	 *     将模板代码输出到模板文件中，供FreeMarker生成目标文件。
	 * </p>
	 *
	 * @param templateInfos 模板信息集合
	 * @throws IOException 生成失败
	 */
	public void input(List<GnrTemplate> templateInfos) throws IOException {
		for (GnrTemplate templateInfo : templateInfos) {
			String templateFilePath = templateBasePath + (1 == templateInfo.getTemplateType() ? PAGE_DIR : CODE_DIR) + templateInfo.getTemplateName();
			File templateFile = new File(templateFilePath);
			if (!templateFile.getParentFile().exists()) {
				templateFile.getParentFile().mkdirs();
			}
			templateFile.createNewFile();

			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(templateFilePath),"UTF-8");
			out.write(templateInfo.getGnrContent().getContent());
			out.flush();
			out.close();
		}
	}

	/**<p>递归遍历模板文件，获取模板文件和目标文件的路径与名称。</p>
	 *
	 * @param fileRelPath 模板文件的相对路径
	 * @param className 模板文件对应的类名
	 * @return 模板文件的输入与输出信息
	 */
	private List<Map<String, String>> getAllFiles(String fileRelPath, String className) {
		List<Map<String, String>> files = new ArrayList<>();

		File file = new File(templateBasePath + (StringUtils.isEmpty(fileRelPath) ? fileRelPath = "" : "/" + fileRelPath));
		if (file.isFile()) {
			Map<String, String> map = new HashMap<>();

			map.put("inputFileDir", file.getParent());
			map.put("inputFileName", file.getName());

			String targetFilePath = generationBasePath +
					fileRelPath
							.replace(PLACEHOLDER_DIR_PAGE, className.toLowerCase())
							.replace(PLACEHOLDER_DIR_CLASS, getPackage().replace(".", "/"));
			map.put("targetFileDir",  targetFilePath.substring(0, targetFilePath.lastIndexOf("/")));
			map.put("targetFileName", file.getName().replace(PLACEHOLDER_FILE_CLASS, className));

			files.add(map);
		}else if (file.isDirectory()) {
			String[] fileList = file.list();
			for (String f : fileList) {
				files.addAll(getAllFiles(fileRelPath + "/" + f, className));
			}
		}
		return files;
	}
}