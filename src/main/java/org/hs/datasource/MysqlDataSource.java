package org.hs.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hs.datasource.config.DataSourceConfig;

public class MysqlDataSource implements DataSource {

	private final static Logger logger = Logger.getLogger(MysqlDataSource.class);
	private BasicDataSource datasource;

	public MysqlDataSource(DataSourceConfig conf) {
		datasource = new BasicDataSource();
		datasource.setUrl(conf.getDbUrl());
		datasource.setUsername(conf.getDbUser());
		datasource.setPassword(conf.getDbPassword());
		datasource.setInitialSize(conf.getMaxConnection());
		datasource.setMaxActive(conf.getMaxConnection());
		datasource.setMaxIdle(conf.getMaxConnection());
	}

	@Override
	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	@Override
	public void close() {
		try {
			datasource.close();
		} catch (SQLException e) {
			logger.error("Error in closing mysql-Datasource." + e.getMessage());
		}
	}

}
