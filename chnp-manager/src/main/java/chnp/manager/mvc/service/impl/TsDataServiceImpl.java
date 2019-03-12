package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.model.query.TsDataItemQuery;
import chnp.manager.mvc.service.TsDataItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chnp.manager.mvc.dao.TsDataDao;
import chnp.manager.mvc.model.domain.TsData;
import chnp.manager.mvc.model.query.TsDataQuery;
import chnp.manager.mvc.service.TsDataService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TsDataServiceImpl implements TsDataService {

	@Autowired
	private TsDataDao tsDataDao;
	@Autowired
	private TsDataItemService tsDataItemService;

	@Override
	public long save(TsData tsData) {
		return tsDataDao.insert(tsData);
	}

	@Override
	public long delData(Integer id) {
		if(null == id) {
			throw new NullPointerException("主键不能为空");
		}

		TsDataItemQuery dataItemQuery = new TsDataItemQuery();
		dataItemQuery.setDataId(id);
		tsDataItemService.delete(dataItemQuery);

		return tsDataDao.delete(new TsDataQuery(id));
	}

	@Override
	public TsData getById(Integer id) {
		if(null == id) {
			throw new IllegalArgumentException("主键不能为空");
		}

		TsDataItem tsDataItem = new TsDataItem();

		TsDataQuery tsDataQuery = new TsDataQuery();
		tsDataQuery.setId(id);
		return tsDataDao.getByCondition(tsDataQuery);
	}

	@Override
	public List<TsDataItem> findValuesByKey(String keyName) {
		List<TsDataItem> values = tsDataDao.findItemsByKey(keyName);
		if (null == values) values = new ArrayList<>();
		return values;
	}

	@Override
	public DataTables findPage(TsDataQuery tsDataQuery) {
		DataTables dataTables = new DataTables();
		dataTables.setDraw(tsDataQuery.getDraw());

		Page page = PageHelper.startPage(tsDataQuery.getPageNo(), tsDataQuery.getLength());
		List<TsData> tsConfigs = tsDataDao.findByCondition(tsDataQuery);
		dataTables.setData(tsConfigs);

		dataTables.setRecordsFiltered(page.getTotal());
		dataTables.setRecordsTotal(page.getTotal());

		return dataTables;
	}

	@Override
	public long update(TsData tsData) {
		if(null == tsData.getId()) throw new NullPointerException("主键不能为空");
		return tsDataDao.update(tsData);
	}
}
