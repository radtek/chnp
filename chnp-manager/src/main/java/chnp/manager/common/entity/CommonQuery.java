package chnp.manager.common.entity;

public abstract class CommonQuery {

	private String additionalColumns;
	public String getAdditionalColumns() {
		return additionalColumns;
	}

	public void setAdditionalColumns(String additionalColumns) {
		this.additionalColumns = additionalColumns;
	}

	private String additionalTables;
	public String getAdditionalTables() {
		return additionalTables;
	}

	public void setAdditionalTables(String additionalTables) {
		this.additionalTables = additionalTables;
	}

	private String filterCondition;
	public String getFilterCondition() {
		return filterCondition;
	}

	public void setFilterCondition(String filterCondition) {
		this.filterCondition = filterCondition;
	}

	private String orderCondition;
	public String getOrderCondition() {
		return orderCondition;
	}

	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}
}