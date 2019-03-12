package chnp.manager.mvc.model.domain;

import chnp.manager.common.entity.AbstractEntity;

public class TsDataItem extends AbstractEntity {

	// Properties
	private Integer id;
	
	private String itemKey;
	
	private String itemValue;
	
	private String itemDesc;
	
	private Integer itemSort;
	
	private Integer dataId;
	
	
	
	// Getter/Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Integer getItemSort() {
		return itemSort;
	}

	public void setItemSort(Integer itemSort) {
		this.itemSort = itemSort;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}




	private TsData tsData;

	public TsData getTsData() {
		return tsData;
	}

	public void setTsData(TsData tsData) {
		this.tsData = tsData;
	}

	// Constructor
	public TsDataItem() {}

	public TsDataItem(Integer id) {
		this.id = id;
	}
}
