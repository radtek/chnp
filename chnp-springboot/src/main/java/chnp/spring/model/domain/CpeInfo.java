package chnp.spring.model.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.ffcs.common.domain.AbstractEntity;
import com.ffcs.common.utils.DateFormats;
import com.ffcs.common.utils.DateUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

@Entity
@Table(name="cpe_info")
public class CpeInfo {

	//alias
	public static final String ALIAS_HOST_ID = "终端ID";
	public static final String ALIAS_DEVICE_TYPE_ID = "设备类型";
	public static final String ALIAS_SERIAL_NO = "设备序列号";	
	public static final String ALIAS_SFW_UPD_TIME = "软件版本更新时间";
	public static final String ALIAS_SOFTWARE = "当前软件版本号";
	public static final String ALIAS_HARDWARE = "当前硬件版本号";
	public static final String ALIAS_MAC_ADDRESS = "Mac地址";
	public static final String ALIAS_AREA_ID = "区域";
	public static final String ALIAS_FIRST_CONTACT = "首次连接时间";
	public static final String ALIAS_DATA_MODEL_NAME = "数据模型名称";
	public static final String ALIAS_BOOT_SCREEN_ID = "开机图片";
	public static final String ALIAS_USER_ID = "ITV账号";
	public static final String ALIAS_BIZ_PASSWORD = "ITV密码";
	public static final String ALIAS_SYNCH_TIME = "用户开通时间";
	public static final String ALIAS_ACCESS_TYPE = "用户接入方式";
	public static final String ALIAS_OLT_NO = "oltNo";
	public static final String ALIAS_OLT_COUNTY = "olt所属区县";
	public static final String ALIAS_PON_NO = "ponNo";
	public static final String ALIAS_SCREEN_UPDATE_TIME = "开机图片更新时间";
	public static final String ALIAS_USER_STATUS = "业务账号状态";
	public static final String ALIAS_USER_STATUS_UPDATE_TIME = "业务账号状态更新时间";
	public static final String ALIAS_BOOT_ANIMATION_ID = "开机动画";
	public static final String ALIAS_BOOT_ANIMATION_UPDATE_TIME = "开机动画更新时间";
	public static final String ALIAS_DESKTOP_APK_VERSION = "桌面APK版本";
	public static final String ALIAS_GET_TASK_STATUS = "零配置get任务执行状态";
	public static final String ALIAS_SET_TASK_STATUS = "零配置set任务执行状态";

	
	//date formats
	public static final String FORMAT_SFW_UPD_TIME = DateFormats.DATE_TIME_FORMAT;
	public static final String FORMAT_FIRST_CONTACT = DateFormats.DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_CONTACT = DateFormats.DATE_TIME_FORMAT;
	public static final String FORMAT_SYNCH_TIME = DateFormats.DATE_TIME_FORMAT;
	public static final String FORMAT_SCREEN_UPDATE_TIME = DateFormats.DATE_TIME_FORMAT;
	public static final String FORMAT_USER_STATUS_UPDATE_TIME = DateFormats.DATE_TIME_FORMAT;
	public static final String FORMAT_BOOT_ANIMATION_UPDATE_TIME = DateFormats.DATE_TIME_FORMAT;
	
	public static final int USER_STATUS_NORMAL = 0;//正常
	public static final int USER_STATUS_PAUSE = 1;//暂停
	public static final int USER_STATUS_CANCEL = 2;//销户


	//columns START
	

    /**
     * 终端ID       db_column: host_id 
     */ 	
	@Length(max=32)
	@Id
	@Column(name = "host_id")
	@com.ffcs.common.annotation.Column(name = ALIAS_HOST_ID)
	private String hostId;

    /**
     * 设备类型ID       db_column: device_type_id
     */
	@NotNull
	@Max(9999999999L)
	@Column(name = "device_type_id")
	@com.ffcs.common.annotation.Column(name = ALIAS_DEVICE_TYPE_ID)
	private Integer deviceTypeId;

    /**
     * 设备序列号       db_column: serial_no
     */
	@Length(max=32)
	@Column(name = "serial_no")
	@com.ffcs.common.annotation.Column(name = ALIAS_SERIAL_NO)
	private String serialNo;


    /**
     * 软件版本更新时间       db_column: sfw_upd_time
     */

	@Column(name = "sfw_upd_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_SFW_UPD_TIME)
	private java.util.Date sfwUpdTime;

	@Column(name = "sfw_upd_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_SFW_UPD_TIME)
	@Transient
	private String sfwUpdTimeString;
    /**
     * 当前软件版本号       db_column: software
     */
	@Length(max=50)
	@Column(name = "software")
	@com.ffcs.common.annotation.Column(name = ALIAS_SOFTWARE)
	private String software;

    /**
     * 当前硬件版本号       db_column: hardware
     */
	@Length(max=50)
	@Column(name = "hardware")
	@com.ffcs.common.annotation.Column(name = ALIAS_HARDWARE)
	private String hardware;

    /**
     * Mac地址       db_column: mac_address
     */
	@Length(max=20)
	@Column(name = "mac_address")
	@com.ffcs.common.annotation.Column(name = ALIAS_MAC_ADDRESS)
	private String macAddress;

    /**
     * 区域ID       db_column: area_id
     */
	@Length(max=10)
	@Column(name = "area_id")
	@com.ffcs.common.annotation.Column(name = ALIAS_AREA_ID)
	private String areaId;

    /**
     * 首次连接时间       db_column: first_contact
     */

	@Column(name = "first_contact")
	@com.ffcs.common.annotation.Column(name = ALIAS_FIRST_CONTACT)
	private java.util.Date firstContact;

	@Column(name = "first_contact")
	@com.ffcs.common.annotation.Column(name = ALIAS_FIRST_CONTACT)
	@Transient
	private String firstContactString;

    /**
     * 数据模型名称       db_column: data_model_name
     */
	@Length(max=20)
	@Column(name = "data_model_name")
	@com.ffcs.common.annotation.Column(name = ALIAS_DATA_MODEL_NAME)
	private String dataModelName;

    /**
     * 开机图片ID       db_column: boot_screen_id
     */
	@Max(9999999999L)
	@Column(name = "boot_screen_id")
	@com.ffcs.common.annotation.Column(name = ALIAS_BOOT_SCREEN_ID)
	private Integer bootScreenId;

    /**
     * 用户ID       db_column: user_id
     */
	@Length(max=50)
	@Column(name = "user_id")
	@com.ffcs.common.annotation.Column(name = ALIAS_USER_ID)
	private String userId;

    /**
     * 业务密码       db_column: biz_password
     */
	@Length(max=32)
	@Column(name = "biz_password")
	@com.ffcs.common.annotation.Column(name = ALIAS_BIZ_PASSWORD, disabled=true)
	private String bizPassword;

    /**
     * 用户开通时间       db_column: synch_time
     */

	@Column(name = "synch_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_SYNCH_TIME)
	private java.util.Date synchTime;

	@Column(name = "synch_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_SYNCH_TIME)
	@Transient
	private String synchTimeString;
    /**
     * 用户接入方式0：AD用户,1：LAN用户,2：光用户       db_column: access_type
     */
	@Max(9999999999L)
	@Column(name = "access_type")
	@com.ffcs.common.annotation.Column(name = ALIAS_ACCESS_TYPE)
	private Integer accessType;

    /**
     * oltNo       db_column: olt_no
     */
	@Length(max=150)
	@Column(name = "olt_no")
	@com.ffcs.common.annotation.Column(name = ALIAS_OLT_NO)
	private String oltNo;

    /**
     * olt所属区县       db_column: olt_county
     */
	@Length(max=10)
	@Column(name = "olt_county")
	@com.ffcs.common.annotation.Column(name = ALIAS_OLT_COUNTY)
	private String oltCounty;

    /**
     * ponNo       db_column: pon_no
     */
	@Length(max=50)
	@Column(name = "pon_no")
	@com.ffcs.common.annotation.Column(name = ALIAS_PON_NO)
	private String ponNo;

    /**
     * 开机图片更新时间       db_column: screen_update_time
     */

	@Column(name = "screen_update_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_SCREEN_UPDATE_TIME)
	private java.util.Date screenUpdateTime;

	@Column(name = "screen_update_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_SCREEN_UPDATE_TIME)
	@Transient
	private String screenUpdateTimeString;
    /**
     * 业务账号状态:0-正常，1-暂停，2-销户       db_column: user_status
     */
	@NotNull
	@Max(9999999999L)
	@Column(name = "user_status")
	@com.ffcs.common.annotation.Column(name = ALIAS_USER_STATUS)
	private Integer userStatus;

    /**
     * 业务账号状态更新时间       db_column: user_status_update_time
     */

	@Column(name = "user_status_update_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_USER_STATUS_UPDATE_TIME)
	private java.util.Date userStatusUpdateTime;

	@Column(name = "user_status_update_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_USER_STATUS_UPDATE_TIME)
	@Transient
	private String userStatusUpdateTimeString;

    /**
     * 开机动画ID       db_column: boot_animation_id
     */
	@Max(9999999999L)
	@Column(name = "boot_animation_id")
	@com.ffcs.common.annotation.Column(name = ALIAS_BOOT_ANIMATION_ID)
	private Integer bootAnimationId;

    /**
     * 开机动画更新时间       db_column: boot_animation_update_time
     */

	@Column(name = "boot_animation_update_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_BOOT_ANIMATION_UPDATE_TIME)
	private java.util.Date bootAnimationUpdateTime;

	@Column(name = "boot_animation_update_time")
	@com.ffcs.common.annotation.Column(name = ALIAS_BOOT_ANIMATION_UPDATE_TIME)
	@Transient
	private String bootAnimationUpdateTimeString;
//	@JSONField(serialize = false)
//	private CpeInfoQuery cpeInfoQuery;


	@Length(max=20)
	@Column(name = "desktop_apk_version")
	@com.ffcs.common.annotation.Column(name = ALIAS_DESKTOP_APK_VERSION)
	private String desktopApkVersion;

	/**
     * 零配置get任务执行状态:0-初始值，1-已成功执行       db_column: get_task_status
     */
	@NotNull
	@Max(9999999999L)
	@Column(name = "get_task_status")
	@com.ffcs.common.annotation.Column(name = ALIAS_GET_TASK_STATUS)
	private Integer getTaskStatus;

	/**
	 * 零配置set任务执行状态:0-初始值，1-已成功执行       db_column: set_task_status
	 */
	@NotNull
	@Max(9999999999L)
	@Column(name = "set_task_status")
	@com.ffcs.common.annotation.Column(name = ALIAS_SET_TASK_STATUS)
	private Integer setTaskStatus;

	//columns END

	public CpeInfo(){
	}

	public CpeInfo(
		String hostId
	){
		this.hostId = hostId;
	}



	public void setHostId(String value) {
		this.hostId = value;
	}

	public String getHostId() {
		return this.hostId;
	}


	public void setDeviceTypeId(Integer value) {
		this.deviceTypeId = value;
	}

	public Integer getDeviceTypeId() {
		return this.deviceTypeId;
	}


	public void setSerialNo(String value) {
		this.serialNo = value;
	}

	public String getSerialNo() {
		return this.serialNo;
	}


	public void setSfwUpdTime(java.util.Date value) {
		this.sfwUpdTime = value;
		this.sfwUpdTimeString = getSfwUpdTimeString();
	}

	public java.util.Date getSfwUpdTime() {
		return this.sfwUpdTime;
	}

	public String getSfwUpdTimeString() {
		return this.sfwUpdTimeString = DateUtil.formatDateToString(FORMAT_SFW_UPD_TIME, getSfwUpdTime());
	}
	public void setSfwUpdTimeString(String value) throws ParseException {
		setSfwUpdTime(DateUtil.stringConvertDate(FORMAT_SFW_UPD_TIME, value));
	}

	public void setSoftware(String value) {
		this.software = value;
	}

	public String getSoftware() {
		return this.software;
	}


	public void setHardware(String value) {
		this.hardware = value;
	}

	public String getHardware() {
		return this.hardware;
	}

	public void setMacAddress(String value) {
		this.macAddress = value;
	}

	public String getMacAddress() {
		return this.macAddress;
	}


	public void setAreaId(String value) {
		this.areaId = value;
	}

	public String getAreaId() {
		return this.areaId;
	}

	public void setFirstContact(java.util.Date value) {
		this.firstContact = value;
		this.firstContactString = getFirstContactString();
	}

	public java.util.Date getFirstContact() {
		return this.firstContact;
	}

	public String getFirstContactString() {
		return this.firstContactString = DateUtil.formatDateToString(FORMAT_FIRST_CONTACT, getFirstContact());
	}
	public void setFirstContactString(String value) throws ParseException {
		setFirstContact(DateUtil.stringConvertDate(FORMAT_FIRST_CONTACT, value));
	}

	public void setDataModelName(String value) {
		this.dataModelName = value;
	}

	public String getDataModelName() {
		return this.dataModelName;
	}


	public void setBootScreenId(Integer value) {
		this.bootScreenId = value;
	}

	public Integer getBootScreenId() {
		return this.bootScreenId;
	}


	public void setUserId(String value) {
		this.userId = value;
	}

	public String getUserId() {
		return this.userId;
	}


	public void setBizPassword(String value) {
		this.bizPassword = value;
	}

	public String getBizPassword() {
		return this.bizPassword;
	}

	public void setSynchTime(java.util.Date value) {
		this.synchTime = value;
		this.synchTimeString = getSynchTimeString();
	}

	public java.util.Date getSynchTime() {
		return this.synchTime;
	}

	public String getSynchTimeString() {
		return this.synchTimeString = DateUtil.formatDateToString(FORMAT_SYNCH_TIME, getSynchTime());
	}
	public void setSynchTimeString(String value) throws ParseException {
		setSynchTime(DateUtil.stringConvertDate(FORMAT_SYNCH_TIME, value));
	}

	public void setAccessType(Integer value) {
		this.accessType = value;
	}

	public Integer getAccessType() {
		return this.accessType;
	}


	public void setOltNo(String value) {
		this.oltNo = value;
	}

	public String getOltNo() {
		return this.oltNo;
	}

	public void setOltCounty(String value) {
		this.oltCounty = value;
	}

	public String getOltCounty() {
		return this.oltCounty;
	}


	public void setPonNo(String value) {
		this.ponNo = value;
	}

	public String getPonNo() {
		return this.ponNo;
	}

	public void setScreenUpdateTime(java.util.Date value) {
		this.screenUpdateTime = value;
		this.screenUpdateTimeString = getScreenUpdateTimeString();
	}

	public java.util.Date getScreenUpdateTime() {
		return this.screenUpdateTime;
	}

	public String getScreenUpdateTimeString() {
		return this.screenUpdateTimeString = DateUtil.formatDateToString(FORMAT_SCREEN_UPDATE_TIME, getScreenUpdateTime());
	}
	public void setScreenUpdateTimeString(String value) throws ParseException {
		setScreenUpdateTime(DateUtil.stringConvertDate(FORMAT_SCREEN_UPDATE_TIME, value));
	}

	public void setUserStatus(Integer value) {
		this.userStatus = value;
	}

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatusUpdateTime(java.util.Date value) {
		this.userStatusUpdateTime = value;
		this.userStatusUpdateTimeString = getUserStatusUpdateTimeString();
	}

	public java.util.Date getUserStatusUpdateTime() {
		return this.userStatusUpdateTime;
	}

	public String getUserStatusUpdateTimeString() {
		return this.userStatusUpdateTimeString = DateUtil.formatDateToString(FORMAT_USER_STATUS_UPDATE_TIME, getUserStatusUpdateTime());
	}
	public void setUserStatusUpdateTimeString(String value) throws ParseException {
		setUserStatusUpdateTime(DateUtil.stringConvertDate(FORMAT_USER_STATUS_UPDATE_TIME, value));
	}



	public void setBootAnimationId(Integer value) {
		this.bootAnimationId = value;
	}

	public Integer getBootAnimationId() {
		return this.bootAnimationId;
	}

	public void setBootAnimationUpdateTime(java.util.Date value) {
		this.bootAnimationUpdateTime = value;
		this.bootAnimationUpdateTimeString = getBootAnimationUpdateTimeString();
	}

	public java.util.Date getBootAnimationUpdateTime() {
		return this.bootAnimationUpdateTime;
	}

	public String getBootAnimationUpdateTimeString() {
		return this.bootAnimationUpdateTimeString = DateUtil.formatDateToString(FORMAT_BOOT_ANIMATION_UPDATE_TIME, getBootAnimationUpdateTime());
	}
	public void setBootAnimationUpdateTimeString(String value) throws ParseException {
		setBootAnimationUpdateTime(DateUtil.stringConvertDate(FORMAT_BOOT_ANIMATION_UPDATE_TIME, value));
	}


	public String getDesktopApkVersion() {
		return desktopApkVersion;
	}

	public void setDesktopApkVersion(String desktopApkVersion) {
		this.desktopApkVersion = desktopApkVersion;
	}

	public Integer getGetTaskStatus() {
		return getTaskStatus;
	}

	public void setGetTaskStatus(Integer getTaskStatus) {
		this.getTaskStatus = getTaskStatus;
	}

	public Integer getSetTaskStatus() {
		return setTaskStatus;
	}

	public void setSetTaskStatus(Integer setTaskStatus) {
		this.setTaskStatus = setTaskStatus;
	}

	@JSONField(serialize = false)
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append("HostId",getHostId())
			.append("DeviceTypeId",getDeviceTypeId())
			.append("SerialNo",getSerialNo())			
			.append("SfwUpdTime",getSfwUpdTime())
			.append("Software",getSoftware())
			.append("Hardware",getHardware())
			.append("MacAddress",getMacAddress())
			.append("AreaId",getAreaId())
			.append("FirstContact",getFirstContact())
			.append("DataModelName",getDataModelName())
			.append("BootScreenId",getBootScreenId())
			.append("UserId",getUserId())
			.append("BizPassword",getBizPassword())
			.append("SynchTime",getSynchTime())
			.append("AccessType",getAccessType())
			.append("OltNo",getOltNo())
			.append("OltCounty",getOltCounty())
			.append("PonNo",getPonNo())
			.append("ScreenUpdateTime",getScreenUpdateTime())
			.append("UserStatus",getUserStatus())
			.append("UserStatusUpdateTime",getUserStatusUpdateTime())
			.append("BootAnimationId",getBootAnimationId())
			.append("BootAnimationUpdateTime",getBootAnimationUpdateTime())
			.append("DesktopApkVersion",getDesktopApkVersion())
			.append("GetTaskStatus",getGetTaskStatus())
			.append("SetTaskStatus",getSetTaskStatus())
			.toString();
	}


	
	@JSONField(serialize = false)
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getHostId())
			.toHashCode();
	}
	
	@JSONField(serialize = false)
	public boolean equals(Object obj) {
		if(obj instanceof CpeInfo == false) return false;
		if(this == obj) return true;
		CpeInfo other = (CpeInfo)obj;
		return new EqualsBuilder()
			.append(getHostId(),other.getHostId())
			.isEquals();
	}
	
	@JSONField(serialize = false)
	public Boolean areNotEmpty() {
		if(deviceTypeId != null){
			return true;
		}
		if(userId != null){
			return true;
		}
		if(userStatus != null){
			return true;
		}
		return false;
	}
}

