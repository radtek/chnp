package chnp.manager.generator.service.impl;

import chnp.manager.common.entity.DataTables;
import chnp.manager.generator.dao.GnrDatatypeDao;
import chnp.manager.generator.model.domain.GnrDatatype;
import chnp.manager.generator.model.query.GnrDatatypeQuery;
import chnp.manager.generator.service.GnrDatatypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GnrDatatypeServiceImpl implements GnrDatatypeService {

	@Autowired
	private GnrDatatypeDao gnrDatatypeDao;

	@Override
	public Integer save(GnrDatatype gnrDatatype) {
		return gnrDatatypeDao.save(gnrDatatype);
	}

	@Override
	public Long delete(GnrDatatypeQuery gnrDatatypeQuery) {
		return gnrDatatypeDao.delete(gnrDatatypeQuery);
	}

	@Override
	public Long deleteById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrDatatypeQuery gnrDatatypeQuery = new GnrDatatypeQuery();
		gnrDatatypeQuery.setId(id);
		return gnrDatatypeDao.delete(gnrDatatypeQuery);
	}

	@Override
	public GnrDatatype view(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrDatatypeQuery gnrDatatypeQuery = new GnrDatatypeQuery();
		gnrDatatypeQuery.setId(id);
		return gnrDatatypeDao.getByAssociation(gnrDatatypeQuery);
	}

	@Override
	public GnrDatatype getById(Integer id) {
		if(null == id) throw new NullPointerException("主键不能为空");
		GnrDatatypeQuery gnrDatatypeQuery = new GnrDatatypeQuery();
		gnrDatatypeQuery.setId(id);
		return gnrDatatypeDao.getByCondition(gnrDatatypeQuery);
	}

	@Override
	public GnrDatatype getByCondition(GnrDatatypeQuery gnrDatatypeQuery) {
		return gnrDatatypeDao.getByCondition(gnrDatatypeQuery);
	}

	@Override
	public List<GnrDatatype> findByCondition(GnrDatatypeQuery gnrDatatypeQuery) {
		List<GnrDatatype> results = gnrDatatypeDao.findByCondition(gnrDatatypeQuery);
		return null == results ? new ArrayList<>() : results;
	}

	@Override
	public DataTables findPage(GnrDatatypeQuery gnrDatatypeQuery) {
		DataTables dataTables = new DataTables();
		
		Page page = PageHelper.startPage(gnrDatatypeQuery.getPageNo(), gnrDatatypeQuery.getLength());
		List<GnrDatatype> gnrDatatypes = gnrDatatypeDao.findByCondition(gnrDatatypeQuery);
		if (null == gnrDatatypes) gnrDatatypes = new ArrayList<>();
		dataTables.setData(gnrDatatypes);
		dataTables.setDraw(gnrDatatypeQuery.getDraw());
		dataTables.setRecordsTotal(page.getTotal());
		dataTables.setRecordsFiltered(page.getTotal());

		return dataTables;
	}

	@Override
	public Long countByCondition(GnrDatatypeQuery gnrDatatypeQuery) {
		return gnrDatatypeDao.countByCondition(gnrDatatypeQuery);
	}

	@Override
	public Integer update(GnrDatatype gnrDatatype) {
		if(null == gnrDatatype.getId()) throw new NullPointerException("主键不能为空");
		return gnrDatatypeDao.updateById(gnrDatatype);
	}
}