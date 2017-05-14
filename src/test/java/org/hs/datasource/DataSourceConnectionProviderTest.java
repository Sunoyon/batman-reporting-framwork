package org.hs.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.hs.datasource.provider.DataSourceConnectionProvider;
import org.junit.Test;

public class DataSourceConnectionProviderTest {

	@Test
	public void dataSourceConnectionProviderTest() throws SQLException {
		Connection dataSourceConnection = DataSourceConnectionProvider.getInstance().getDataSourceConnection("sale4ReportDs");
		System.out.println(dataSourceConnection.getMetaData());
		dataSourceConnection.close();
	}

	@Test
	public void mySqlDataSourceTest() throws SQLException {
		Connection dataSourceConnection = DataSourceConnectionProvider.getInstance()
				.getDataSourceConnection("sale1ReportDs");
		System.out.println(dataSourceConnection.getMetaData());
		dataSourceConnection.close();
	}
}
