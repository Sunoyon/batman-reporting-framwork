package org.hs.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

	public Connection getConnection() throws SQLException;

	public void close();

}
