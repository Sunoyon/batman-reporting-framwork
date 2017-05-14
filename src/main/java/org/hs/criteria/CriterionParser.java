package org.hs.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hs.util.Constants;

public class CriterionParser {

	private static final CriterionParser instance = new CriterionParser();

	private CriterionParser() {

	}

	public static CriterionParser getInstance() {
		return instance;
	}

	public List<Criterion> getCriteria(String sql) {
		List<Criterion> criteria = new ArrayList<Criterion>();
		int fromIndex = sql.indexOf(Constants.CRITERION_PREFIX);
		while (fromIndex != -1) {
			String criterionClause = sql.substring(fromIndex,
					sql.indexOf(Constants.CRITERION_SUFFIX, fromIndex) + Constants.CRITERION_SUFFIX.length());
			String trimmedClause = criterionClause
					.substring(Constants.CRITERION_PREFIX.length(),
							criterionClause.indexOf(Constants.CRITERION_SUFFIX, Constants.CRITERION_SUFFIX.length()))
					.trim().replaceAll("\\s+", " ");
			String criterionName = CriterionUtils.getCriterionName(trimmedClause);
			List<String> criterionParams = CriterionUtils.getKeyParamsFromClause(trimmedClause);
			String queryClause = trimmedClause.substring(criterionName.length() + 1);
			criteria.add(new Criterion(criterionName, criterionClause, queryClause, criterionParams));
			fromIndex = sql.indexOf(Constants.CRITERION_PREFIX, fromIndex + Constants.CRITERION_PREFIX.length());
		}
		return criteria;
	}

	public String getCriterionQuery(String criterionClause, Map<String, Object> params) {
		String criterionClauseTrimmed = criterionClause.trim().replaceAll("\\s+", " ");
		List<String> keyParams = CriterionUtils.getKeyParamsFromClause(criterionClauseTrimmed);
		if (!CriterionUtils.isKeysExist(keyParams, params)) {
			return "true";
		}
		String queryClause = criterionClauseTrimmed
				.substring(CriterionUtils.getCriterionName(criterionClauseTrimmed).length() + 1);
		return CriterionUtils.strSubstitutor(queryClause, keyParams, "%", "", params);
	}

}
