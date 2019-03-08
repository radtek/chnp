package chnp.manager.mvc.dao;

import chnp.manager.base.dao.IbaBaseDao;
import chnp.manager.mvc.model.domain.TsKeyValue;
import chnp.manager.mvc.model.query.TsKeyValueQuery;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TsKeyValueDao extends IbaBaseDao<TsKeyValue, TsKeyValueQuery> {

	TsKeyValue getByAssociation(TsKeyValueQuery query);

}