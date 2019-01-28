package chnp.manager.service;

import chnp.manager.model.domain.TsModule;
import chnp.manager.model.query.TsModuleQuery;

import java.util.List;

public interface TsModuleService {

	TsModule getById(Integer id);

	List<TsModule> findAllByCondition(TsModuleQuery query);

	List<TsModule> findMenu(TsModuleQuery query);

}