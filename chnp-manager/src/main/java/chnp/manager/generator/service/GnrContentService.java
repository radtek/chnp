package chnp.manager.generator.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.model.domain.GnrContent;
import chnp.manager.generator.model.query.GnrContentQuery;

import java.util.List;


public interface GnrContentService {

	Integer save(GnrContent gnrContent);

	Long delete(GnrContentQuery gnrContentQuery);

	Long deleteById(Integer id);

	GnrContent getById(Integer id);

	GnrContent getByCondition(GnrContentQuery gnrContentQuery);

	List<GnrContent> findByCondition(GnrContentQuery gnrContentQuery);

	DataTables findPage(GnrContentQuery gnrContentQuery);

	Long countByCondition(GnrContentQuery gnrContentQuery);

	Integer update(GnrContent gnrContent);
}