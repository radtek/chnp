package chnp.hsql.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HSQLUtils {

	private static String dbName = "acs";
	private static String username = "acs";
	private static String password = "acs";

	public HSQLUtils() throws ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
	}

	public void setDbName(String dbName) {
		HSQLUtils.dbName = dbName;
	}

	public void setUsername(String username) {
		HSQLUtils.username = username;
	}

	public void setPassword(String password) {
		HSQLUtils.password = password;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:hsqldb:mem:" + dbName, username, password);
	}

	/**<p>执行SQL语句。</p>
	 * <p>
	 *     该方法支持几乎所有的SQL语句，但没有返回执行结果。因此建议执行不注重结果的SQL语句，例如建表。
	 * </p>
	 *
	 * @param sql SQL语句。不支持占位符。
	 * @throws SQLException
	 */
	public void execute(String sql) throws SQLException {
		try (Connection conn = getConnection();
			 Statement s = conn.createStatement()) {

			s.execute(sql);

		}
	}

	/**<p>数据库更新操作，支持DELETE、INSERT和UPDATE。</p>
	 *
	 * @param sql SQL语句。占位符是英文‘?’。
	 * @param params 参数集合
	 * @return 影响的行数。为0时表示操作失败。
	 * @throws SQLException
	 */
	public int update(String sql, Object... params) throws SQLException {
		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			for (int i=0; i< params.length; i++) ps.setObject(i + 1, params[i]);
			return ps.executeUpdate();
		}
	}

	/**<p>数据库更新操作，支持DELETE、INSERT和UPDATE。</p>
	 *
	 * @param sql SQL语句。占位符是英文‘?’。
	 * @param params 参数集合
	 * @return 影响的行数。为0时表示操作失败。
	 * @throws SQLException
	 */
	public int update(String sql, List<Object> params) throws SQLException {
		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			for (int i=0; i< params.size(); i++) ps.setObject(i + 1, params.get(i));
			return ps.executeUpdate();
		}
	}

	/**<p>数据库批量更新操作，包括批量INSERT和UPDATE。</p>
	 *
	 * @param sql SQL语句。占位符是英文‘?’。
	 * @param params 数据集
	 * @return 操作结果。为0时表示操作失败。
	 * @throws SQLException
	 */
	public int[] batchUpdate(String sql, List<Object[]> params) throws SQLException {
		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			for (int i=0; i<params.size(); i++) {
				Object[] param = params.get(i);
				for (int j=0; j<param.length; j++) {
					ps.setObject(j + 1, param[j]);
				}
				ps.addBatch();
			}
			return ps.executeBatch();
		}
	}

	/**<p>数据库查询操作。</p>
	 *
	 * @param sql SQL语句。占位符是英文‘?’。
	 * @param params 参数集合
	 * @return 查询结果集合
	 * @throws SQLException
	 */
	public List<Map<String, Object>> find(String sql, Object... params) throws SQLException {
		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			for (int i=0; i< params.length; i++)
				ps.setObject(i + 1, params[i]);

			ResultSet rs = ps.executeQuery();

			ResultSetMetaData metaData = ps.getMetaData();
			List<Map<String, Object>> results = new ArrayList<>();
			while(rs.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i=1; i<=metaData.getColumnCount(); i++)
					row.put(metaData.getColumnName(i).toLowerCase(), rs.getObject(i));
				results.add(row);
			}
			return results;
		}
	}

	public Map<String, Object> get(String sql, Object... params) throws SQLException {
		List<Map<String, Object>> results = find(sql, params);
		return results.size() > 0 ? results.get(0) : null;
	}

}