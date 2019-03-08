package chnp.manager.generator.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.dao.GnrFilepathDao;
import chnp.manager.generator.model.domain.GnrFilepath;
import chnp.manager.generator.model.query.GnrFilepathQuery;
import chnp.manager.generator.service.GnrFilepathService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GnrFilepathServiceImpl implements GnrFilepathService {

	@Autowired
	private GnrFilepathDao gnrFilepathDao;

	@Override
	public Integer save(GnrFilepath gnrFilepath) {
		return gnrFilepathDao.save(gnrFilepath);
	}

	@Override
	public Long delete(GnrFilepathQuery gnrFilepathQuery) {
		return gnrFilepathDao.delete(gnrFilepathQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrFilepathQuery gnrFilepathQuery = new GnrFilepathQuery();
		gnrFilepathQuery.setId(id);
		return gnrFilepathDao.delete(gnrFilepathQuery);
	}

	@Override
	public GnrFilepath getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrFilepathQuery gnrFilepathQuery = new GnrFilepathQuery();
		gnrFilepathQuery.setId(id);
		return gnrFilepathDao.getByCondition(gnrFilepathQuery);
	}

	@Override
	public GnrFilepath getByCondition(GnrFilepathQuery gnrFilepathQuery) {
		return gnrFilepathDao.getByCondition(gnrFilepathQuery);
	}

	@Override
	public List<GnrFilepath> findByCondition(GnrFilepathQuery gnrFilepathQuery) {
		List<GnrFilepath> results = gnrFilepathDao.findByCondition(gnrFilepathQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(GnrFilepathQuery gnrFilepathQuery) {
		DataTables dataTables = new DataTables();
		
		Page page = PageHelper.startPage(gnrFilepathQuery.getPageNo(), gnrFilepathQuery.getLength());
		List<GnrFilepath> gnrFilepaths = gnrFilepathDao.findByCondition(gnrFilepathQuery);
		if (null == gnrFilepaths) gnrFilepaths = new ArrayList<>();
		dataTables.setData(gnrFilepaths);
		dataTables.setDraw(gnrFilepathQuery.getDraw());
		dataTables.setRecordsTotal(page.getTotal());
		dataTables.setRecordsFiltered(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(GnrFilepathQuery gnrFilepathQuery) {
		return gnrFilepathDao.countByCondition(gnrFilepathQuery);
	}

	@Override
	public Integer update(GnrFilepath gnrFilepath) {
		if(null == gnrFilepath.getId()) throw new NullPointerException("主键不能为空");
		return gnrFilepathDao.updateById(gnrFilepath);
	}
}