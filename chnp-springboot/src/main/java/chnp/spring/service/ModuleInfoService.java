package chnp.spring.service;

import chnp.spring.model.domain.ModuleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModuleInfoService extends JpaRepository<ModuleInfo, Integer> {

	ModuleInfo getByModuleNo(String moduleNo);

	@Query(value = "update ModuleInfo mi set mi.serviceStatus=0 where mi.moduleId=:moduleId")
	int register(Integer moduleId);

	@Query(value = "update ModuleInfo mi set mi.serviceStatus=1 where mi.moduleId=:moduleId")
	int cancel(Integer moduleId);

	@Query(value = "update ModuleInfo mi set mi.heartStatus=0 where mi.moduleId=:moduleId")
	int startHeart(Integer moduleId);

}