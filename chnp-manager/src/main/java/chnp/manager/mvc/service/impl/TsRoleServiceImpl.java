package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsRoleDao;
import chnp.manager.mvc.model.domain.TsRole;
import chnp.manager.mvc.model.query.TsRoleQuery;
import chnp.manager.mvc.service.TsRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TsRoleServiceImpl implements TsRoleService {

	@Autowired
	private TsRoleDao tsRoleDao;

	@Override
	public long save(TsRole tsRole) {
		return tsRoleDao.insert(tsRole);
	}

	@Override
	public long delete(TsRoleQuery tsRoleQuery) {
		return tsRoleDao.delete(tsRoleQuery);
	}

	@Override
	public long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsRoleQuery tsRoleQuery = new TsRoleQuery();
		tsRoleQuery.setId(id);
		return tsRoleDao.delete(tsRoleQuery);
	}

	@Override
	public TsRole getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsRoleQuery tsRoleQuery = new TsRoleQuery();
		tsRoleQuery.setId(id);
		return tsRoleDao.getByCondition(tsRoleQuery);
	}

	@Override
	public TsRole getByCondition(TsRoleQuery tsRoleQuery) {
		return tsRoleDao.getByCondition(tsRoleQuery);
	}

	@Override
	public List<TsRole> findByCondition(TsRoleQuery tsRoleQuery) {
		List<TsRole> results = tsRoleDao.findByCondition(tsRoleQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(TsRoleQuery tsRoleQuery) {
		DataTables dataTables = new DataTables();
		dataTables.setDraw(tsRoleQuery.getDraw());

		Page page = PageHelper.startPage(tsRoleQuery.getPageNo(), tsRoleQuery.getLength());
		List<TsRole> tsRoles = tsRoleDao.findByCondition(tsRoleQuery);
		if (null == tsRoles) tsRoles = new ArrayList<>();
		dataTables.setData(tsRoles);

		dataTables.setRecordsFiltered(page.getTotal());
		dataTables.setRecordsTotal(page.getTotal());

		return dataTables;
	}

	@Override
	public long countByCondition(TsRoleQuery tsRoleQuery) {
		return tsRoleDao.countByCondition(tsRoleQuery);
	}

	@Override
	public long update(TsRole tsRole) {
		if(null == tsRole.getId()) throw new NullPointerException("主键不能为空");
		return tsRoleDao.update(tsRole);
	}
}