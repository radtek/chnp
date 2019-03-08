package chnp.manager.generator.dao;

import chnp.manager.base.dao.IbaBaseDao;
import chnp.manager.generator.model.domain.GnrContent;
import chnp.manager.generator.model.query.GnrContentQuery;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GnrContentDao extends IbaBaseDao<GnrContent, GnrContentQuery> {}