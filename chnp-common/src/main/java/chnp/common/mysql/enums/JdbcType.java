package chnp.common.mysql.enums;

public enum JdbcType {

	LONGBLOB("LONGBLOB"),
	INT("INTEGER"),
	VARCHAR("VARCHAR"),
	BIT("BIT"),
	TIMESTAMP("TIMESTAMP"),
	DATETIME("DATETIME"),
	DATE("DATE"),
	TIME("TIME"),
	LONGTEXT("LONGVARCHAR"),
	BIGINT("BIGINT"),
	DECIMAL("DOUBLE"),
	DOUBLE("DOUBLE"),
	TEXT("VARCAHR"),
	SMALLINT("INTEGER"),
	TINYINT("INTEGER");
	
	
	private String value = "VARCHAR";
	
	private JdbcType(String dbTypeName) {
		this.value = dbTypeName;
	}

	public String toString() {
		return this.value;
	}
	
}