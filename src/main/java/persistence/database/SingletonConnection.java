package persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection implements AutoCloseable {
	private static SingletonConnection connectionInstance;

	private Connection connection;

	private SingletonConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:file:/opt/db/testdb", "SA", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static SingletonConnection getInstance() {
		if (connectionInstance == null)
			connectionInstance = new SingletonConnection();
		return connectionInstance;
	}

	@Override
	public void close() throws Exception {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
		connection = null;
	    connectionInstance = null;
	}

}
