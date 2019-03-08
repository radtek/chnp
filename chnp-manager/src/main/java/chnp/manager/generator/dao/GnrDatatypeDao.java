package chnp.manager.generator.dao;

import chnp.manager.base.dao.IbaBaseDao;
import chnp.manager.generator.model.domain.GnrDatatype;
import chnp.manager.generator.model.query.GnrDatatypeQuery;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GnrDatatypeDao extends IbaBaseDao<GnrDatatype, GnrDatatypeQuery> {

	GnrDatatype getByAssociation(GnrDatatypeQuery query);

}