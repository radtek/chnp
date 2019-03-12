package chnp.manager.mvc.service.impl;

import chnp.manager.common.service.UtilService;
import chnp.manager.mvc.dao.TsModuleDao;
import chnp.manager.mvc.model.domain.TsModule;
import chnp.manager.mvc.model.query.TsModuleQuery;
import chnp.manager.mvc.service.TsModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TsModuleServiceImpl implements TsModuleService {

	@Autowired
	private UtilService utilService;
	@Autowired
	private TsModuleDao tsModuleDao;

	@Override
	public Integer save(TsModule entity) {
		return tsModuleDao.save(entity);
	}

	@Override
	public TsModule getById(Integer id) {
		TsModuleQuery query = new TsModuleQuery();
		query.setId(id);
		return tsModuleDao.getByCondition(query);
	}

	@Override
	public Integer update(TsModule tsModule) {
		return tsModuleDao.updateById(tsModule);
	}

	@Override
	public List<TsModule> findAllByCondition(TsModuleQuery query) {
		return tsModuleDao.findByCondition(query);
	}

	@Override
	public List<TsModule> findMenu(TsModuleQuery query) {
		query.setAdditionalConstrains("order by parent_id, sort");
		List<TsModule> tsModules = tsModuleDao.findByCondition(query);

		List<TsModule> menus = new ArrayList<>();
		for (TsModule tsModule : tsModules) {
			if (tsModule.getParentId() == 0) {
				menus.add(tsModule);
				menus.addAll(getChildren(tsModule.getId(), tsModules));
			}
		}
		return menus;
	}

	private List<TsModule> getChildren(int pid, List<TsModule> tsModules) {
		List<TsModule> children = new ArrayList<>();
		for (TsModule tsModule : tsModules) {
			if (tsModule.getParentId() == pid) {
				children.add(tsModule);
				if (tsModule.getIsParent() == 1)
					children.addAll(getChildren(tsModule.getId(), tsModules));
			}
		}
		return children;
	}

	@Override
	public List<Map<String, Object>> getSelectTree(TsModuleQuery query) {
		List<Map<String, Object>> list = tsModuleDao.getSelectTree(query);
		if (null == list) list = new ArrayList<>();
		return utilService.list2SelectTree(list, 0, 0);
	}

	@Override
	public Long deleteById(Integer id) {
		TsModuleQuery query = new TsModuleQuery();
		query.setId(id);
		return tsModuleDao.delete(query);
	}

	@Override
	public Long deepdelete(Integer id) {
		long num = deleteChild(id);
		num += tsModuleDao.deleteById(id);
		return num;
	}

	private long deleteChild(Integer parentId) {
		long num = 0;
		if (null != parentId) {
			TsModuleQuery query = new TsModuleQuery();
			query.setParentId(parentId);
			List<TsModule> children = tsModuleDao.findByCondition(query);
			if (0 < children.size()) {
				for (TsModule child : children) {
					num += deleteChild(child.getId());
				}
				num += tsModuleDao.delete(query);
			}
		}
		return num;
	}
}