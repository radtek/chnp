package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;

public class TemplateInfoQuery extends PaginationQuery {

	private Integer id;
	
	private String name;
	
	private String filePath;
	
	private String template;
	
	private Integer engine;
	
	private java.util.Date updateTimeBegin;

	private String updateTimeBeginString;

	private java.util.Date updateTimeEnd;

	private String updateTimeEndString;
	
	private Integer userId;
	

	public TemplateInfoQuery() {
		
	}

	
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
	
	public java.util.Date getUpdateTimeBegin() {
		return updateTimeBegin;
	}

	public void setUpdateTimeBegin(java.util.Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
		this.updateTimeBeginString = getUpdateTimeBeginString();
	}

	public String getUpdateTimeBeginString() {
		if (null == updateTimeBegin) {
			return null;
		}
		return this.updateTimeBeginString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTimeBegin);
	}

	public void setUpdateTimeBeginString(String updateTimeBeginString) throws java.text.ParseException {
		setUpdateTimeBegin(new java.text.SimpleDateFormat().parse(updateTimeBeginString));
	}

	public java.util.Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(java.util.Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
		this.updateTimeEndString = getUpdateTimeEndString();
	}

	public String getUpdateTimeEndString() {
		if (null == updateTimeEnd) {
			return null;
		}
		return this.updateTimeEndString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTimeEnd);
	}

	public void setUpdateTimeEndString(String updateTimeEndString) throws java.text.ParseException {
		setUpdateTimeEnd(new java.text.SimpleDateFormat().parse(updateTimeEndString));
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("'template':{");
		
		if (null != id) {
			stringBuilder.append("'id':"+id+", ");
		}
		if (null != name && !"".equals(name)) {
			stringBuilder.append("'name':"+name+",");
		}
		if (null != filePath && !"".equals(filePath)) {
			stringBuilder.append("'filePath':"+filePath+",");
		}
		if (null != template && !"".equals(template)) {
			stringBuilder.append("'template':"+template+",");
		}
		if (null != engine) {
			stringBuilder.append("'engine':"+engine+", ");
		}
		if (null != updateTimeBegin) {
			stringBuilder.append("'updateTimeBegin':"+updateTimeBegin+",")
						 .append("'updateTimeBeginString':"+updateTimeBeginString+",");
		}
		if (null != updateTimeEnd) {
			stringBuilder.append("'updateTimeEnd':"+updateTimeEnd+",")
						 .append("'updateTimeEndString':"+updateTimeEndString+",");
		}
		if (null != userId) {
			stringBuilder.append("'userId':"+userId+", ");
		}
		
		return new StringBuilder(stringBuilder.substring(0, stringBuilder.length()-1)).append("}").toString();
	}
}
