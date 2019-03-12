package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.model.query.TsDataItemQuery;

import java.util.List;


public interface TsDataItemService {

	long save(TsDataItem tsDataItem);

	Long delete(TsDataItemQuery tsDataItemQuery);

	Long deleteById(Integer id);

	TsDataItem view(Integer id);

	TsDataItem getById(Integer id);

	TsDataItem getByCondition(TsDataItemQuery tsDataItemQuery);

	List<TsDataItem> findByCondition(TsDataItemQuery tsDataItemQuery);

	DataTables findPage(TsDataItemQuery tsDataItemQuery);

	Long countByCondition(TsDataItemQuery tsDataItemQuery);

	long update(TsDataItem tsDataItem);
}