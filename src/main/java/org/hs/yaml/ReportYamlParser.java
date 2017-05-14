package org.hs.yaml;

import java.io.IOException;
import java.util.Map;

import org.hs.util.Constants;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportYamlParser {

	private static final ReportYamlParser instance = new ReportYamlParser();
	private static Yaml yaml;

	private ReportYamlParser() {
		yaml = new Yaml();
	}

	public static ReportYamlParser getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getYamlConfig(String yamlFile) {
		return (Map<String, Object>) yaml
				.load(ReportYamlParser.class.getClassLoader().getResourceAsStream(yamlFile));
	}

	public String getSql(Map<String, Object> yaml) {
		Object sql = yaml.get(Constants.SQLKEY);
		if (sql != null) {
			return (String) sql;
		}
		return null;
	}

	public String getDataSource(Map<String, Object> yaml) {
		Object dataSource = yaml.get(Constants.DATASOURCEKEY);
		if (dataSource != null) {
			return (String) dataSource;
		}
		return null;
	}

	public Map<String, String> getTranslatorMap(Map<String, Object> yaml) throws JsonParseException, JsonMappingException, IOException {
		Object tsMap = yaml.get(Constants.TRANSLATORMAP);
		if (tsMap != null) {
			ObjectMapper mapper = new ObjectMapper();
			String tsMapStr = (String) tsMap;
			return mapper.readValue(tsMapStr, new TypeReference<Map<String, String>>(){});
		}
		return null;
	}
}
