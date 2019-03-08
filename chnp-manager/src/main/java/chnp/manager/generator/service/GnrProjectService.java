package chnp.manager.generator.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.model.domain.GnrProject;
import chnp.manager.generator.model.query.GnrProjectQuery;

import java.util.List;


public interface GnrProjectService {

	Integer save(GnrProject gnrProject);

	Long delete(GnrProjectQuery gnrProjectQuery);

	Long deleteById(Integer id);

	GnrProject getById(Integer id);

	GnrProject getByCondition(GnrProjectQuery gnrProjectQuery);

	List<GnrProject> findByCondition(GnrProjectQuery gnrProjectQuery);

	DataTables findPage(GnrProjectQuery gnrProjectQuery);

	Long countByCondition(GnrProjectQuery gnrProjectQuery);

	Integer update(GnrProject gnrProject);
}