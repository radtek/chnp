package chnp.manager.mvc.service;

import chnp.manager.mvc.model.domain.TsModule;
import chnp.manager.mvc.model.query.TsModuleQuery;

import java.util.List;

public interface TsModuleService {

	TsModule getById(Integer id);

	List<TsModule> findAllByCondition(TsModuleQuery query);

	List<TsModule> findMenu(TsModuleQuery query);

	int deleteById(Integer id);

}