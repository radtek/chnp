package chnp.manager.dao;

import chnp.manager.model.domain.TsUser;
import chnp.manager.model.query.TsUserQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsUserDao {

	TsUser getByCondition(TsUserQuery query);

}