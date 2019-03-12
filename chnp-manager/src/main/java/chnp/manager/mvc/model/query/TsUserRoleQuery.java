package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;


public class TsUserRoleQuery extends PaginationQuery{

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
	


	public TsUserRoleQuery() {}

	public TsUserRoleQuery(Integer id) {
		this.id = id;
	}
}