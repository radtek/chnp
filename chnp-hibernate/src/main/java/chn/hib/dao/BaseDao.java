package chn.hib.dao;

import chn.hib.common.CommonQuery;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**<p>Hibernate基础Dao接口</p>
 * <p>基础Dao提供了三种操作数据库的方式：Hibernate现有的API、HQL和SQL。</p>
 * <ul>
 *     <li>Hibernate的API：该类方法不带任何{@code In*}后缀；</li>
 *     <li>HQL：该类方法带有{@code InHql}后缀；</li>
 *     <li>SQL：该类方法带有{@code InSql}后缀。</li>
 * </ul>
 *
 * @CreateBy chngzhen@outlook.com
 * @CreateOn 2017-12-08
 * @UpdateBy chngzhen@outlook.com
 * @UpdateOn 2017-12-08
 *
 * @param <E> 实体类型
 */
public interface BaseDao<E> {

	/**<p>保存数据。该方法由Hibernate提供。</p>
	 *
	 * @param entity 实体对象
	 * @return 主键
	 */
	Serializable insert(E entity);

	/**<p>删除数据。该方法由Hibernate提供。</p>
	 *
	 * @param entity 实体对象。主键不能为空。
	 */
	void delete(E entity);

	/**<p>根据主键获取数据。该方法由Hibernate提供。</p>
	 *
	 * @param id 主键
	 * @return 实体对象
	 */
	E get(Serializable id);

	/**<p>根据主键更新所有字段。该方法由Hibernate提供，如果字段为null，会覆盖数据库中的字段。</p>
	 *
	 * @param entity 实体对象。主键不能为空
	 */
	void update(E entity);

	/**<p>查询所有数据。</p>
	 *
	 */
	List<E> findAllInHql();

	/**<p>根据主键更新非空字段。</p>
	 *
	 * @param entity 实体对象。主键不能为空，且至少有一个不为空的更新属性
	 * @return 更新的实体数量
	 */
	int updateByIdInHql(E entity) throws Exception;

	/**<p>条件查询单条数据。</p>
	 *
	 * @param query 条件对象
	 * @return 实体对象
	 */
	E getByConditionInSql(CommonQuery query) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

	/**<p>条件查询多条数据。</p>
	 *
	 * @param query 条件对象
	 * @return 实体对象集合
	 */
	List<E> findByConditionInSql(CommonQuery query) throws IllegalArgumentException, IllegalAccessException;

	List findMapByConditionInSql(CommonQuery query) throws IllegalArgumentException, IllegalAccessException;

	/**<p>条件统计</p>
	 * <p>拼接SQL语句利用条件对象统计数据。</p>
	 * @param query 条件对象
	 * @return 满足条件的记录总数
	 */
	long countByConditionInSql(CommonQuery query) throws IllegalArgumentException, IllegalAccessException;


}