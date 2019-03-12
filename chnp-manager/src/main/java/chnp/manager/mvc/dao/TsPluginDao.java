package chnp.manager.mvc.dao;

import chnp.manager.base.dao.BaseDao;
import chnp.manager.mvc.model.domain.TsPlugin;
import chnp.manager.mvc.model.query.TsPluginQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsPluginDao extends BaseDao<TsPlugin, TsPluginQuery> {}