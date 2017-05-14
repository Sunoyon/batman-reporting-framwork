package org.hs.config;

import org.hs.datasource.provider.DataSourceConnectionProvider;
import org.hs.query.ReportQueryProvider;

public class AppConfigurator {

	public static void init() {
		DataSourceConnectionProvider.getInstance();
		ReportQueryProvider.getInstance();
	}
}
