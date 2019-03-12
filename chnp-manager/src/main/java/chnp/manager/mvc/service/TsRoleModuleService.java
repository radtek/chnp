package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsRoleModule;
import chnp.manager.mvc.model.query.TsRoleModuleQuery;

import java.util.List;


public interface TsRoleModuleService {

	long save(TsRoleModule tsRoleModule);

	long delete(TsRoleModuleQuery tsRoleModuleQuery);

	long deleteById(Integer id);

	TsRoleModule getById(Integer id);

	TsRoleModule getByCondition(TsRoleModuleQuery tsRoleModuleQuery);

	List<TsRoleModule> findByCondition(TsRoleModuleQuery tsRoleModuleQuery);

	DataTables findPage(TsRoleModuleQuery tsRoleModuleQuery);

	long countByCondition(TsRoleModuleQuery tsRoleModuleQuery);

	long update(TsRoleModule tsRoleModule);
}