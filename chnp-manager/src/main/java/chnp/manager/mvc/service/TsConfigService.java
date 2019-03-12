package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsConfig;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsConfigQuery;
import chnp.manager.mvc.model.query.TsUserQuery;

public interface TsConfigService {

	/**<p>新增数据</p>
	 *
	 * @param tsConfig 新增对象
	 * @return 操作影响的记录数量
	 */
	Integer save(TsConfig tsConfig);

	/**<p>根据主键获取数据</p>
	 *
	 * @param id 主键
	 * @return 不存在时返回null
	 */
	TsConfig getById(Integer id);

	TsConfig getByKey(String configKey);

	DataTables findPage(TsConfigQuery query);

	int deleteById(Integer id);

	Integer update(TsConfig tsConfig);

}