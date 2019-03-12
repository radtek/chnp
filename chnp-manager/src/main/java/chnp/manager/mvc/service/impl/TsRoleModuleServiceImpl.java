package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsRoleModuleDao;
import chnp.manager.mvc.model.domain.TsRoleModule;
import chnp.manager.mvc.model.query.TsRoleModuleQuery;
import chnp.manager.mvc.service.TsRoleModuleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TsRoleModuleServiceImpl implements TsRoleModuleService {

	@Autowired
	private TsRoleModuleDao tsRoleModuleDao;

	@Override
	public long save(TsRoleModule tsRoleModule) {
		return tsRoleModuleDao.insert(tsRoleModule);
	}

	@Override
	public long delete(TsRoleModuleQuery tsRoleModuleQuery) {
		return tsRoleModuleDao.delete(tsRoleModuleQuery);
	}

	@Override
	public long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsRoleModuleQuery tsRoleModuleQuery = new TsRoleModuleQuery();
		tsRoleModuleQuery.setId(id);
		return tsRoleModuleDao.delete(tsRoleModuleQuery);
	}

	@Override
	public TsRoleModule getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsRoleModuleQuery tsRoleModuleQuery = new TsRoleModuleQuery();
		tsRoleModuleQuery.setId(id);
		return tsRoleModuleDao.getByCondition(tsRoleModuleQuery);
	}

	@Override
	public TsRoleModule getByCondition(TsRoleModuleQuery tsRoleModuleQuery) {
		return tsRoleModuleDao.getByCondition(tsRoleModuleQuery);
	}

	@Override
	public List<TsRoleModule> findByCondition(TsRoleModuleQuery tsRoleModuleQuery) {
		List<TsRoleModule> results = tsRoleModuleDao.findByCondition(tsRoleModuleQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(TsRoleModuleQuery tsRoleModuleQuery) {
		DataTables dataTables = new DataTables();
		dataTables.setDraw(tsRoleModuleQuery.getDraw());

		Page page = PageHelper.startPage(tsRoleModuleQuery.getPageNo(), tsRoleModuleQuery.getLength());
		List<TsRoleModule> tsRoleModules = tsRoleModuleDao.findByCondition(tsRoleModuleQuery);
		if (null == tsRoleModules) tsRoleModules = new ArrayList<>();
		dataTables.setData(tsRoleModules);

		dataTables.setRecordsFiltered(page.getTotal());
		dataTables.setRecordsTotal(page.getTotal());

		return dataTables;
	}

	@Override
	public long countByCondition(TsRoleModuleQuery tsRoleModuleQuery) {
		return tsRoleModuleDao.countByCondition(tsRoleModuleQuery);
	}

	@Override
	public long update(TsRoleModule tsRoleModule) {
		if(null == tsRoleModule.getId()) throw new NullPointerException("主键不能为空");
		return tsRoleModuleDao.update(tsRoleModule);
	}
}