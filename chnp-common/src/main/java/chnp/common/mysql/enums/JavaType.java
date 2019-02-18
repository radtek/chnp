package chnp.common.mysql.enums;

public enum JavaType {

	INT("java.lang.Integer"),
	VARCHAR("java.lang.String"),
	TIMESTAMP("java.util.Date"),
	DATETIME("java.util.Date"),
	LONGTEXT("java.lang.String"),
	BIGINT("java.lang.Long"),
	DECIMAL("java.lang.Double"),
	DOUBLE("java.lang.Double"),
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