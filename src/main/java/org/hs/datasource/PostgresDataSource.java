package org.hs.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.hs.datasource.config.DataSourceConfig;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

public class PostgresDataSource implements DataSource {

	private Jdbc3PoolingDataSource datasoure;

	public PostgresDataSource(DataSourceConfig conf) {
		datasoure = new Jdbc3PoolingDataSource();
		datasoure.setDataSourceName(conf.getName());
		datasoure.setUrl(conf.getDbUrl());
		datasoure.setUser(conf.getDbUser());
		datasoure.setPassword(conf.getDbPassword());
		datasoure.setMaxConnections(conf.getMaxConnection());
	}

	@Override
	public Connection getConnection() throws SQLException {
		return datasoure.getConnection();
	}

	@Override
	public void close() {
		datasoure.close();
	}

}
