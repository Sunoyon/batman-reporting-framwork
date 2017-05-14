package org.hs.yaml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hs.util.Constants;
import org.hs.yaml.ReportYamlParser;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ReportYamlParserTest {

	private static ReportYamlParser yaml;

	@BeforeClass
	public static void init() {
		yaml = ReportYamlParser.getInstance();
	}

	@Test
	public void getTranslatorMapTest() throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> config = new HashMap<String, Object>();
		String tsMap = "{" +
                "\"si.mId\": \"mId\"," +
                "\"si.value as sg\": \"sg\"," + 
                "\"si.prob\": \"prob\"," + 
                "\"si.value\": \"sg\"" +
              "}";
		config.put(Constants.TRANSLATORMAP, tsMap);
		Map<String, String> translatorMap = yaml.getTranslatorMap(config);
		for (Map.Entry<String, String> entry : translatorMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	@Test
	public void getTranslatorMapTest1() throws JsonParseException, JsonMappingException, IOException {
		String reportFile = "reports/v1/examples/example1.yaml";
		Map<String, Object> yamlConfig = yaml.getYamlConfig(reportFile);
		Map<String, String> translatorMap = yaml.getTranslatorMap(yamlConfig);
		for (Map.Entry<String, String> entry : translatorMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
