package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsConfig;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsConfigQuery;
import chnp.manager.mvc.model.query.TsUserQuery;

public interface TsConfigService {

	Integer save(TsConfig tsConfig);

	TsConfig getById(Integer id);

	TsConfig getByKey(String configKey);

	DataTables findPage(TsConfigQuery query);

	int deleteById(Integer id);

	Integer update(TsConfig tsConfig);

}