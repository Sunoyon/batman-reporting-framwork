package org.hs.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hs.util.AppStringUtils;

public class CriterionUtils {

	public static boolean isKeysExist(List<String> keys, Map<String, Object> params) {
		if (keys.isEmpty() || MapUtils.isEmpty(params)) {
			return false;
		}
		for (String key : keys) {
			if (!params.containsKey(key)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getParamStr(List<String> keys, Map<String, Object> params) {
		Map<String, String> paramStr = new HashMap<String, String>();
		for (String key : keys) {
			Object value = params.get(key);
			String valueStr = (value instanceof List)
					? String.format("(%s)", StringUtils.join((List<Object>) value, ",")) : String.valueOf(value);
			paramStr.put(key, valueStr);
		}
		return paramStr;
	}

	public static List<String> getKeyParamsFromClause(String criterionClause) {
		String[] split = criterionClause.split(" ", 0);
		List<String> paramKeys = new ArrayList<String>();
		for (String splitstr : split) {
			if (splitstr.startsWith("%")) {
				paramKeys.add(splitstr.substring(1));
			}
		}
		return paramKeys;
	}

	public static String getCriterionName(String criterionClause) {
		String[] params = criterionClause.split(" ", 0);
		if (params.length < 1) {
			return "";
		}
		return params[0];
	}

	@SuppressWarnings("unchecked")
	public static String getParamValueStr(Object value) {
		if (value == null) {
			return "''";
		} else if (value instanceof String) {
			return String.format("'%s'", String.valueOf(value));
		} else if (value instanceof List) {
			if (((List<Object>) value).get(0) instanceof String) {
				return String.format("('%s')", StringUtils.join((List<Object>) value, "','"));
			} else {
				return String.format("(%s)", StringUtils.join((List<Object>) value, ","));
			}
		} else {
			return String.valueOf(value);
		}
	}

	public static String strSubstitutor(String string, List<String> keys, String keyPrefix, String keySuffix,
			Map<String, Object> params) {
		StringBuffer replace = new StringBuffer(string);
		for (String key : keys) {
			Object value = params.get(key);
			String valueStr = getParamValueStr(value);
			AppStringUtils.replaceAll(replace, keyPrefix.concat(key).concat(keySuffix), valueStr);
		}
		return replace.toString();
	}

}
