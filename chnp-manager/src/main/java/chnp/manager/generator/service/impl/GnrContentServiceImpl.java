package chnp.manager.generator.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.dao.GnrContentDao;
import chnp.manager.generator.model.domain.GnrContent;
import chnp.manager.generator.model.query.GnrContentQuery;
import chnp.manager.generator.service.GnrContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GnrContentServiceImpl implements GnrContentService {

	@Autowired
	private GnrContentDao gnrContentDao;

	@Override
	public Integer save(GnrContent gnrContent) {
		return gnrContentDao.save(gnrContent);
	}

	@Override
	public Long delete(GnrContentQuery gnrContentQuery) {
		return gnrContentDao.delete(gnrContentQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrContentQuery gnrContentQuery = new GnrContentQuery();
		gnrContentQuery.setId(id);
		return gnrContentDao.delete(gnrContentQuery);
	}

	@Override
	public GnrContent getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrContentQuery gnrContentQuery = new GnrContentQuery();
		gnrContentQuery.setId(id);
		return gnrContentDao.getByCondition(gnrContentQuery);
	}

	@Override
	public GnrContent getByCondition(GnrContentQuery gnrContentQuery) {
		return gnrContentDao.getByCondition(gnrContentQuery);
	}

	@Override
	public List<GnrContent> findByCondition(GnrContentQuery gnrContentQuery) {
		List<GnrContent> results = gnrContentDao.findByCondition(gnrContentQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(GnrContentQuery gnrContentQuery) {
		DataTables dataTables = new DataTables();
		
		Page page = PageHelper.startPage(gnrContentQuery.getPageNo(), gnrContentQuery.getLength());
		List<GnrContent> gnrContents = gnrContentDao.findByCondition(gnrContentQuery);
		if (null == gnrContents) gnrContents = new ArrayList<>();
		dataTables.setData(gnrContents);
		dataTables.setDraw(gnrContentQuery.getDraw());
		dataTables.setRecordsTotal(page.getTotal());
		dataTables.setRecordsFiltered(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(GnrContentQuery gnrContentQuery) {
		return gnrContentDao.countByCondition(gnrContentQuery);
	}

	@Override
	public Integer update(GnrContent gnrContent) {
		if(null == gnrContent.getId()) throw new NullPointerException("主键不能为空");
		return gnrContentDao.updateById(gnrContent);
	}
}