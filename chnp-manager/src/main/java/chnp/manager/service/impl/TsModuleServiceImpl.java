package chnp.manager.service.impl;

import chnp.manager.dao.TsModuleDao;
import chnp.manager.model.domain.TsModule;
import chnp.manager.model.query.TsModuleQuery;
import chnp.manager.service.TsModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TsModuleServiceImpl implements TsModuleService {

	@Autowired
	private TsModuleDao tsModuleDao;

	@Override
	public TsModule getById(Integer id) {
		TsModuleQuery query = new TsModuleQuery();
		query.setId(id);
		return tsModuleDao.getByCondition(query);
	}

	@Override
	public List<TsModule> findAllByCondition(TsModuleQuery query) {
		return tsModuleDao.findByCondition(query);
	}

	@Override
	public List<TsModule> findMenu(TsModuleQuery query) {
		query.setOrderCondition("parent_id, sort");
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
}