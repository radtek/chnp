package chnp.common.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chnp.common.mysql.enums.JdbcType;
import chnp.common.mysql.enums.JavaType;
import chnp.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlUtils {
	private static final Logger log = LoggerFactory.getLogger(MysqlUtils.class);

	public static final String MD_TABLE_NAME = "tableName";
	public static final String MD_TABLE_CLASS_NAME = "className";
	public static final String MD_TABLE_INSTANCE_NAME = "instanceName";
	public static final String MD_TABLE_COLUMNS = "columns";
	public static final String MD_COLUMN_NAME = "name";
	public static final String MD_COLUMN_FIELD_NAME = "instanceName";
	public static final String MD_COLUMN_REMARKS = "remarks";
	public static final String MD_COLUMN_JAVA_TYPE = "javaType";
	public static final String MD_COLUMN_JDBC_TYPE = "jdbcType";
	public static final String MD_COLUMN_SIZE = "size";
	public static final String MD_COLUMN_TYPE_CODE = "typeCode";
	public static final String MD_COLUMN_TYPE = "type";
	public static final String MD_COLUMN_DECIMAL_DIGITS = "decimalDigits";
	public static final String MD_COLUMN_NULLABLE = "nullable";
	public static final String MD_COLUMN_IS_NULLABLE = "isNullable";
	public static final String MD_COLUMN_IS_AUTOINCREMENT = "isAuto";
	public static final String MD_COLUMN_ORDINAL_POSITION = "ordinalPosition";
	public static final String MD_COLUMN_CHAR_OCTET_LENGTH = "charOctetLength";
	public static final String MD_COLUMN_COLUMN_DEF = "default";
	public static final String MD_COLUMN_NUM_PREC_RADIX = "numPrecRadix";

	private Connection conn = null;// 数据库连接实例

	private String dbHost = "localhost";
	private Integer dbPort = 3306;
	private Map<String, String> dbParams = new HashMap<>();
	private String dbUser = "root";
	private String dbPswd = "root";
	public MysqlUtils(String dbHost, Integer dbPort, Map<String, String> dbParams, String dbUser, String dbPswd) {
		this.dbHost = dbHost;
		this.dbPort = dbPort;
		this.dbParams.putAll(dbParams);
		this.dbUser = dbUser;
		this.dbPswd = dbPswd;
	}

	/**<p>获取数据库地址</p>
	 *
	 * @return 数据库地址。格式：jdbc:mysql://[host]:[port][?params]
	 */
	private String getDbUrl() {
		StringBuilder urlParams = new StringBuilder(256);
		for (String key : dbParams.keySet()) {
			urlParams.append("&").append(key).append("=").append(dbParams.get(key));
		}
		return "jdbc:mysql://" + dbHost + ":" + dbPort + (0 < dbParams.size() ? "?" + urlParams.substring(1) : "");
	}

	/**<p>获取数据库连接</p>
	 *
	 * @return 数据库连接
	 * @throws SQLException 连接获取失败
	 */
	public Connection getConnection() throws SQLException {
		if (null == this.conn || this.conn.isClosed()) {
			conn = DriverManager.getConnection(getDbUrl(), dbUser, dbPswd);
		}
		log.info("数据库连接已建立！");
		return conn;
	}

	/**<p>判断数据库是否可用</p>
	 *
	 * @return true - 可用
	 * @throws SQLException 数据库连接失败
	 */
	public boolean isAvailable() throws SQLException {
		Connection c = getConnection();
		if (null != c && !c.isClosed()) {
			c.close();
			return true;
		}
		return false;
	}

	/**<p>关闭数据库连接</p>
	 *
	 * @throws SQLException 连接关闭失败
	 */
	public void close() throws SQLException {
		if (!conn.isClosed()) {
			this.conn.close();
		}
		log.info("数据库连接已关闭！");
	}

	/**<p>获取指定数据库的元数据</p>
	 *
	 * @param dbName 数据库名称
	 * @param tables 待获取的表集合
	 * @return 指定表的元数据集合
	 * @throws SQLException 数据库连接错误
	 */
	public List<Map<String, Object>> getMetaData(String dbName, String... tables) throws SQLException {
		List<Map<String, Object>> dataSet = new ArrayList<>();
		Connection c = getConnection();

		DatabaseMetaData dbmd = c.getMetaData();
		if(null == dbmd) throw new RuntimeException("未获取到指定数据库的元数据！");

		// 遍历获取每一张表的元数据
		for(String table : tables) {
			log.info("开始获取{}表的信息...", table);
			Map<String, Object> tableData = new HashMap<>();

			// 第1步：获取当前表的主键。这个集合在第2步用于标记字段的isKey
			ResultSet rs = dbmd.getPrimaryKeys(dbName, null, table);
			List<String> keys = new ArrayList<>();
			while (rs.next()) {
				keys.add(rs.getString("COLUMN_NAME"));
			}

			// 第2步：获取字段元数据。部分字段经过翻译
			List<Map<String, Object>> columns = new ArrayList<>();
			rs = dbmd.getColumns(dbName, "%", table, "%");
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();// 字段信息集合

				if (!keys.isEmpty() && keys.contains(rs.getString("COLUMN_NAME"))) {// 主键标志：true
					map.put("isKey", true);
				}else map.put("isKey", false);

				map.put(MD_COLUMN_NAME, rs.getString("COLUMN_NAME"));// 字段名称：user_id
				map.put("normalName", StringUtils.upperCamelCase(rs.getString("COLUMN_NAME")));// 标准名称：UserId
				map.put("aliasName", StringUtils.toAliasName(rs.getString("COLUMN_NAME")));// 字段别名：ALIAS_USER_ID
				map.put("authName", StringUtils.toAuthName(rs.getString("COLUMN_NAME")));// 权限名称：userid
				map.put(MD_COLUMN_FIELD_NAME, StringUtils.lowerCamelCase(rs.getString("COLUMN_NAME")));// 属性名称：userId

				map.put(MD_COLUMN_REMARKS, rs.getString("REMARKS"));// 字段注解：用户ID
				map.put(MD_COLUMN_JAVA_TYPE, JavaType.valueOf(rs.getString("TYPE_NAME")));// Java数据类型名称：java.lang.Integer
				map.put(MD_COLUMN_JDBC_TYPE, JdbcType.valueOf(rs.getString("TYPE_NAME")));// JDBC数据类型名称：INTEGER
				map.put(MD_COLUMN_SIZE, rs.getInt("COLUMN_SIZE"));// 字段大小

				map.put(MD_COLUMN_TYPE_CODE, rs.getInt("DATA_TYPE"));// 字段类型代码
				map.put(MD_COLUMN_TYPE, rs.getString("TYPE_NAME"));// 字段类型名称
				map.put(MD_COLUMN_DECIMAL_DIGITS, rs.getInt("DECIMAL_DIGITS"));// 字段小数精度
				map.put(MD_COLUMN_NULLABLE, rs.getInt("NULLABLE"));
				map.put(MD_COLUMN_NUM_PREC_RADIX, rs.getInt("NUM_PREC_RADIX"));
				map.put(MD_COLUMN_COLUMN_DEF, rs.getString("COLUMN_DEF"));// 字段默认值
				map.put(MD_COLUMN_CHAR_OCTET_LENGTH, rs.getInt("CHAR_OCTET_LENGTH"));
				map.put(MD_COLUMN_ORDINAL_POSITION, rs.getInt("ORDINAL_POSITION"));
				map.put(MD_COLUMN_IS_NULLABLE, rs.getString("IS_NULLABLE"));
				map.put(MD_COLUMN_IS_AUTOINCREMENT, rs.getString("IS_AUTOINCREMENT"));

				columns.add(map);
			}

			tableData.put(MD_TABLE_NAME, table);// 保存当前表名
			tableData.put(MD_TABLE_CLASS_NAME, StringUtils.upperCamelCase(table));// 保存当前表的类名
			tableData.put(MD_TABLE_INSTANCE_NAME, StringUtils.lowerCamelCase(table));// 保存当前表的实例名
			tableData.put("authName", StringUtils.toAuthName(table));// 保存当前表的权限名
			tableData.put(MD_TABLE_COLUMNS, columns);// 保存当前表的字段信息
			//tableData.setBasePackage(config.getBasePackage());

			dataSet.add(tableData);// 保存当前表信息
		}

		return dataSet;
	}
	
}