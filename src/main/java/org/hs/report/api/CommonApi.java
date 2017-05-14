package org.hs.report.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.hs.datasource.provider.DataSourceConnectionProvider;
import org.hs.query.ReportQueryProvider;

public class CommonApi {

	private static ReportQueryProvider reportQueries = ReportQueryProvider.getInstance();

	public static List<Map<String, Object>> getReport(String name, Map<String, Object> queryParam,
			List<String> inputParamGroup) {
		Connection connection = null;
		try {
			String reportDataSourceName = reportQueries.getReportDataSourceName(name);
			connection = DataSourceConnectionProvider.getInstance().getDataSourceConnection(reportDataSourceName);
			QueryRunner query = new QueryRunner();
			String sql = reportQueries.getReportQuery(name, queryParam, inputParamGroup);
			return query.query(connection, sql, new MapListHandler());

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(connection);
		}

	}

}