package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsKeyValueDao;
import chnp.manager.mvc.model.domain.TsKeyValue;
import chnp.manager.mvc.model.query.TsKeyValueQuery;
import chnp.manager.mvc.service.TsKeyValueService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TsKeyValueServiceImpl implements TsKeyValueService {

	@Autowired
	private TsKeyValueDao tsKeyValueDao;

	@Override
	public Integer save(TsKeyValue tsKeyValue) {
		return tsKeyValueDao.save(tsKeyValue);
	}

	@Override
	public Long delete(TsKeyValueQuery tsKeyValueQuery) {
		return tsKeyValueDao.delete(tsKeyValueQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsKeyValueQuery tsKeyValueQuery = new TsKeyValueQuery();
		tsKeyValueQuery.setId(id);
		return tsKeyValueDao.delete(tsKeyValueQuery);
	}

	@Override
	public TsKeyValue view(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsKeyValueQuery tsKeyValueQuery = new TsKeyValueQuery();
		tsKeyValueQuery.setId(id);
		return tsKeyValueDao.getByAssociation(tsKeyValueQuery);
	}

	@Override
	public TsKeyValue getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsKeyValueQuery tsKeyValueQuery = new TsKeyValueQuery();
		tsKeyValueQuery.setId(id);
		return tsKeyValueDao.getByCondition(tsKeyValueQuery);
	}

	@Override
	public TsKeyValue getByCondition(TsKeyValueQuery tsKeyValueQuery) {
		return tsKeyValueDao.getByCondition(tsKeyValueQuery);
	}

	@Override
	public List<TsKeyValue> findByCondition(TsKeyValueQuery tsKeyValueQuery) {
		List<TsKeyValue> results = tsKeyValueDao.findByCondition(tsKeyValueQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(TsKeyValueQuery tsKeyValueQuery) {
		DataTables dataTables = new DataTables();
		
		Page page = PageHelper.startPage(tsKeyValueQuery.getPageNo(), tsKeyValueQuery.getLength());
		List<TsKeyValue> tsKeyValues = tsKeyValueDao.findByCondition(tsKeyValueQuery);
		if (null == tsKeyValues) tsKeyValues = new ArrayList<>();
		dataTables.setData(tsKeyValues);

		dataTables.setDraw(tsKeyValueQuery.getDraw());
		dataTables.setRecordsTotal(page.getTotal());
		dataTables.setRecordsFiltered(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(TsKeyValueQuery tsKeyValueQuery) {
		return tsKeyValueDao.countByCondition(tsKeyValueQuery);
	}

	@Override
	public Integer update(TsKeyValue tsKeyValue) {
		if(null == tsKeyValue.getId()) throw new NullPointerException("主键不能为空");
		return tsKeyValueDao.updateById(tsKeyValue);
	}
}