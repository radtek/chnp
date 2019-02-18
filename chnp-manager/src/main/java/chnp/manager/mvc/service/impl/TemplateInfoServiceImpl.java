package chnp.manager.mvc.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.dao.TemplateInfoDao;
import chnp.manager.mvc.model.domain.TemplateInfo;
import chnp.manager.mvc.model.query.TemplateInfoQuery;
import chnp.manager.mvc.service.TemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateInfoServiceImpl implements TemplateInfoService {

	@Autowired
	private TemplateInfoDao templateInfoDao;

	@Override
	public Integer save(TemplateInfo entity) {
		return templateInfoDao.save(entity);
	}

	@Override
	public TemplateInfo getById(Integer id) {
		TemplateInfoQuery query = new TemplateInfoQuery();
		query.setId(id);
		query.setStart(0);
		query.setLength(1);
		List<TemplateInfo> templateInfos = templateInfoDao.findByCondition(query);
		return templateInfos.size() > 0 ? templateInfos.get(0) : null;
	}

	@Override
	public DataTables findPage(TemplateInfoQuery query) {
		DataTables dataTables = new DataTables();

		List<TemplateInfo> templateInfos = templateInfoDao.findByCondition(query);
		if (null == templateInfos) templateInfos = new ArrayList<>();
		dataTables.setData(templateInfos);
		dataTables.setRecordsFiltered(templateInfos.size());

		dataTables.setRecordsTotal(templateInfoDao.countByCondition(query));

		return dataTables;
	}

	@Override
	public List<TemplateInfo> findByCondition(TemplateInfoQuery query) {
		return templateInfoDao.findByCondition(query);
	}

	@Override
	public Integer deleteById(Integer id) {
		TemplateInfoQuery query = new TemplateInfoQuery();
		query.setId(id);
		return templateInfoDao.deleteByCondition(query);
	}

	@Override
	public Integer update(TemplateInfo entity) {
		return templateInfoDao.updateById(entity);
	}
}