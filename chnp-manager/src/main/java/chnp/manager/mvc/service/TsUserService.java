package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;

import java.util.List;

public interface TsUserService {

	TsUser getById(Integer id);

	TsUser getByUserName(String userName);

	DataTables findPage(TsUserQuery query);

	int deleteById(Integer id);

}