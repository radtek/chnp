package chnp.manager.mvc.dao;

import chnp.manager.base.dao.BaseDao;
import chnp.manager.mvc.model.domain.TsRole;
import chnp.manager.mvc.model.query.TsRoleQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsRoleDao extends BaseDao<TsRole, TsRoleQuery> {}