package chnp.manager.generator.dao;

import chnp.manager.base.dao.IbaBaseDao;
import chnp.manager.generator.model.domain.GnrFilepath;
import chnp.manager.generator.model.query.GnrFilepathQuery;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GnrFilepathDao extends IbaBaseDao<GnrFilepath, GnrFilepathQuery> {}