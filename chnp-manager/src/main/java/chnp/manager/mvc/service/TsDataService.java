package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsData;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.model.query.TsDataQuery;

import java.util.List;

public interface TsDataService {

	/**<p>保存数据</p>
	 *
	 * @param tsData 数据
	 * @return 影响的行数
	 */
	long save(TsData tsData);

	/**<p>删除数据字典。该操作会删除数据字典下所有的键值对。</p>
	 *
	 * @param id 主键
	 * @return 影响的行数
	 */
	long delData(Integer id);

	/**<p>根据主键获取数据</p>
	 *
	 * @param id 主键
	 * @return 数据对象
	 */
	TsData getById(Integer id);

	/**<p>分页查询数据。</p>
	 *
	 * @param query 查询条件
	 * @return 分页数据
	 */
	DataTables findPage(TsDataQuery query);

	/**<p>更新数据</p>
	 *
	 * @param tsData 数据
	 * @return 影响的行数
	 */
	long update(TsData tsData);

	/**<p>根据字典名查询键值集合</p>
	 *
	 * @param keyName 字典名
	 * @return 键值集合
	 */
	List<TsDataItem> findValuesByKey(String keyName);
}