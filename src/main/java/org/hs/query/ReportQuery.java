package org.hs.query;

import java.util.List;
import java.util.Map;

import org.hs.criteria.Criterion;
import org.hs.group.Group;

public class ReportQuery {

	private String sql;
	private List<Criterion> criteria;
	private List<String> constantParams;
	private String dataSourceName;
	private List<Group> groups;
	private Map<String, String> groupTranslatorMap;

	public ReportQuery(String sql, List<Criterion> criteria, List<String> constantParams, String dataSourceName,
			List<Group> groups, Map<String, String> groupTranslatorMap) {
		this.sql = sql;
		this.criteria = criteria;
		this.constantParams = constantParams;
		this.dataSourceName = dataSourceName;
		this.groups = groups;
		this.groupTranslatorMap = groupTranslatorMap;
	}

	public Map<String, String> getGroupTranslatorMap() {
		return groupTranslatorMap;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public String getSql() {
		return sql;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public List<String> getConstantParams() {
		return constantParams;
	}

	public List<Group> getGroups() {
		return groups;
	}

}
