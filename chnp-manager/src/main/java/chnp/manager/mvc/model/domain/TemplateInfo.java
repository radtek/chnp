package chnp.manager.mvc.model.domain;

import chnp.manager.common.entity.AbstractEntity;

public class TemplateInfo extends AbstractEntity {

	// Alias
	public static final String ALIAS_ID = "模板ID";
	public static final String ALIAS_NAME = "模板名称（包括后缀）";
	public static final String ALIAS_FILE_PATH = "相对路径";
	public static final String ALIAS_TEMPLATE = "模板内容";
	public static final String ALIAS_ENGINE = "模板引擎：1-FreeMarker";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_USER_ID = "创建者";


	// Properties
	private Integer id;
	
	private String name;
	
	private String filePath;
	
	private String template;
	
	private Integer engine;
	
	private java.util.Date updateTime;
	
	private String updateTimeString;
	
	private Integer userId;
	
	
	
	// Getter/Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	public Integer getEngine() {
		return engine;
	}

	public void setEngine(Integer engine) {
		this.engine = engine;
	}
	
	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
		this.updateTimeString = getUpdateTimeString();
	}

	public String getUpdateTimeString() {
		if (null == updateTime) {
			return null;
		}
		return this.updateTimeString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
	}

	public void setUpdateTimeString(String updateTimeString) throws java.text.ParseException {
		setUpdateTime(new java.text.SimpleDateFormat().parse(updateTimeString));
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	// Constructor
	public TemplateInfo() {
		
	}
}
