package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;


public class TsUserQuery extends PaginationQuery{

	private Integer id;
	
	private String userName;
	
	private String userPswd;
	
	private java.util.Date registerTimeBegin;

	private String registerTimeBeginString;

	private java.util.Date registerTimeEnd;

	private String registerTimeEndString;
	
	private String userNick;
	
	private String linkEmail;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPswd() {
		return userPswd;
	}

	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}
	
	public java.util.Date getRegisterTimeBegin() {
		return registerTimeBegin;
	}

	public void setRegisterTimeBegin(java.util.Date registerTimeBegin) {
		this.registerTimeBegin = registerTimeBegin;
		this.registerTimeBeginString = getRegisterTimeBeginString();
	}

	public String getRegisterTimeBeginString() {
		if (null == registerTimeBegin) {
			return null;
		}
		return this.registerTimeBeginString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registerTimeBegin);
	}

	public void setRegisterTimeBeginString(String registerTimeBeginString) throws java.text.ParseException {
		if(null!=registerTimeBeginString && !"".equals(registerTimeBeginString)){
			setRegisterTimeBegin(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(registerTimeBeginString));
		}
	}

	public java.util.Date getRegisterTimeEnd() {
		return registerTimeEnd;
	}

	public void setRegisterTimeEnd(java.util.Date registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
		this.registerTimeEndString = getRegisterTimeEndString();
	}

	public String getRegisterTimeEndString() {
		if (null == registerTimeEnd) {
			return null;
		}
		return this.registerTimeEndString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registerTimeEnd);
	}

	public void setRegisterTimeEndString(String registerTimeEndString) throws java.text.ParseException{
		if(null!=registerTimeEndString && !"".equals(registerTimeEndString)) {
			setRegisterTimeEnd(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(registerTimeEndString));
		}
	}
	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	public String getLinkEmail() {
		return linkEmail;
	}

	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}
	


	public TsUserQuery() {}

	public TsUserQuery(Integer id) {
		this.id = id;
	}
}