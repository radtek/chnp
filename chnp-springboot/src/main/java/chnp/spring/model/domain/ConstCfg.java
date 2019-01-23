package chnp.spring.model.domain;

/**
 * @author chngzhen@outlook.com
 * @date 2019/1/17
 */
public class ConstCfg {

	private Integer id;
	private String constName;
	private String constKey;
	private String constValue;
	private String constDesc;
	private String groupName;
	private Integer sort;
	private Integer rootPrivilege;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConstName() {
		return constName;
	}

	public void setConstName(String constName) {
		this.constName = constName;
	}

	public String getConstKey() {
		return constKey;
	}

	public void setConstKey(String constKey) {
		this.constKey = constKey;
	}

	public String getConstValue() {
		return constValue;
	}

	public void setConstValue(String constValue) {
		this.constValue = constValue;
	}

	public String getConstDesc() {
		return constDesc;
	}

	public void setConstDesc(String constDesc) {
		this.constDesc = constDesc;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRootPrivilege() {
		return rootPrivilege;
	}

	public void setRootPrivilege(Integer rootPrivilege) {
		this.rootPrivilege = rootPrivilege;
	}
}