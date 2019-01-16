package chnp.spring.model.domain;

/**
 * @author chngzhen@outlook.com
 * @date 2019/1/16
 */
public class ModuleInfo {

	private Integer moduleStatus;
	private Integer serviceStatus;


	public Integer getModuleStatus() {
		return moduleStatus;
	}

	public void setModuleStatus(Integer moduleStatus) {
		this.moduleStatus = moduleStatus;
	}

	public Integer getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(Integer serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
}