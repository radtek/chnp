package chnp.manager.mvc.service;

import chnp.manager.mvc.model.domain.TsModule;
import chnp.manager.mvc.model.query.TsModuleQuery;

import java.util.List;
import java.util.Map;

public interface TsModuleService {

	Integer save(TsModule entity);

	TsModule getById(Integer id);

	List<TsModule> findAllByCondition(TsModuleQuery query);

	List<TsModule> findMenu(TsModuleQuery query);

	Long deleteById(Integer id);

	/**<p>深度删除。删除指定模块及其子孙模块。</p>
	 *
	 * @param id 根模块ID
	 * @return 影响的行数
	 */
	Long deepdelete(Integer id);

	/**<p>获取树型下拉列表</p>
	 *
	 * @param query 查询条件
	 * @return 树型下拉列表
	 */
	List<Map<String, Object>> getSelectTree(TsModuleQuery query);

	Integer update(TsModule tsModule);

}