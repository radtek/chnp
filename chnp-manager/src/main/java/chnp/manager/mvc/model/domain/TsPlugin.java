package chnp.manager.mvc.model.domain;


public class TsPlugin {

	public static final String ALIAS_ID = "插件ID";
	public static final String ALIAS_NAME = "插件名称";
	public static final String ALIAS_VERSION = "插件版本";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_TYPE = "插件类型：1-前端，2-后端";
	public static final String ALIAS_DESCR = "插件描述";

	private Integer id;
	
	private String name;
	
	private String version;
	
	private java.util.Date updateTime;
	
	private String updateTimeString;
	
	private Integer type;
	
	private String descr;
	
	
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
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
		if (null !=  updateTimeString && !"".equals( updateTimeString)) {
			setUpdateTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateTimeString));
		}
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
	
	// Constructor
	public TsPlugin() {}

	public TsPlugin(Integer id) {
		this.id = id;
	}

}