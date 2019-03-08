package chnp.manager.mvc.model.query;

import chnp.manager.common.entity.PaginationQuery;

public class TsKeyQuery extends PaginationQuery {

	private Integer id;
	
	private String name;
	
	private String chinese;
	
	private String descr;
	

	public TsKeyQuery() {
		
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
