package chnp.manager.mvc.dao;

import chnp.manager.base.dao.BaseDao;
import chnp.manager.mvc.model.domain.TsData;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.model.query.TsDataQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsDataDao extends BaseDao<TsData, TsDataQuery> {

	/**<p>根据键名获取字典列表。</p>
	 *
	 * @param keyName 键名
	 * @return 字典列表
	 */
    List<TsDataItem> findItemsByKey(String keyName);

}