package chnp.manager.mvc.dao;

import chnp.manager.mvc.model.domain.TsKey;
import chnp.manager.mvc.model.query.TsKeyQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsKeyDao {

	/**<p>插入操作</p>
	 *
	 * @param tsKey 操作对象
	 * @return 操作结果：成功返回插入的数量
	 */
    Integer save(TsKey tsKey);

	/**<p>条件删除</p>
	 *
	 * @param query 删除条件
	 * @return 删除结果：成功返回删除的数量
	 */
    Long delete(TsKeyQuery query);


	/**<p>条件查询（单条）</p>
	 *
	 * @param query 查询条件
	 * @return 查询结果
	 */
    TsKey getByCondition(TsKeyQuery query);

	/**<p>条件查询</p>
	 *
	 * @param query 查询条件
	 * @return 查询结果
	 */
    List<TsKey> findByCondition(TsKeyQuery query);

	/**<p>条件统计</p>
	 *
	 * @param query 统计条件
	 * @return 统计结果
	 */
    Long countByCondition(TsKeyQuery query);

	/**<p>更新操作</p>
	 *
	 * @param tsKey 更新对象
	 * @return 操作结果：成功返回更新的数量
	 */
    Integer updateById(TsKey tsKey);
}