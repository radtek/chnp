package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chnp.manager.mvc.dao.TsKeyDao;
import chnp.manager.mvc.model.domain.TsKey;
import chnp.manager.mvc.model.query.TsKeyQuery;
import chnp.manager.mvc.service.TsKeyService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TsKeyServiceImpl implements TsKeyService {

	@Autowired
	private TsKeyDao tsKeyDao;

	@Override
	public Integer save(TsKey tsKey) {
		return tsKeyDao.save(tsKey);
	}

	@Override
	public Long delete(TsKeyQuery tsKeyQuery) {
		return tsKeyDao.delete(tsKeyQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsKeyQuery tsKeyQuery = new TsKeyQuery();
		tsKeyQuery.setId(id);
		return tsKeyDao.delete(tsKeyQuery);
	}

	@Override
	public TsKey getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsKeyQuery tsKeyQuery = new TsKeyQuery();
		tsKeyQuery.setId(id);
		return tsKeyDao.getByCondition(tsKeyQuery);
	}

	@Override
	public TsKey getByCondition(TsKeyQuery tsKeyQuery) {
		return tsKeyDao.getByCondition(tsKeyQuery);
	}

	@Override
	public List<TsKey> findByCondition(TsKeyQuery tsKeyQuery) {
		return tsKeyDao.findByCondition(tsKeyQuery);
	}

	@Override
	public DataTables findPage(TsKeyQuery tsKeyQuery) {
		DataTables dataTables = new DataTables();
		dataTables.setDraw(tsKeyQuery.getDraw());

		Page page = PageHelper.startPage(tsKeyQuery.getPageNo(), tsKeyQuery.getLength());
		List<TsKey> tsConfigs = tsKeyDao.findByCondition(tsKeyQuery);
		if (null == tsConfigs) tsConfigs = new ArrayList<>();
		dataTables.setData(tsConfigs);

		dataTables.setRecordsFiltered(page.getTotal());
		dataTables.setRecordsTotal(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(TsKeyQuery tsKeyQuery) {
		return tsKeyDao.countByCondition(tsKeyQuery);
	}

	@Override
	public Integer update(TsKey tsKey) {
		if(null == tsKey.getId()) throw new NullPointerException("主键不能为空");
		return tsKeyDao.updateById(tsKey);
	}
}
