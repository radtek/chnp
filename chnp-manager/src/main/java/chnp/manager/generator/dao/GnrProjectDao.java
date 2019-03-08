package chnp.manager.generator.dao;

import chnp.manager.base.dao.IbaBaseDao;
import chnp.manager.generator.model.domain.GnrProject;
import chnp.manager.generator.model.query.GnrProjectQuery;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GnrProjectDao extends IbaBaseDao<GnrProject, GnrProjectQuery> {}