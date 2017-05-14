package org.hs.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hs.util.AppStringUtils;

public class GroupTranslator {

	private static final GroupTranslator instance = new GroupTranslator();

	private GroupTranslator() {
	}

	public static GroupTranslator getInstance() {
		return instance;
	}

	public void translate(StringBuffer buf, List<Group> groups, Map<String, String> translatorMap,
			List<String> inputParam) {
		for (Group group : groups) {
			AppStringUtils.replaceAll(buf, group.getClause(), getReplaceGroup(group, translatorMap, inputParam));
		}
	}

	private String getReplaceGroupField(String primaryField, Map<String, String> translatorMap,
			List<String> inputParam) {
		if (MapUtils.isEmpty(translatorMap) || CollectionUtils.isEmpty(inputParam)) {
			return primaryField;
		}
		String translatorField = translatorMap.get(primaryField);
		if (StringUtils.isNotEmpty(translatorField) && inputParam.contains(translatorField)) {
			return primaryField;
		}
		return null;
	}

	private String getReplaceGroup(Group group, Map<String, String> translatorMap,
			List<String> inputParam) {
		List<String> replaceFields = new ArrayList<String>();
		for (String field : group.getPrimaryFields()) {
			String replaceGroupField = getReplaceGroupField(field, translatorMap, inputParam);
			CollectionUtils.addIgnoreNull(replaceFields, replaceGroupField);
		}
		if (CollectionUtils.isEmpty(replaceFields)) {
			return StringUtils.join(group.getPrimaryFields(), ", ");
		}
		return StringUtils.join(replaceFields, ", ");
	}
}
