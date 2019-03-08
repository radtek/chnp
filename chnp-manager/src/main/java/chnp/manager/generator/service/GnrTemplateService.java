package chnp.manager.generator.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.model.domain.GnrContent;
import chnp.manager.generator.model.domain.GnrTemplate;
import chnp.manager.generator.model.query.GnrTemplateQuery;

import java.util.List;


public interface GnrTemplateService {

	void addTemmplate(GnrTemplate gnrTemplate);

	Long delete(GnrTemplateQuery gnrTemplateQuery);

	Long deleteById(Integer id);

	GnrTemplate view(Integer id);

	GnrTemplate getById(Integer id);

	GnrTemplate getByCondition(GnrTemplateQuery gnrTemplateQuery);

	List<GnrTemplate> findByAssociation(GnrTemplateQuery gnrTemplateQuery);

	DataTables findPage(GnrTemplateQuery gnrTemplateQuery);

	Long countByCondition(GnrTemplateQuery gnrTemplateQuery);

	void modify(GnrTemplate gnrTemplate);
}