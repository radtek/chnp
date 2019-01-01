package chn.hib.common;

public class CommonQuery {

	private String additionalColumns = "";
	public String getAdditionalColumns() {
		return additionalColumns;
	}

	private void addColumns(String columns) {
		if (null != columns && !"".equals(columns.trim())) {
			this.additionalColumns += ", " + columns;
		}
	}

}