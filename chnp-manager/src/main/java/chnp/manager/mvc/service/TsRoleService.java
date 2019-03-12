package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsRole;
import chnp.manager.mvc.model.query.TsRoleQuery;

import java.util.List;


public interface TsRoleService {

	long save(TsRole tsRole);

	long delete(TsRoleQuery tsRoleQuery);

	long deleteById(Integer id);

	TsRole getById(Integer id);

	TsRole getByCondition(TsRoleQuery tsRoleQuery);

	List<TsRole> findByCondition(TsRoleQuery tsRoleQuery);

	DataTables findPage(TsRoleQuery tsRoleQuery);

	long countByCondition(TsRoleQuery tsRoleQuery);

	long update(TsRole tsRole);
}