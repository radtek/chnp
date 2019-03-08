package chnp.manager.generator.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.dao.GnrTemplateDao;
import chnp.manager.generator.model.domain.GnrContent;
import chnp.manager.generator.model.domain.GnrTemplate;
import chnp.manager.generator.model.query.GnrTemplateQuery;
import chnp.manager.generator.service.GnrContentService;
import chnp.manager.generator.service.GnrTemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GnrTemplateServiceImpl implements GnrTemplateService {

	@Autowired
	private GnrTemplateDao gnrTemplateDao;
	@Autowired
	private GnrContentService gnrContentService;

	@Override
	public void addTemmplate(GnrTemplate gnrTemplate) {
		if (null != gnrTemplate.getGnrContent()) {
			gnrContentService.save(gnrTemplate.getGnrContent());
			gnrTemplate.setContentId(gnrTemplate.getGnrContent().getId());
		}
		gnrTemplateDao.save(gnrTemplate);
	}

	@Override
	public void modify(GnrTemplate gnrTemplate) {
		if (null != gnrTemplate.getGnrContent()) {
			GnrContent content = gnrTemplate.getGnrContent();
			if (null == content.getId()) {
				gnrContentService.save(content);
				gnrTemplate.setContentId(content.getId());
			}else gnrContentService.update(content);
		}

		gnrTemplate.setCreateTime(null);
		gnrTemplate.setModifiedTime(null);
		gnrTemplateDao.updateById(gnrTemplate);
	}

	@Override
	public GnrTemplate view(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrTemplateQuery gnrTemplateQuery = new GnrTemplateQuery();
		gnrTemplateQuery.setId(id);
		return gnrTemplateDao.getByAssociation(gnrTemplateQuery);
	}

	@Override
	public Long delete(GnrTemplateQuery gnrTemplateQuery) {
		return gnrTemplateDao.delete(gnrTemplateQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrTemplateQuery gnrTemplateQuery = new GnrTemplateQuery();
		gnrTemplateQuery.setId(id);
		return gnrTemplateDao.delete(gnrTemplateQuery);
	}

	@Override
	public GnrTemplate getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrTemplateQuery gnrTemplateQuery = new GnrTemplateQuery();
		gnrTemplateQuery.setId(id);
		return gnrTemplateDao.getByCondition(gnrTemplateQuery);
	}

	@Override
	public GnrTemplate getByCondition(GnrTemplateQuery gnrTemplateQuery) {
		return gnrTemplateDao.getByCondition(gnrTemplateQuery);
	}

	@Override
	public List<GnrTemplate> findByAssociation(GnrTemplateQuery gnrTemplateQuery) {
		List<GnrTemplate> results = gnrTemplateDao.findByAssociation(gnrTemplateQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(GnrTemplateQuery gnrTemplateQuery) {
		DataTables dataTables = new DataTables();
		
		Page page = PageHelper.startPage(gnrTemplateQuery.getPageNo(), gnrTemplateQuery.getLength());
		List<GnrTemplate> gnrTemplates = gnrTemplateDao.findByCondition(gnrTemplateQuery);
		if (null == gnrTemplates) gnrTemplates = new ArrayList<>();
		dataTables.setData(gnrTemplates);

		dataTables.setDraw(gnrTemplateQuery.getDraw());
		dataTables.setRecordsTotal(page.getTotal());
		dataTables.setRecordsFiltered(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(GnrTemplateQuery gnrTemplateQuery) {
		return gnrTemplateDao.countByCondition(gnrTemplateQuery);
	}
}