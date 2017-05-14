package org.hs.criteria;

import java.util.List;

public class Criterion {
	
	private String name;
	private String clause;
	private String queryClause;
	private List<String> params;
	
	
	public Criterion(String name, String clause, String queryClause, List<String> params) {
		this.name = name;
		this.clause = clause;
		this.queryClause = queryClause;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public String getQueryClause() {
		return queryClause;
	}

	public void setQueryClause(String queryClause) {
		this.queryClause = queryClause;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

}
