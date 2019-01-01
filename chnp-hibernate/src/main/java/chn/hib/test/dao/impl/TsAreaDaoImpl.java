package chn.hib.test.dao.impl;

import chn.hib.test.dao.TsAreaDao;
import chn.hib.test.model.domain.TsArea;
import chn.hib.test.util.HibUtils;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TsAreaDaoImpl implements TsAreaDao {

	@Override
	public void save(TsArea tsArea) {
		Session session = HibUtils.getsSession();
		Transaction tx = session.beginTransaction();
		try {
			System.out.println("ID为1的地区信息：" + JSONObject.toJSONString(session.get(TsArea.class, 1)));

			session.save(tsArea);

			System.out.println("ID为2的地区信息：" + JSONObject.toJSONString(session.get(TsArea.class, 1)));

			Thread.sleep(10 * 1000);

			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibUtils.closeSession();
		}
	}

	@Override
	public TsArea getById(Integer id) {
		return null;
	}
}