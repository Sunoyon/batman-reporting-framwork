package org.hs.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hs.datasource.config.DataSourceConfig;
import org.hs.util.Constants;
import org.yaml.snakeyaml.Yaml;

public class DataSourceConfigurator {

	@SuppressWarnings("unchecked")
	public static List<DataSourceConfig> getDataSourceConfig() {
		List<DataSourceConfig> dsConfig = new ArrayList<DataSourceConfig>();
		Yaml yaml = new Yaml();
		String dbConfLoc = Constants.DB_CONFIG_LOCATION;

		Map<String, Object> yamlParsers = (Map<String, Object>) yaml
				.load(DataSourceConfigurator.class.getClassLoader().getResourceAsStream(dbConfLoc));

		List<Map<String, Object>> dataSources = (List<Map<String, Object>>) yamlParsers.get("data-sources");
		for (Map<String, Object> ds : dataSources) {
			dsConfig.add(new DataSourceConfig((String) ds.get("name"), (String) ds.get("db-url"),
					(String) ds.get("db-user"), (String) ds.get("db-password"), (Integer) ds.get("db-max-connection"),
					(String) ds.get("db-type")));
		}
		return dsConfig;

	}
}
