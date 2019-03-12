package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsDataItemDao;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.model.query.TsDataItemQuery;
import chnp.manager.mvc.service.TsDataItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TsDataItemServiceImpl implements TsDataItemService {

	@Autowired
	private TsDataItemDao tsDataItemDao;

	@Override
	public long save(TsDataItem tsDataItem) {
		return tsDataItemDao.insert(tsDataItem);
	}

	@Override
	public Long delete(TsDataItemQuery tsDataItemQuery) {
		return tsDataItemDao.delete(tsDataItemQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsDataItemQuery tsDataItemQuery = new TsDataItemQuery();
		tsDataItemQuery.setId(id);
		return tsDataItemDao.delete(tsDataItemQuery);
	}

	@Override
	public TsDataItem view(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsDataItemQuery tsDataItemQuery = new TsDataItemQuery();
		tsDataItemQuery.setId(id);
		return tsDataItemDao.getByAssociation(tsDataItemQuery);
	}

	@Override
	public TsDataItem getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		TsDataItemQuery tsDataItemQuery = new TsDataItemQuery();
		tsDataItemQuery.setId(id);
		return tsDataItemDao.getByCondition(tsDataItemQuery);
	}

	@Override
	public TsDataItem getByCondition(TsDataItemQuery tsDataItemQuery) {
		return tsDataItemDao.getByCondition(tsDataItemQuery);
	}

	@Override
	public List<TsDataItem> findByCondition(TsDataItemQuery tsDataItemQuery) {
		List<TsDataItem> results = tsDataItemDao.findByCondition(tsDataItemQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(TsDataItemQuery tsDataItemQuery) {
		DataTables dataTables = new DataTables();
		
		Page page = PageHelper.startPage(tsDataItemQuery.getPageNo(), tsDataItemQuery.getLength());
		List<TsDataItem> tsDataItems = tsDataItemDao.findByCondition(tsDataItemQuery);
		if (null == tsDataItems) tsDataItems = new ArrayList<>();
		dataTables.setData(tsDataItems);

		dataTables.setDraw(tsDataItemQuery.getDraw());
		dataTables.setRecordsTotal(page.getTotal());
		dataTables.setRecordsFiltered(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(TsDataItemQuery tsDataItemQuery) {
		return tsDataItemDao.countByCondition(tsDataItemQuery);
	}

	@Override
	public long update(TsDataItem tsDataItem) {
		if(null == tsDataItem.getId()) throw new NullPointerException("主键不能为空");
		return tsDataItemDao.update(tsDataItem);
	}
}