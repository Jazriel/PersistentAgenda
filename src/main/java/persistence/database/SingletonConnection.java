package persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase SingletonConnection
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class SingletonConnection {
	/**
	 * Guardamos la conexión de la BD.
	 */
	private static SingletonConnection connectionInstance;
	/**
	 * guardamos conexion con la BD
	 */
	private Connection connection;

	/**
	 * SingletonConnection().COnstructor de clase.
	 * 
	 * @throws SQLException.
	 */
	private SingletonConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
	}

	/**
	 * getInstance(). MEtodo para coger la instancia de nuestra BD.
	 * 
	 * @return connectionInstance.
	 * @throws SQLException
	 *             excepción.
	 */
	public static SingletonConnection getInstance() throws SQLException {
		if (connectionInstance == null)
			connectionInstance = new SingletonConnection();
		return connectionInstance;
	}

	/**
	 * getConnection().Metodo que recoge la conexión.
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * close(). Metodo que cierra la conexión en la BD.
	 */
	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			connection = null;
			connectionInstance = null;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}
