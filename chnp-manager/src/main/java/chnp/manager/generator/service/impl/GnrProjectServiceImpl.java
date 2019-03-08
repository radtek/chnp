package chnp.manager.generator.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.dao.GnrProjectDao;
import chnp.manager.generator.model.domain.GnrProject;
import chnp.manager.generator.model.query.GnrProjectQuery;
import chnp.manager.generator.service.GnrProjectService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GnrProjectServiceImpl implements GnrProjectService {

	@Autowired
	private GnrProjectDao gnrProjectDao;

	@Override
	public Integer save(GnrProject gnrProject) {
		return gnrProjectDao.save(gnrProject);
	}

	@Override
	public Long delete(GnrProjectQuery gnrProjectQuery) {
		return gnrProjectDao.delete(gnrProjectQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrProjectQuery gnrProjectQuery = new GnrProjectQuery();
		gnrProjectQuery.setId(id);
		return gnrProjectDao.delete(gnrProjectQuery);
	}

	@Override
	public GnrProject getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrProjectQuery gnrProjectQuery = new GnrProjectQuery();
		gnrProjectQuery.setId(id);
		return gnrProjectDao.getByCondition(gnrProjectQuery);
	}

	@Override
	public GnrProject getByCondition(GnrProjectQuery gnrProjectQuery) {
		return gnrProjectDao.getByCondition(gnrProjectQuery);
	}

	@Override
	public List<GnrProject> findByCondition(GnrProjectQuery gnrProjectQuery) {
		List<GnrProject> results = gnrProjectDao.findByCondition(gnrProjectQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(GnrProjectQuery gnrProjectQuery) {
		DataTables dataTables = new DataTables();
		
		Page page = PageHelper.startPage(gnrProjectQuery.getPageNo(), gnrProjectQuery.getLength());
		List<GnrProject> gnrProjects = gnrProjectDao.findByCondition(gnrProjectQuery);
		if (null == gnrProjects) gnrProjects = new ArrayList<>();
		dataTables.setData(gnrProjects);
		dataTables.setDraw(gnrProjectQuery.getDraw());
		dataTables.setRecordsTotal(page.getTotal());
		dataTables.setRecordsFiltered(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(GnrProjectQuery gnrProjectQuery) {
		return gnrProjectDao.countByCondition(gnrProjectQuery);
	}

	@Override
	public Integer update(GnrProject gnrProject) {
		if(null == gnrProject.getId()) throw new NullPointerException("主键不能为空");
		return gnrProjectDao.updateById(gnrProject);
	}
}