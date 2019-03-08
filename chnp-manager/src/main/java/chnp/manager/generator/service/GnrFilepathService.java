package chnp.manager.generator.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.model.domain.GnrFilepath;
import chnp.manager.generator.model.query.GnrFilepathQuery;

import java.util.List;


public interface GnrFilepathService {

	Integer save(GnrFilepath gnrFilepath);

	Long delete(GnrFilepathQuery gnrFilepathQuery);

	Long deleteById(Integer id);

	GnrFilepath getById(Integer id);

	GnrFilepath getByCondition(GnrFilepathQuery gnrFilepathQuery);

	List<GnrFilepath> findByCondition(GnrFilepathQuery gnrFilepathQuery);

	DataTables findPage(GnrFilepathQuery gnrFilepathQuery);

	Long countByCondition(GnrFilepathQuery gnrFilepathQuery);

	Integer update(GnrFilepath gnrFilepath);
}