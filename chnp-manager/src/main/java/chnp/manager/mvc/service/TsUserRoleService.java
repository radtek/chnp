package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsUserRole;
import chnp.manager.mvc.model.query.TsUserRoleQuery;

import java.util.List;


public interface TsUserRoleService {

	long save(TsUserRole tsUserRole);

	long delete(TsUserRoleQuery tsUserRoleQuery);

	long deleteById(Integer id);

	TsUserRole getById(Integer id);

	TsUserRole getByCondition(TsUserRoleQuery tsUserRoleQuery);

	List<TsUserRole> findByCondition(TsUserRoleQuery tsUserRoleQuery);

	DataTables findPage(TsUserRoleQuery tsUserRoleQuery);

	long countByCondition(TsUserRoleQuery tsUserRoleQuery);

	long update(TsUserRole tsUserRole);
}