package chnp.manager.mvc.model.domain;

public class Template {

	private Integer id;
	private Integer orm;
	private String template;
	private Integer engine;
	private String outputFile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrm() {
		return orm;
	}

	public void setOrm(Integer orm) {
		this.orm = orm;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getEngine() {
		return engine;
	}

	public void setEngine(Integer engine) {
		this.engine = engine;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
}