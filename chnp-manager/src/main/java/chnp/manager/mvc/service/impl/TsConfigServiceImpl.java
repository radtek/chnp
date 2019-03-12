package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TsConfigDao;
import chnp.manager.mvc.dao.TsUserDao;
import chnp.manager.mvc.model.domain.TsConfig;
import chnp.manager.mvc.model.domain.TsUser;
import chnp.manager.mvc.model.query.TsConfigQuery;
import chnp.manager.mvc.model.query.TsUserQuery;
import chnp.manager.mvc.service.TsConfigService;
import chnp.manager.mvc.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TsConfigServiceImpl implements TsConfigService {

	@Autowired
	private TsConfigDao tsConfigDao;

	@Override
	public Integer save(TsConfig tsConfig) {
		return tsConfigDao.save(tsConfig);
	}

	@Override
	public TsConfig getById(Integer id) {
		TsConfigQuery query = new TsConfigQuery();
		query.setId(id);
		return tsConfigDao.getByCondition(query);
	}

	@Override
	public TsConfig getByKey(String configKey) {
		TsConfigQuery query = new TsConfigQuery();
		query.setConfigKey(configKey);
		return tsConfigDao.getByCondition(query);
	}

	@Override
	public int deleteById(Integer id) {
		TsConfigQuery query = new TsConfigQuery();
		query.setId(id);
		return tsConfigDao.deleteByCondition(query);
	}

	@Override
	public DataTables findPage(TsConfigQuery query) {
		DataTables dataTables = new DataTables();

		List<TsConfig> tsConfigs = tsConfigDao.findByCondition(query);
		if (null == tsConfigs) tsConfigs = new ArrayList<>();
		dataTables.setData(tsConfigs);

		Long total = tsConfigDao.countByCondition(query);
		dataTables.setRecordsFiltered(total);
		dataTables.setRecordsTotal(total);

		return dataTables;
	}

	@Override
	public Integer update(TsConfig tsConfig) {
		return tsConfigDao.updateById(tsConfig);
	}
}