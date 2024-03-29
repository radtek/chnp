package chnp.manager.mvc.dao;

import chnp.manager.mvc.model.domain.TsConfig;
import chnp.manager.mvc.model.query.TsConfigQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsConfigDao {

	Integer save(TsConfig tsConfig);

	TsConfig getByCondition(TsConfigQuery query);

	List<TsConfig> findByCondition(TsConfigQuery query);

	List<TsConfig> findPage(TsConfigQuery query);

	Long countByCondition(TsConfigQuery query);

	Integer deleteByCondition(TsConfigQuery query);

	Integer updateById(TsConfig tsConfig);

}