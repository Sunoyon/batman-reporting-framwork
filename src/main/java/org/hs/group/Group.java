package org.hs.group;

import java.util.List;

public class Group {

	private String clause;
	private List<String> primaryFields;

	public Group(String clause, List<String> primaryFields) {
		this.clause = clause;
		this.primaryFields = primaryFields;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public List<String> getPrimaryFields() {
		return primaryFields;
	}

	public void setPrimaryFields(List<String> primaryFields) {
		this.primaryFields = primaryFields;
	}

}
