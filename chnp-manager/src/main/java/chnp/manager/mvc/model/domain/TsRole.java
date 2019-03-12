package chnp.manager.mvc.model.domain;


import chnp.manager.annotatoion.Column;

public class TsRole {

	public static final String ALIAS_ID = "角色ID";
	public static final String ALIAS_NAME = "中文名称";
	public static final String ALIAS_ORG_ID = "隶属组织";
	public static final String ALIAS_STATUS = "角色状态";

	private Integer id;
	
	private String name;
	
	private Integer orgId;

	@Column(dataType = "tsRoleStatus")
	private Integer status;
	
	
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
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	// Constructor
	public TsRole() {}

	public TsRole(Integer id) {
		this.id = id;
	}

}