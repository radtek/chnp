package chnp.manager.mvc.model.domain;


public class TsUserRole {

	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_ROLE_ID = "角色ID";

	private Integer id;
	
	private Integer userId;
	
	private Integer roleId;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	
	// Constructor
	public TsUserRole() {}

	public TsUserRole(Integer id) {
		this.id = id;
	}

}