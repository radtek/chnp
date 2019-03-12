package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;

public class TsDataQuery extends PaginationQuery {

	private Integer id;
	
	private String dataKey;
	
	private String dataName;
	
	private String dataDesc;

	
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

	public TsDataQuery() {}

	public TsDataQuery(Integer id) {
		this.id = id;
	}


}
