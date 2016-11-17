package persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	private static SingletonConnection connectionInstance;

	private Connection connection;

	private SingletonConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
	}

	public static SingletonConnection getInstance() throws SQLException {
		if (connectionInstance == null)
			connectionInstance = new SingletonConnection();
		return connectionInstance;
	}

	public Connection getConnection() {
		return connection;
	}

	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			connection = null;
			connectionInstance = null;
		} catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}
	}

}
