package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsUserDao;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;
import chnp.manager.mvc.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public int deleteById(Integer id) {
		TsUserQuery query = new TsUserQuery();
		query.setId(id);
		return tsUserDao.deleteByCondition(query);
	}

	@Override
	public DataTables findPage(TsUserQuery query) {
		DataTables dataTables = new DataTables();

		List<TsUser> tsUsers = tsUserDao.findPage(query);
		if (null == tsUsers) tsUsers = new ArrayList<>();
		dataTables.setData(tsUsers);
		dataTables.setRecordsFiltered(tsUsers.size());

		dataTables.setRecordsTotal(tsUserDao.countByCondition(query));

		return dataTables;
	}
}