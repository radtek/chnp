package chnp.hsql;

import chnp.hsql.dao.HSQLUtils;

/**
 * @author chngzhen@outlook.com
 * @date 2019/1/4
 */
public class Test {


	public static void main(String[] args) {
		try {
			HSQLUtils hsqlUtils = new HSQLUtils();
			hsqlUtils.execute("CREATE TABLE traffic_count(module_no VARCHAR(10), msg_type INT, start_time DATETIME, end_time DATETIME, num INT, day_num INT)");

			int num = hsqlUtils.update("INSERT INTO traffic_count VALUES(?,?,?,?,?,?)", new Object[] {
					"ZDJKMK-1", 1, "2018-01-04 10:32:10", "2018-01-04 10:32:20", 12083, 473829
			});

			System.out.print(hsqlUtils.find("SELECT * FROM traffic_count").get(0).get("module_no"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}