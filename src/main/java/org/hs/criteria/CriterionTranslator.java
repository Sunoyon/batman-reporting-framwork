package org.hs.criteria;

import java.util.List;
import java.util.Map;

import org.hs.util.AppStringUtils;

public class CriterionTranslator {

	private static final CriterionTranslator instance = new CriterionTranslator();

	private CriterionTranslator() {

	}

	public static CriterionTranslator getInstance() {
		return instance;
	}

	public void translate(StringBuffer buf, List<Criterion> criteria, Map<String, Object> queryParam) {
		for (Criterion criterion : criteria) {
			String strSubstitutor = "true";
			if (CriterionUtils.isKeysExist(criterion.getParams(), queryParam)) {
				strSubstitutor = CriterionUtils.strSubstitutor(criterion.getQueryClause(), criterion.getParams(), "%",
						"", queryParam);
			}
			AppStringUtils.replaceAll(buf, criterion.getClause(), strSubstitutor);
		}

	}
}
