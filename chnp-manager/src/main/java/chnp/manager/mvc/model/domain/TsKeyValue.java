package chnp.manager.mvc.model.domain;

import chnp.manager.common.entity.AbstractEntity;

public class TsKeyValue extends AbstractEntity {

	// Alias
	public static final String ALIAS_ID = "值ID";
	public static final String ALIAS_NAME = "值";
	public static final String ALIAS_CHINESE = "中文名";
	public static final String ALIAS_DESCR = "描述";
	public static final String ALIAS_SORT = "排序";
	public static final String ALIAS_KEY_ID = "键ID";


	// Properties
	private Integer id;
	
	private String name;
	
	private String chinese;
	
	private String descr;
	
	private Integer sort;
	
	private Integer keyId;
	
	
	
	// Getter/Setter
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
	
	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}
	
	private TsKey tsKey;

	public TsKey getTsKey() {
		return tsKey;
	}

	public void setTsKey(TsKey tsKey) {
		this.tsKey = tsKey;
	}

	// Constructor
	public TsKeyValue() {
		
	}
}
