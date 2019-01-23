package chn.hib;

import chn.hib.test.dao.TsAreaDao;
import chn.hib.test.dao.impl.TsAreaDaoImpl;
import chn.hib.test.model.domain.TsArea;
import chn.hib.test.util.HibUtils;

/**
 * @author chngzhen@outlook.com
 * @date 2018/12/21
 */
public class Test {

	public static void main(String[] args) {
//		TsArea tsArea = new TsArea();
//		tsArea.setAreaCode("10010");
//		tsArea.setAreaName("测试地区");
//		tsArea.setParentCode("0000");
//		tsArea.setParentId(1);
//		tsArea.setAreaId(10010);

//		TsArea t = JSON.parseObject(JSON.toJSONString(tsArea), TsArea.class, new ProcessBuilder.Redirect());

		TsAreaDao tsAreaDao = new TsAreaDaoImpl();
		System.out.print(tsAreaDao.getById(1));
	}

}
