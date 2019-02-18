package chnp.manager.mvc.service;

import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TemplateInfo;
import chnp.manager.mvc.model.query.TemplateInfoQuery;

import java.util.List;

public interface TemplateInfoService {

	Integer save(TemplateInfo entity);

	TemplateInfo getById(Integer id);

	DataTables findPage(TemplateInfoQuery query);

	List<TemplateInfo> findByCondition(TemplateInfoQuery query);

	Integer deleteById(Integer id);

	Integer update(TemplateInfo entity);
}