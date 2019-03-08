package chnp.manager.mvc.model.domain;

import chnp.manager.common.entity.AbstractEntity;

public class TsKey extends AbstractEntity {

	// Alias
	public static final String ALIAS_ID = "键ID";
	public static final String ALIAS_NAME = "键名";
	public static final String ALIAS_CHINESE = "中文名";
	public static final String ALIAS_DESCR = "描述";


	// Properties
	private Integer id;
	
	private String name;
	
	private String chinese;
	
	private String descr;
	
	
	
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
	
	
	
	// Constructor
	public TsKey() {
		
	}
}
