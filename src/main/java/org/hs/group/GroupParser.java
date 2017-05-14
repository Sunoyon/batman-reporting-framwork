package org.hs.group;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hs.util.Constants;

public class GroupParser {

	private static final GroupParser instance = new GroupParser();

	private GroupParser() {

	}

	public static GroupParser getInstance() {
		return instance;
	}

	public List<Group> getGroups(String sql) {
		List<Group> groups = new ArrayList<Group>();
		int fromIndex = sql.indexOf(Constants.GROUP_PREFIX);
		while (fromIndex != -1) {
			String grpClause = sql.substring(fromIndex,
					sql.indexOf(Constants.GROUP_SUFFIX, fromIndex) + Constants.GROUP_SUFFIX.length());
			String trimmedClause = grpClause
					.substring(Constants.GROUP_PREFIX.length(),
							grpClause.indexOf(Constants.GROUP_SUFFIX, Constants.GROUP_SUFFIX.length()))
					.trim().replaceAll("\\s+", " ");
			List<String> primaryFields = getPrimaryFields(trimmedClause);
			groups.add(new Group(grpClause, primaryFields));
			fromIndex = sql.indexOf(Constants.GROUP_PREFIX, fromIndex + Constants.GROUP_PREFIX.length());
		}
		return groups;
	}

	public List<String> getPrimaryFields(String clause) {
		List<String> primaryFields = new ArrayList<String>();
		if (StringUtils.isEmpty(clause)) {
			return primaryFields;
		}
		int endIndex = clause.indexOf("%");
		if (endIndex != -1) {
			String[] fields = clause.substring(0, endIndex).trim().split(",", 0);
			for (String field : fields) {
				primaryFields.add(field.trim().replaceAll("\\s+", " "));
			}
		}
		return primaryFields;
	}
}
