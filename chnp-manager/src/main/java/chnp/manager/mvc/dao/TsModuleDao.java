package chnp.manager.mvc.dao;

import chnp.manager.base.dao.IbaBaseDao;
import chnp.manager.mvc.model.domain.TsModule;
import chnp.manager.mvc.model.query.TsModuleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TsModuleDao extends IbaBaseDao<TsModule, TsModuleQuery> {

	Long deleteById(Integer id);

	TsModule getById(Integer id);

	List<Map<String, Object>> getSelectTree(TsModuleQuery query);

}