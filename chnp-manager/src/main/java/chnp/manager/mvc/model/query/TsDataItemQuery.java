package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;

public class TsDataItemQuery extends PaginationQuery {

	private Integer id;
	
	private String itemKey;
	
	private String itemValue;
	
	private String itemDesc;
	
	private Integer itemSort;
	
	private Integer dataId;

	
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

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getItemSort() {
		return itemSort;
	}

	public void setItemSort(Integer itemSort) {
		this.itemSort = itemSort;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public TsDataItemQuery() {

	}
}
