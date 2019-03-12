package chnp.manager.mvc.model.domain;

import chnp.manager.common.entity.AbstractEntity;

public class TsData extends AbstractEntity {


	// Properties
	private Integer id;
	
	private String dataKey;
	
	private String dataName;
	
	private String dataDesc;
	
	
	
	// Getter/Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataDesc() {
		return dataDesc;
	}

	public void setDataDesc(String dataDesc) {
		this.dataDesc = dataDesc;
	}

	// Constructor
	public TsData() {}

	public TsData(Integer id) {
		this.id = id;
	}


}
