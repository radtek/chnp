package chnp.manager.mvc.dao;

import chnp.manager.base.dao.BaseDao;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.model.query.TsDataItemQuery;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TsDataItemDao extends BaseDao<TsDataItem, TsDataItemQuery> {

	/**<p>关联查询</p>
	 *
	 * @param query 查询条件
	 * @return 实体对象
	 */
	TsDataItem getByAssociation(TsDataItemQuery query);

}