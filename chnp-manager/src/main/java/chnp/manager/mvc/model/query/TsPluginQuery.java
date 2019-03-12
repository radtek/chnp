package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;


public class TsPluginQuery extends PaginationQuery{

	private Integer id;
	
	private String name;
	
	private String version;
	
	private java.util.Date updateTimeBegin;

	private String updateTimeBeginString;

	private java.util.Date updateTimeEnd;

	private String updateTimeEndString;
	
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
		if(null!=updateTimeBeginString && !"".equals(updateTimeBeginString)){
			setUpdateTimeBegin(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateTimeBeginString));
		}
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

	public void setUpdateTimeEndString(String updateTimeEndString) throws java.text.ParseException{
		if(null!=updateTimeEndString && !"".equals(updateTimeEndString)) {
			setUpdateTimeEnd(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateTimeEndString));
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
	


	public TsPluginQuery() {}

	public TsPluginQuery(Integer id) {
		this.id = id;
	}
}