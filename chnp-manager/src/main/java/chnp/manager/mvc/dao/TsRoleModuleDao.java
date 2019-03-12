package chnp.manager.mvc.dao;

import chnp.manager.base.dao.BaseDao;
import chnp.manager.mvc.model.domain.TsRoleModule;
import chnp.manager.mvc.model.query.TsRoleModuleQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsRoleModuleDao extends BaseDao<TsRoleModule, TsRoleModuleQuery> {}