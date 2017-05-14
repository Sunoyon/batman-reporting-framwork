package org.hs.datasource.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hs.config.DataSourceConfigurator;
import org.hs.datasource.DataSource;
import org.hs.datasource.MysqlDataSource;
import org.hs.datasource.PostgresDataSource;
import org.hs.datasource.config.DataSourceConfig;
import org.hs.util.Constants;

public class DataSourceConnectionProvider {

	private static final DataSourceConnectionProvider instance = new DataSourceConnectionProvider();
	private static Map<String, DataSource> datasources;

	private DataSourceConnectionProvider() {

		datasources = new HashMap<String, DataSource>();
		List<DataSourceConfig> dsConfs = DataSourceConfigurator.getDataSourceConfig();

		for (DataSourceConfig dsConf : dsConfs) {
			if (!datasources.containsKey(dsConf.getName())) {
				if (dsConf.getDbType().equalsIgnoreCase(Constants.DB_TYPE_POSTGRESQL)) {
					datasources.put(dsConf.getName(), new PostgresDataSource(dsConf));
				} else if (dsConf.getDbType().equalsIgnoreCase(Constants.DB_TYPE_MYSQL)) {
					datasources.put(dsConf.getName(), new MysqlDataSource(dsConf));
				}
			}
		}
	}

	public static DataSourceConnectionProvider getInstance() {
		return instance;
	}

	public Connection getDataSourceConnection(String dsName) throws SQLException {
		if (datasources.containsKey(dsName)) {
			return datasources.get(dsName).getConnection();
		}
		throw new SQLException("Data Source Not found");
	}

	public void closeAll() {
		for (Map.Entry<String, DataSource> entry : datasources.entrySet()) {
			entry.getValue().close();
		}
	}

}
