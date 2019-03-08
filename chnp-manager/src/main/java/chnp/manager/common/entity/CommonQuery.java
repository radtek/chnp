package chnp.manager.common.entity;

import chnp.common.utils.StringUtils;

public abstract class CommonQuery {

	/**<p>自定义SELECT对象</p>
	 * <p>
	 *     可以是字段，也可以是子查询语句。
	 *
	 *     注意：必须以英文逗号开头。
	 * </p>
	 *
	 */
	private String additionalColumns;
	public String getAdditionalColumns() {
		return additionalColumns;
	}

	public void setAdditionalColumns(String additionalColumns) {
		this.additionalColumns = additionalColumns;
	}

	/**<p>自定义FROM对象</p>
	 * <p>
	 *     可以是表名，也可以是JOIN操作。
	 *
	 *     注意：是表名时必须以英文逗号开头。
	 * </p>
	 *
	 */
	private String additionalTables;
	public String getAdditionalTables() {
		return additionalTables;
	}

	public void setAdditionalTables(String additionalTables) {
		this.additionalTables = additionalTables;
	}

	/**<p>自定义WHERE条件</p>
	 * <p>
	 *     可以包含子查询，甚至可以包含排序等。
	 *
	 *     注意：不必以and开头。
	 * </p>
	 *
	 */
	private String additionalFilters;
	public String getAdditionalFilters() {
		return additionalFilters;
	}

	public void setAdditionalFilters(String additionalFilters) {
		this.additionalFilters = additionalFilters;
	}

	/**<p>自定义约束条件</p>
	 * <p>
	 *     包括GROUP BY、HAVING等
	 * </p>
	 *
	 */
	private String additionalConstrains;
	public String getAdditionalConstrains() {
		return additionalConstrains;
	}

	public void setAdditionalConstrains(String additionalConstrains) {
		this.additionalConstrains = additionalConstrains;
	}
}