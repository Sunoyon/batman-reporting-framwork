package org.hs.group;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hs.group.Group;
import org.hs.group.GroupParser;
import org.hs.group.GroupTranslator;
import org.hs.util.Constants;
import org.hs.yaml.ReportYamlParser;
import org.junit.BeforeClass;
import org.junit.Test;

public class GroupTranslatorTest {

	private static String sql;
	private static List<Group> groups;
	private static Map<String, String> translatorMap;

	@BeforeClass
	public static void init() throws Exception {
		sql = "SELECT [si.mId, si.value as sg, si.prob %groupFields], "
				+ "count(distinct si.userid) as uu, count(distinct id) as ids "
				+ "FROM sg_inv si " + "JOIN items l ON si.item = l.item "
				+ "WHERE {created TO_CHAR(si.created, 'YYYY-MM-DD') BETWEEN %startDate AND %endDate} "
				+ "GROUP BY [si.mId, si.value, si.prob %groupFields]";
		groups = GroupParser.getInstance().getGroups(sql);
		
		Map<String, Object> config = new HashMap<String, Object>();
		String tsMap = "{" + "\"si.mId\": \"mId\"," + "\"si.value as sg\": \"sg\","
				+ "\"si.prob\": \"prob\"," + "\"si.value\": \"sg\"" + "}";
		config.put(Constants.TRANSLATORMAP, tsMap);
		translatorMap = ReportYamlParser.getInstance().getTranslatorMap(config);
	}

	@Test
	public void translateTest() {
		StringBuffer buffer = new StringBuffer(sql);
		List<String> inputParams = Arrays.asList("mId", "sg", "prob");

		GroupTranslator.getInstance().translate(buffer, groups, translatorMap, inputParams);
		System.out.println(buffer);
	}
	
	@Test
	public void translateTest1() {
		StringBuffer buffer = new StringBuffer(sql);
		List<String> inputParams = Arrays.asList("mId", "prob");
		
		GroupTranslator.getInstance().translate(buffer, groups, translatorMap, inputParams);
		System.out.println(buffer);
	}
	
	@Test
	public void translateTest2() {
		StringBuffer buffer = new StringBuffer(sql);
		List<String> inputParams = Arrays.asList("mId", "sg", "prob");
		translatorMap = null;
		GroupTranslator.getInstance().translate(buffer, groups, translatorMap, inputParams);
		System.out.println(buffer);
	}

}
