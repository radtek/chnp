package chnp.manager.generator.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.model.domain.GnrDatatype;
import chnp.manager.generator.model.query.GnrDatatypeQuery;

import java.util.List;


public interface GnrDatatypeService {

	Integer save(GnrDatatype gnrDatatype);

	Long delete(GnrDatatypeQuery gnrDatatypeQuery);

	Long deleteById(Integer id);

	GnrDatatype view(Integer id);

	GnrDatatype getById(Integer id);

	GnrDatatype getByCondition(GnrDatatypeQuery gnrDatatypeQuery);

	List<GnrDatatype> findByCondition(GnrDatatypeQuery gnrDatatypeQuery);

	DataTables findPage(GnrDatatypeQuery gnrDatatypeQuery);

	Long countByCondition(GnrDatatypeQuery gnrDatatypeQuery);

	Integer update(GnrDatatype gnrDatatype);
}