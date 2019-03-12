package chnp.manager.mvc.dao;

import chnp.manager.base.dao.BaseDao;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsUserDao extends BaseDao<TsUser, TsUserQuery> {}