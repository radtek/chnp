package chnp.manager.mvc.dao;

import chnp.manager.mvc.model.domain.TsModule;
import chnp.manager.mvc.model.query.TsModuleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsModuleDao {

	TsModule getByCondition(TsModuleQuery query);

	List<TsModule> findByCondition(TsModuleQuery query);

}