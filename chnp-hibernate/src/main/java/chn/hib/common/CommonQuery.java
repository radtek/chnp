package chn.hib.common;

public class CommonQuery {

	private String search;
	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}

	private String additionalColumns = "";
	public String getAdditionalColumns() {
		return additionalColumns;
	}

	private void addColumns(String columns) {
		if (null != columns && !"".equals(columns.trim())) {
			this.additionalColumns += ", " + columns;
		}
	}

	private String additionalFilters = "";
	public String getAdditionalFilters() {
		return additionalFilters;
	}

	/**<p>添加额外的过滤条件。</p>
	 * <pre>
	 * query.addAdditionalFilters("id>5");
	 * </pre>
	 *
	 * @param filter 过滤条件。不需要补充{@code and | or}
	 */
	public void addAdditionalFilters(String filter) {
		if (null != additionalFilters && !"".equals(additionalFilters.trim())) {
			this.additionalFilters += " and " + filter;
		}else this.additionalFilters = filter;
	}
}