package chnp.common.mysql.enums;

public enum JavaType {

	TINYINT("Integer"),
	INT("Integer"),
	NUMERIC("Double"),
	DOUBLE("Double"),
	DECIMAL("Double"),
	CHAR("String"),
	VARCHAR("String"),
	BIT("Boolean"),
	TIMESTAMP("java.util.Date"),
	DATETIME("java.util.Date"),
	DATE("java.util.Date"),
	TIME("java.util.Date"),
	LONGTEXT("java.lang.String"),
	LONGBLOB("Object"),
	BIGINT("java.lang.Long"),
	TEXT("java.lang.String"),
	SMALLINT("java.lang.Integer");

	private String value = "java.lang.String";

	private JavaType(String typeName) {
		this.value = typeName;
	}

	public String toString() {
		return this.value;
	}

}