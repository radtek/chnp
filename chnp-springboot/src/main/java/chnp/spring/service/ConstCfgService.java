package chnp.spring.service;

import chnp.spring.model.domain.ConstCfg;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chngzhen@outlook.com
 * @date 2019/1/17
 */
public interface ConstCfgService extends JpaRepository<ConstCfg, Integer> {

	ConstCfg getByConstKey(String key);

}