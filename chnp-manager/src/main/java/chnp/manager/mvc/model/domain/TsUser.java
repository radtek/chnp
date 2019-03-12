package chnp.manager.mvc.model.domain;


public class TsUser {

	public static final String ALIAS_ID = "用户ID";
	public static final String ALIAS_USER_NAME = "登陆账号";
	public static final String ALIAS_USER_PSWD = "登陆密码";
	public static final String ALIAS_REGISTER_TIME = "注册时间";
	public static final String ALIAS_USER_NICK = "用户昵称";
	public static final String ALIAS_LINK_EMAIL = "对外邮箱";

	private Integer id;
	
	private String userName;
	
	private String userPswd;
	
	private java.util.Date registerTime;
	
	private String registerTimeString;
	
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
	
	public java.util.Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
		this.registerTimeString = getRegisterTimeString();
	}

	public String getRegisterTimeString() {
		if (null == registerTime) {
			return null;
		}
		return this.registerTimeString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registerTime);
	}

	public void setRegisterTimeString(String registerTimeString) throws java.text.ParseException {
		if (null !=  registerTimeString && !"".equals( registerTimeString)) {
			setRegisterTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(registerTimeString));
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
	
	
	
	// Constructor
	public TsUser() {}

	public TsUser(Integer id) {
		this.id = id;
	}

}