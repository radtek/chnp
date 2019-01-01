package chn.hib.test.dao;

import chn.hib.test.model.domain.TsArea;

public interface TsAreaDao {

	void save(TsArea tsArea);

	TsArea getById(Integer id);

}