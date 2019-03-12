package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;

import java.util.List;


public interface TsUserService {

	long save(TsUser tsUser);

	long delete(TsUserQuery tsUserQuery);

	long deleteById(Integer id);

	TsUser getById(Integer id);

	TsUser getByUserName(String userName);

	List<TsUser> findByCondition(TsUserQuery tsUserQuery);

	DataTables findPage(TsUserQuery tsUserQuery);

	long countByCondition(TsUserQuery tsUserQuery);

	long update(TsUser tsUser);
}