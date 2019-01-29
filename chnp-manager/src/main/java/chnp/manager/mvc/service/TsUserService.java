package chnp.manager.mvc.service;

import chnp.manager.mvc.model.domain.TsUser;

public interface TsUserService {

	TsUser getById(Integer id);

	TsUser getByUserName(String userName);

}