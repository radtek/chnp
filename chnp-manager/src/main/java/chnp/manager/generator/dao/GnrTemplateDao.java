package chnp.manager.generator.dao;

import chnp.manager.base.dao.IbaBaseDao;
import chnp.manager.generator.model.domain.GnrTemplate;
import chnp.manager.generator.model.query.GnrTemplateQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface GnrTemplateDao extends IbaBaseDao<GnrTemplate, GnrTemplateQuery> {

	GnrTemplate getByAssociation(GnrTemplateQuery query);

	List<GnrTemplate> findByAssociation(GnrTemplateQuery query);

}