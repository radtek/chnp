package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;


public class TsRoleModuleQuery extends PaginationQuery{

	private Integer id;
	
	private Integer roleId;
	
	private Integer moduleId;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	


	public TsRoleModuleQuery() {}

	public TsRoleModuleQuery(Integer id) {
		this.id = id;
	}
}