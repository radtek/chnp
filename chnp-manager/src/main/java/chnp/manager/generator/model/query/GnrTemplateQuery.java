package chnp.manager.generator.model.query;

import chnp.manager.common.entity.PaginationQuery;

public class GnrTemplateQuery extends PaginationQuery{

	private Integer id;
	
	private Integer projectId;
	
	private String templateName;

	private Integer templateType;
	
	private Integer contentId;
	
	private java.util.Date createTimeBegin;

	private String createTimeBeginString;

	private java.util.Date createTimeEnd;

	private String createTimeEndString;
	
	private java.util.Date modifiedTimeBegin;

	private String modifiedTimeBeginString;

	private java.util.Date modifiedTimeEnd;

	private String modifiedTimeEndString;
	

	public GnrTemplateQuery() {
		
	}
	
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
	
	public java.util.Date getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(java.util.Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
		this.createTimeBeginString = getCreateTimeBeginString();
	}

	public String getCreateTimeBeginString() {
		if (null == createTimeBegin) {
			return null;
		}
		return this.createTimeBeginString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTimeBegin);
	}

	public void setCreateTimeBeginString(String createTimeBeginString) throws java.text.ParseException {
		if(null!=createTimeBeginString && !"".equals(createTimeBeginString)){
			setCreateTimeBegin(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTimeBeginString));
		}
	}

	public java.util.Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(java.util.Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
		this.createTimeEndString = getCreateTimeEndString();
	}

	public String getCreateTimeEndString() {
		if (null == createTimeEnd) {
			return null;
		}
		return this.createTimeEndString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTimeEnd);
	}

	public void setCreateTimeEndString(String createTimeEndString) throws java.text.ParseException{
		if(null!=createTimeEndString && !"".equals(createTimeEndString)) {
			setCreateTimeEnd(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTimeEndString));
		}
	}
	public java.util.Date getModifiedTimeBegin() {
		return modifiedTimeBegin;
	}

	public void setModifiedTimeBegin(java.util.Date modifiedTimeBegin) {
		this.modifiedTimeBegin = modifiedTimeBegin;
		this.modifiedTimeBeginString = getModifiedTimeBeginString();
	}

	public String getModifiedTimeBeginString() {
		if (null == modifiedTimeBegin) {
			return null;
		}
		return this.modifiedTimeBeginString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(modifiedTimeBegin);
	}

	public void setModifiedTimeBeginString(String modifiedTimeBeginString) throws java.text.ParseException {
		if(null!=modifiedTimeBeginString && !"".equals(modifiedTimeBeginString)){
			setModifiedTimeBegin(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(modifiedTimeBeginString));
		}
	}

	public java.util.Date getModifiedTimeEnd() {
		return modifiedTimeEnd;
	}

	public void setModifiedTimeEnd(java.util.Date modifiedTimeEnd) {
		this.modifiedTimeEnd = modifiedTimeEnd;
		this.modifiedTimeEndString = getModifiedTimeEndString();
	}

	public String getModifiedTimeEndString() {
		if (null == modifiedTimeEnd) {
			return null;
		}
		return this.modifiedTimeEndString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(modifiedTimeEnd);
	}

	public void setModifiedTimeEndString(String modifiedTimeEndString) throws java.text.ParseException{
		if(null!=modifiedTimeEndString && !"".equals(modifiedTimeEndString)) {
			setModifiedTimeEnd(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(modifiedTimeEndString));
		}
	}
	
}
