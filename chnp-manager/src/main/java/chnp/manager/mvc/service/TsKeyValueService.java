package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsKeyValue;
import chnp.manager.mvc.model.query.TsKeyValueQuery;

import java.util.List;


public interface TsKeyValueService {

	Integer save(TsKeyValue tsKeyValue);

	Long delete(TsKeyValueQuery tsKeyValueQuery);

	Long deleteById(Integer id);

	TsKeyValue view(Integer id);

	TsKeyValue getById(Integer id);

	TsKeyValue getByCondition(TsKeyValueQuery tsKeyValueQuery);

	List<TsKeyValue> findByCondition(TsKeyValueQuery tsKeyValueQuery);

	DataTables findPage(TsKeyValueQuery tsKeyValueQuery);

	Long countByCondition(TsKeyValueQuery tsKeyValueQuery);

	Integer update(TsKeyValue tsKeyValue);
}