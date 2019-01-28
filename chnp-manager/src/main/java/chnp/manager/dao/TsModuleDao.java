package chnp.manager.dao;

import chnp.manager.model.domain.TsModule;
import chnp.manager.model.query.TsModuleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsModuleDao {

	TsModule getByCondition(TsModuleQuery query);

	List<TsModule> findByCondition(TsModuleQuery query);

}