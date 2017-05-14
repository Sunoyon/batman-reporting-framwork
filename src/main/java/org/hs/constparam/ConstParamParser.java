package org.hs.constparam;

import java.util.ArrayList;
import java.util.List;

import org.hs.util.Constants;

public class ConstParamParser {

	private static final ConstParamParser instance = new ConstParamParser();

	private ConstParamParser() {

	}

	public static ConstParamParser getInstance() {
		return instance;
	}

	public List<String> getConstantParams(String sql) {
		List<String> constParams = new ArrayList<String>();
		int fromIndex = sql.indexOf(Constants.CONSTANT_PARAM_PREFIX);
		while (fromIndex != -1) {
			String constParam = sql.substring(fromIndex + Constants.CONSTANT_PARAM_PREFIX.length(),
					sql.indexOf(Constants.CONSTANT_PARAM_SUFFIX, fromIndex));
			constParams.add(constParam);
			fromIndex = sql.indexOf(Constants.CONSTANT_PARAM_PREFIX,
					fromIndex + Constants.CONSTANT_PARAM_PREFIX.length());
		}
		return constParams;
	}
}
