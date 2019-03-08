package chnp.manager.generator.model.domain;

public class GnrFilepath {

	public static final String ALIAS_ID = "记录ID";
	public static final String ALIAS_PROJECT_ID = "项目ID";
	public static final String ALIAS_OUTPUT_PATH = "输出路径";
	public static final String ALIAS_FILE_SUFFIX = "文件后缀";
	public static final String ALIAS_CREATE_USER = "创建者";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFIED_TIME = "更新时间";

	private Integer id;
	
	private Integer projectId;
	
	private String outputPath;
	
	private String fileSuffix;
	
	private Integer createUser;
	
	private java.util.Date createTime;
	
	private String createTimeString;
	
	private java.util.Date modifiedTime;
	
	private String modifiedTimeString;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
		this.createTimeString = getCreateTimeString();
	}

	public String getCreateTimeString() {
		if (null == createTime) {
			return null;
		}
		return this.createTimeString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
	}

	public void setCreateTimeString(String createTimeString) throws java.text.ParseException {
		if (null !=  createTimeString && !"".equals( createTimeString)) {
			setCreateTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTimeString));
		}
	}
	
	public java.util.Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(java.util.Date modifiedTime) {
		this.modifiedTime = modifiedTime;
		this.modifiedTimeString = getModifiedTimeString();
	}

	public String getModifiedTimeString() {
		if (null == modifiedTime) {
			return null;
		}
		return this.modifiedTimeString = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(modifiedTime);
	}

	public void setModifiedTimeString(String modifiedTimeString) throws java.text.ParseException {
		if (null !=  modifiedTimeString && !"".equals( modifiedTimeString)) {
			setModifiedTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(modifiedTimeString));
		}
	}
	
	
	
	// Constructor
	public GnrFilepath() {
		
	}
}
