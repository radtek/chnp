package chnp.spring.service;

import chnp.spring.model.domain.CpeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author chngzhen@outlook.com
 * @date 2019/1/17
 */
public interface CpeInfoService extends JpaRepository<CpeInfo, String> {

	CpeInfo getByHostId(String hostId);

	@Query(value = "update CpeInfo set userStatus=:status where hostId=:hostId")
	int updateUserStatus(String hostId, Integer status);

}