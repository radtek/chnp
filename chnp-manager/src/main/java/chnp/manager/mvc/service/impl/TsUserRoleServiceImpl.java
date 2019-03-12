package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsUserRoleDao;
import chnp.manager.mvc.model.domain.TsUserRole;
import chnp.manager.mvc.model.query.TsUserRoleQuery;
import chnp.manager.mvc.service.TsUserRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TsUserRoleServiceImpl implements TsUserRoleService {

	@Autowired
	private TsUserRoleDao tsUserRoleDao;

	@Override
	public long save(TsUserRole tsUserRole) {
		return tsUserRoleDao.insert(tsUserRole);
	}

	@Override
	public long delete(TsUserRoleQuery tsUserRoleQuery) {
		return tsUserRoleDao.delete(tsUserRoleQuery);
	}

	@Override
	public long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsUserRoleQuery tsUserRoleQuery = new TsUserRoleQuery();
		tsUserRoleQuery.setId(id);
		return tsUserRoleDao.delete(tsUserRoleQuery);
	}

	@Override
	public TsUserRole getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsUserRoleQuery tsUserRoleQuery = new TsUserRoleQuery();
		tsUserRoleQuery.setId(id);
		return tsUserRoleDao.getByCondition(tsUserRoleQuery);
	}

	@Override
	public TsUserRole getByCondition(TsUserRoleQuery tsUserRoleQuery) {
		return tsUserRoleDao.getByCondition(tsUserRoleQuery);
	}

	@Override
	public List<TsUserRole> findByCondition(TsUserRoleQuery tsUserRoleQuery) {
		List<TsUserRole> results = tsUserRoleDao.findByCondition(tsUserRoleQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(TsUserRoleQuery tsUserRoleQuery) {
		DataTables dataTables = new DataTables();
		dataTables.setDraw(tsUserRoleQuery.getDraw());

		Page page = PageHelper.startPage(tsUserRoleQuery.getPageNo(), tsUserRoleQuery.getLength());
		List<TsUserRole> tsUserRoles = tsUserRoleDao.findByCondition(tsUserRoleQuery);
		if (null == tsUserRoles) tsUserRoles = new ArrayList<>();
		dataTables.setData(tsUserRoles);

		dataTables.setRecordsFiltered(page.getTotal());
		dataTables.setRecordsTotal(page.getTotal());

		return dataTables;
	}

	@Override
	public long countByCondition(TsUserRoleQuery tsUserRoleQuery) {
		return tsUserRoleDao.countByCondition(tsUserRoleQuery);
	}

	@Override
	public long update(TsUserRole tsUserRole) {
		if(null == tsUserRole.getId()) throw new NullPointerException("主键不能为空");
		return tsUserRoleDao.update(tsUserRole);
	}
}