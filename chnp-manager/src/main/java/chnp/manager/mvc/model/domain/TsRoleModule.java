package chnp.manager.mvc.model.domain;


public class TsRoleModule {

	public static final String ALIAS_ID = "记录ID";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_MODULE_ID = "模块ID";

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
	
	
	
	// Constructor
	public TsRoleModule() {}

	public TsRoleModule(Integer id) {
		this.id = id;
	}

}