package chnp.spring.model.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="module_info")
public class ModuleInfo {
	
	//alias
	public static final String ALIAS_MODULE_ID = "主键";
	public static final String ALIAS_MODULE_NO = "模块编号";
	public static final String ALIAS_MODULE_TYPE = "模块类型";
	public static final String ALIAS_WEIGHT = "权重";
	public static final String ALIAS_INTRANET_URL = "内网地址";
	public static final String ALIAS_SPECIAL_URL = "电信专网地址";
	public static final String ALIAS_PUBLIC_URL = "公网地址";
	public static final String ALIAS_DATA_TRAFFIC = "最大数据流量";
	public static final String ALIAS_HEART_STATUS = "心跳状态";
	public static final String ALIAS_SERVICE_STATUS = "服务状态";
	public static final String ALIAS_MODULE_STATUS = "模块状态";
	public static final String ALIAS_REMARKS = "备注";

	
	//date formats

	//columns START
	

    /**
     * 主键       db_column: module_id 
     */
	@Max(9999999999L)
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "module_id")
	private Integer moduleId;
	
    /**
     * 模块编号       db_column: module_no 
     */
	@NotBlank
	@Length(max=10)
	@Column(name = "module_no")
	private String moduleNo;
	
    /**
     * 模块类型：1-接口模块，2-业务模块       db_column: module_type 
     */ 	
	@NotNull
	@Max(9999999999L)
	@Column(name = "module_type")
	private Integer moduleType;
	
    /**
     * 权重，用于负载均衡       db_column: weight 
     */ 	
	@Max(9999999999L)
	@Column(name = "weight")
	private Integer weight;
	
    /**
     * 内网地址       db_column: intranet_url 
     */ 	
	@NotBlank
	@Length(max=100)
	@Column(name = "intranet_url")
	private String intranetUrl;
	
    /**
     * 电信专网地址       db_column: special_url 
     */ 	
	@Length(max=100)
	@Column(name = "special_url")
	private String specialUrl;
	
    /**
     * 公网地址       db_column: public_url 
     */ 	
	@Length(max=100)
	@Column(name = "public_url")
	private String publicUrl;
	
    /**
     * 最大数据流量，单位条       db_column: data_traffic 
     */ 	
	@Max(9999999999L)
	@Column(name = "data_traffic")
	private Integer dataTraffic;
	
    /**
     * 心跳状态：0-心跳正常、1-心跳异常       db_column: heart_status 
     */ 	
	@Max(9999999999L)
	@Column(name = "heart_status")
	private Integer heartStatus;
	
    /**
     * 服务状态：0-上线，1-下线       db_column: service_status 
     */ 	
	@Max(9999999999L)
	@Column(name = "service_status")
	private Integer serviceStatus;
	
    /**
     * 模块状态：0-可用，1-不可用       db_column: module_status 
     */ 	
	@NotNull
	@Max(9999999999L)
	@Column(name = "module_status")
	private Integer moduleStatus;
	
    /**
     * 备注       db_column: remarks 
     */ 	
	@Length(max=100)
	@Column(name = "remarks")
	private String remarks;

	//columns END
	
	public ModuleInfo(){
	}

	public ModuleInfo(Integer moduleId) {
		this.moduleId = moduleId;
	}


		
	public void setModuleId(Integer value) {
		this.moduleId = value;
	}
	
	public Integer getModuleId() {
		return this.moduleId;
	}		
	
		
	public void setModuleNo(String value) {
		this.moduleNo = value;
	}
	
	public String getModuleNo() {
		return this.moduleNo;
	}		
	
		
	public void setModuleType(Integer value) {
		this.moduleType = value;
	}
	
	public Integer getModuleType() {
		return this.moduleType;
	}		
	
		
	public void setWeight(Integer value) {
		this.weight = value;
	}
	
	public Integer getWeight() {
		return this.weight;
	}		
	
		
	public void setIntranetUrl(String value) {
		this.intranetUrl = value;
	}
	
	public String getIntranetUrl() {
		return this.intranetUrl;
	}		
	
		
	public void setSpecialUrl(String value) {
		this.specialUrl = value;
	}
	
	public String getSpecialUrl() {
		return this.specialUrl;
	}		
	
		
	public void setPublicUrl(String value) {
		this.publicUrl = value;
	}
	
	public String getPublicUrl() {
		return this.publicUrl;
	}		
	
		
	public void setDataTraffic(Integer value) {
		this.dataTraffic = value;
	}
	
	public Integer getDataTraffic() {
		return this.dataTraffic;
	}		
	
		
	public void setHeartStatus(Integer value) {
		this.heartStatus = value;
	}
	
	public Integer getHeartStatus() {
		return this.heartStatus;
	}		
	
		
	public void setServiceStatus(Integer value) {
		this.serviceStatus = value;
	}
	
	public Integer getServiceStatus() {
		return this.serviceStatus;
	}		
	
		
	public void setModuleStatus(Integer value) {
		this.moduleStatus = value;
	}
	
	public Integer getModuleStatus() {
		return this.moduleStatus;
	}		
	
		
	public void setRemarks(String value) {
		this.remarks = value;
	}
	
	public String getRemarks() {
		return this.remarks;
	}		


}

