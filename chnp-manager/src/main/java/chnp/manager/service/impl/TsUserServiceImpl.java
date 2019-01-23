package chnp.manager.service.impl;

import chnp.manager.dao.TsUserDao;
import chnp.manager.model.domain.TsUser;
import chnp.manager.model.query.TsUserQuery;
import chnp.manager.service.TsUserService;
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
}