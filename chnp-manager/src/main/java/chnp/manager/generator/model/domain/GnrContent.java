package chnp.manager.generator.model.domain;

public class GnrContent {

	public static final String ALIAS_ID = "模板ID";
	public static final String ALIAS_CONTENT = "模板内容";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFIED_TIME = "更新时间";

	private Integer id;
	
	private String content;
	
	private java.util.Date createTime;
	
	private String createTimeString;
	
	private java.util.Date modifiedTime;
	
	private String modifiedTimeString;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
		this.createTimeString = getCreateTimeString();
	}

	public String getCreateTimeString() {
		if (null == createTime) {
			return null;
		}
		return this.createTimeString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
	}

	public void setCreateTimeString(String createTimeString) throws java.text.ParseException {
		if (null !=  createTimeString && !"".equals( createTimeString)) {
			setCreateTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTimeString));
		}
	}
	
	public java.util.Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(java.util.Date modifiedTime) {
		this.modifiedTime = modifiedTime;
		this.modifiedTimeString = getModifiedTimeString();
	}

	public String getModifiedTimeString() {
		if (null == modifiedTime) {
			return null;
		}
		return this.modifiedTimeString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(modifiedTime);
	}

	public void setModifiedTimeString(String modifiedTimeString) throws java.text.ParseException {
		if (null !=  modifiedTimeString && !"".equals( modifiedTimeString)) {
			setModifiedTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(modifiedTimeString));
		}
	}
	
	
	
	// Constructor
	public GnrContent() {
		
	}
}
