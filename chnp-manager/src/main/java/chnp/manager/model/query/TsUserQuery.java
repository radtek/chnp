package chnp.manager.model.query;

import chnp.manager.common.CommonQuery;

public class TsUserQuery extends CommonQuery {

	private Integer id;
	private String userName;
	private String userPswd;

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
}