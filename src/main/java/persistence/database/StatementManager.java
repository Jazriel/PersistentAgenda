package persistence.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Contact;

public class StatementManager {

	private PreparedStatement preparedStatement;

	public void getFilledContactByIdStatement(int id) throws SQLException {
		// Open connection
		Connection conn = SingletonConnection.getInstance().getConnection();
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getContactByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	public void getFilledUpdateContactStatement(Contact contact) throws SQLException {
		// Open connection
		Connection conn = SingletonConnection.getInstance().getConnection();
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateContactStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		//preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
		
	}

	public void getFilledSaveContactStatement(Contact contact) throws SQLException {
		// Open connection
		Connection conn = SingletonConnection.getInstance().getConnection();
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertContactStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		//preparedStatement.setInt(1, id);
		// TODO
		this.preparedStatement = preparedStatement;
		
	}

	public ResultSet executeQuery() throws SQLException {
		return preparedStatement.executeQuery();
	}

	public void close() {
		try {
			if (preparedStatement != null && !preparedStatement.isClosed())
				preparedStatement.close();
			preparedStatement = null;
		} catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}
	}


}
