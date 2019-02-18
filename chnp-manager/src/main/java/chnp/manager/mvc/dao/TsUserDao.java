package chnp.manager.mvc.dao;

import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsUserDao {

	TsUser getByCondition(TsUserQuery query);

	List<TsUser> findByCondition(TsUserQuery query);

	List<TsUser> findPage(TsUserQuery query);

	Long countByCondition(TsUserQuery query);

	Integer deleteByCondition(TsUserQuery query);

}