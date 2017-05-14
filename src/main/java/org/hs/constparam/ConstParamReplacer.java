package org.hs.constparam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hs.criteria.CriterionUtils;
import org.hs.util.Constants;
import org.hs.util.AppStringUtils;

public class ConstParamReplacer {

	private static final ConstParamReplacer instance = new ConstParamReplacer();

	private ConstParamReplacer() {

	}

	public static ConstParamReplacer getInstance() {
		return instance;
	}

	public void replaceAllConstParam(StringBuffer buf, List<String> constParams, Map<String, Object> queryParams) {

		for (String constParam : constParams) {
			String valueStr = CriterionUtils.getParamValueStr(queryParams.get(constParam));
			AppStringUtils.replaceAll(buf,
					StringUtils.join(
							Arrays.asList(Constants.CONSTANT_PARAM_PREFIX, constParam, Constants.CONSTANT_PARAM_SUFFIX),
							""),
					valueStr);
		}

	}

}
