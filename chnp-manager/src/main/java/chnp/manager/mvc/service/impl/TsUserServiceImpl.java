package chnp.manager.mvc.service.impl;

import chnp.manager.mvc.dao.TsUserDao;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;
import chnp.manager.mvc.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TsUserServiceImpl implements TsUserService {

	@Autowired
	private TsUserDao tsUserDao;

	@Override
	public TsUser getById(Integer id) {
		TsUserQuery query = new TsUserQuery();
		query.setId(id);
		return tsUserDao.getByCondition(query);
	}

	@Override
	public TsUser getByUserName(String userName) {
		TsUserQuery query = new TsUserQuery();
		query.setUserName(userName);
		return tsUserDao.getByCondition(query);
	}
}