package chnp.manager.common.entity;

import java.util.List;
import java.util.Map;

public abstract class PaginationQuery extends CommonQuery {

	/**<p>搜索字段</p>
	 * <p>
	 *     一般用于多字段模糊搜索。
	 * </p>
	 */
	private String search;

	/**<p>请求标识</p>
	 * <p>
	 *     确保原值返回。
	 * </p>
	 *
	 */
	private Integer draw;

	/**<p>起始下标</p>
	 * <p>
	 *     从0开始，每页第一条数据的下标。
	 * </p>
	 *
	 */
	private Integer start = 0;

	/**<p>页长</p>
	 * <p>
	 *     每页的数据条数。
	 * </p>
	 *
	 */
	private Integer length = 10;

	private List<Map<String, String>> order;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setOrder(List<Map<String, String>> order) {
		this.order = order;
	}

	public List<Map<String, String>> getOrder() {
		return order;
	}
}