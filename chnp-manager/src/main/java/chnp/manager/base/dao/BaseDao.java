package chnp.manager.base.dao;

import java.util.List;

public interface BaseDao<E, Q> {

	/**<p>插入数据</p>
	 *
	 * @param entity 存储对象
	 * @return 影响的行数
	 */
	long insert(E entity);

	/**<p>按条件删除数据</p>
	 *
	 * @param query 查询条件
	 * @return 影响的行数
	 */
	long delete(Q query);

	/**<p>按条件获取数据</p>
	 *
	 * @param query 查询条件
	 * @return 实体对象
	 */
	E getByCondition(Q query);

	/**<p>根据条件查询数据</p>
	 *
	 * @param query 查询条件
	 * @return 查询结果
	 */
	List<E> findByCondition(Q query);

	/**<p>根据条件统计数据</p>
	 *
	 * @param query 查询条件
	 * @return 统计结果
	 */
	long countByCondition(Q query);

	/**<p>根据主键数据</p>
	 *
	 * @param entity 更新对象
	 * @return 影响的行数
	 */
	long update(E entity);

}