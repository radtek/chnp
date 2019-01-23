package chnp.spring.common.service;

import chnp.spring.model.domain.ModuleInfo;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author chngzhen@outlook.com
 * @date 2019/1/17
 */
@Component
public class LocalCacheUtils {

	/**<p>可用模块信息本地缓存</p>
	 * <p>
	 *     满足以下条件的模块被视作可用：
	 *     <ul>
	 *         <li>模块状态“可用”或“调试”：moduleStatus=0 || moduleStatus=2</li>
	 *         <li>服务状态“正常”：serviceStatus=0</li>
	 *         <li>心跳状态“正常”：heartStatus=0</li>
	 *     </ul>
	 * </p>
	 *
	 */
	private SortedMap<Integer, ModuleInfo> localModuleInfos = new TreeMap<>();

	public void addService(ModuleInfo moduleInfo) {
		moduleInfo.setLastContact(new Date());
		localModuleInfos.put(moduleInfo.getModuleId(), moduleInfo);
	}

	public void delService(Integer moduleId) {
		localModuleInfos.remove(moduleId);
	}

	public List<ModuleInfo> findService(int type, int moduleStatus) {
		List<ModuleInfo> results = new ArrayList<>();
		if (0 < localModuleInfos.size()) {
			for (ModuleInfo moduleInfo : localModuleInfos.values()) {
				if (moduleInfo.getModuleType().equals(type)
						&& moduleInfo.getModuleStatus().equals(moduleStatus)) {
					results.add(moduleInfo);
				}
			}
		}
		return results;
	}

	/**<p>配置信息本地缓存</p>
	 *
	 */
	private Map<String, String> configs = new HashMap<>();

	public String getConfig(String key) {
		return configs.get(key);
	}

	public void addConfigs(Map<String, String> configs) {
		this.configs.putAll(configs);
	}

}