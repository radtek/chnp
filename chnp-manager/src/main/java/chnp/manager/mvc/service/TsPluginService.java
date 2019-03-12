package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsPlugin;
import chnp.manager.mvc.model.query.TsPluginQuery;

import java.util.List;


public interface TsPluginService {

	long save(TsPlugin tsPlugin);

	long delete(TsPluginQuery tsPluginQuery);

	long deleteById(Integer id);

	TsPlugin getById(Integer id);

	TsPlugin getByCondition(TsPluginQuery tsPluginQuery);

	List<TsPlugin> findByCondition(TsPluginQuery tsPluginQuery);

	DataTables findPage(TsPluginQuery tsPluginQuery);

	long countByCondition(TsPluginQuery tsPluginQuery);

	long update(TsPlugin tsPlugin);
}