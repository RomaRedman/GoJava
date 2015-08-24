package goit.nz.kickstarter.storage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class PostgreStorage implements DataStorage {
	private DataSource dbSource;

	@Override
	public Connection getConnection() throws SQLException {
		return dbSource.getConnection();
	}

	@Override
	public void init(String propertyFile) {
		Properties dbProps = getDBProperties(propertyFile);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbProps.getProperty("driver"));
		dataSource.setUrl(dbProps.getProperty("url"));
		dataSource.setUsername(dbProps.getProperty("user"));
		dataSource.setPassword(dbProps.getProperty("password"));
		dataSource.setDefaultAutoCommit(false);
		dbSource = dataSource;
		try {
			checkConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Problem with DB connectivity!", e);
		}
	}
	
	private void checkConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = dbSource.getConnection();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private Properties getDBProperties(String propertyFile) {
		Properties props = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		try (InputStream input = classLoader.getResourceAsStream(propertyFile)) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

}
