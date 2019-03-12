package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsPluginDao;
import chnp.manager.mvc.model.domain.TsPlugin;
import chnp.manager.mvc.model.query.TsPluginQuery;
import chnp.manager.mvc.service.TsPluginService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TsPluginServiceImpl implements TsPluginService {

	@Autowired
	private TsPluginDao tsPluginDao;

	@Override
	public long save(TsPlugin tsPlugin) {
		return tsPluginDao.insert(tsPlugin);
	}

	@Override
	public long delete(TsPluginQuery tsPluginQuery) {
		return tsPluginDao.delete(tsPluginQuery);
	}

	@Override
	public long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsPluginQuery tsPluginQuery = new TsPluginQuery();
		tsPluginQuery.setId(id);
		return tsPluginDao.delete(tsPluginQuery);
	}

	@Override
	public TsPlugin getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsPluginQuery tsPluginQuery = new TsPluginQuery();
		tsPluginQuery.setId(id);
		return tsPluginDao.getByCondition(tsPluginQuery);
	}

	@Override
	public TsPlugin getByCondition(TsPluginQuery tsPluginQuery) {
		return tsPluginDao.getByCondition(tsPluginQuery);
	}

	@Override
	public List<TsPlugin> findByCondition(TsPluginQuery tsPluginQuery) {
		List<TsPlugin> results = tsPluginDao.findByCondition(tsPluginQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(TsPluginQuery tsPluginQuery) {
		DataTables dataTables = new DataTables();
		dataTables.setDraw(tsPluginQuery.getDraw());

		Page page = PageHelper.startPage(tsPluginQuery.getPageNo(), tsPluginQuery.getLength());
		List<TsPlugin> tsPlugins = tsPluginDao.findByCondition(tsPluginQuery);
		if (null == tsPlugins) tsPlugins = new ArrayList<>();
		dataTables.setData(tsPlugins);

		dataTables.setRecordsFiltered(page.getTotal());
		dataTables.setRecordsTotal(page.getTotal());

		return dataTables;
	}

	@Override
	public long countByCondition(TsPluginQuery tsPluginQuery) {
		return tsPluginDao.countByCondition(tsPluginQuery);
	}

	@Override
	public long update(TsPlugin tsPlugin) {
		if(null == tsPlugin.getId()) throw new NullPointerException("主键不能为空");
		return tsPluginDao.update(tsPlugin);
	}
}