package chnp.manager.generator.model.domain;

public class GnrTemplate {

	public static final String ALIAS_ID = "记录ID";
	public static final String ALIAS_PROJECT_ID = "项目ID";
	public static final String ALIAS_TEMPLATE_NAME = "模板名称";
	public static final String ALIAS_CONTENT_ID = "模板ID";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFIED_TIME = "更新时间";

	private Integer id;
	
	private Integer projectId;
	
	private String templateName;

	private Integer templateType;
	
	private Integer contentId;
	
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
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
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
	


	private GnrProject gnrProject;

	public GnrProject getGnrProject() {
		return gnrProject;
	}

	public void setGnrProject(GnrProject gnrProject) {
		this.gnrProject = gnrProject;
	}

	private GnrContent gnrContent;

	public GnrContent getGnrContent() {
		return gnrContent;
	}

	public void setGnrContent(GnrContent gnrContent) {
		this.gnrContent = gnrContent;
	}

	// Constructor
	public GnrTemplate() {
		
	}
}
