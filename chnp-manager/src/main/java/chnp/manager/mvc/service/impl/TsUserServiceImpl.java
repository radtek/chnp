package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsUserDao;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsUserQuery;
import chnp.manager.mvc.service.TsUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
	public long save(TsUser tsUser) {
		return tsUserDao.insert(tsUser);
	}

	@Override
	public long delete(TsUserQuery tsUserQuery) {
		return tsUserDao.delete(tsUserQuery);
	}

	@Override
	public long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsUserQuery tsUserQuery = new TsUserQuery();
		tsUserQuery.setId(id);
		return tsUserDao.delete(tsUserQuery);
	}

	@Override
	public TsUser getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsUserQuery tsUserQuery = new TsUserQuery();
		tsUserQuery.setId(id);
		return tsUserDao.getByCondition(tsUserQuery);
	}

	@Override
	public TsUser getByUserName(String userName) {
		TsUserQuery query = new TsUserQuery();
		query.setUserName(userName);
		return tsUserDao.getByCondition(query);
	}

	@Override
	public List<TsUser> findByCondition(TsUserQuery tsUserQuery) {
		List<TsUser> results = tsUserDao.findByCondition(tsUserQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(TsUserQuery tsUserQuery) {
		DataTables dataTables = new DataTables();
		dataTables.setDraw(tsUserQuery.getDraw());

		Page page = PageHelper.startPage(tsUserQuery.getPageNo(), tsUserQuery.getLength());
		List<TsUser> tsUsers = tsUserDao.findByCondition(tsUserQuery);
		if (null == tsUsers) tsUsers = new ArrayList<>();
		dataTables.setData(tsUsers);

		dataTables.setRecordsFiltered(page.getTotal());
		dataTables.setRecordsTotal(page.getTotal());

		return dataTables;
	}

	@Override
	public long countByCondition(TsUserQuery tsUserQuery) {
		return tsUserDao.countByCondition(tsUserQuery);
	}

	@Override
	public long update(TsUser tsUser) {
		if(null == tsUser.getId()) throw new NullPointerException("主键不能为空");
		return tsUserDao.update(tsUser);
	}
}