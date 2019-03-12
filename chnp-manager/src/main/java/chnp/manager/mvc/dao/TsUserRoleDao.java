package chnp.manager.mvc.dao;

import chnp.manager.base.dao.BaseDao;
import chnp.manager.mvc.model.domain.TsUserRole;
import chnp.manager.mvc.model.query.TsUserRoleQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsUserRoleDao extends BaseDao<TsUserRole, TsUserRoleQuery> {}