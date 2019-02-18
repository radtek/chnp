package chnp.common.mysql.enums;

public enum JdbcType {
	
	INT("INTEGER"),
	VARCHAR("VARCHAR"),
	DATETIME("TIMESTAMP"),
	LONGTEXT("LONGVARCHAR"),
	BIGINT("BIGINT"),
	DECIMAL("DOUBLE"),
	DOUBLE("DOUBLE"),
	TEXT("VARCAHR"),
	SMALLINT("INTEGER");
	
	
	private String value = "VARCHAR";
	
	private JdbcType(String dbTypeName) {
		this.value = dbTypeName;
	}

	public String toString() {
		return this.value;
	}
	
}