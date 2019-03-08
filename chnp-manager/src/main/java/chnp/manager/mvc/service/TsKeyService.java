package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsKey;
import chnp.manager.mvc.model.query.TsKeyQuery;

import java.util.List;

public interface TsKeyService {

	Integer save(TsKey tsKey);

	Long deleteById(Integer id);

	Long delete(TsKeyQuery tsKeyQuery);

	TsKey getById(Integer id);

	TsKey getByCondition(TsKeyQuery tsKeyQuery);

	List<TsKey> findByCondition(TsKeyQuery tsKeyQuery);

	DataTables findPage(TsKeyQuery query);

	Long countByCondition(TsKeyQuery tsKeyQuery);

	Integer update(TsKey tsKey);
}