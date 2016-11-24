package persistence.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Call;
import model.Contact;
import model.ContactType;

public class StatementManager {

	private PreparedStatement preparedStatement;

	private Connection conn;

	public StatementManager() throws SQLException {
		// Open connection
		this.conn = SingletonConnection.getInstance().getConnection();
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
			System.err.println(e.getMessage());
		}
	}

	// Contacts
	public void getFilledContactByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getContactByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	public void getFilledUpdateContactStatement(Contact contact) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateContactStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;

	}

	public void getFilledSaveContactStatement(Contact contact) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertContactStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;

	}

	// Calls
	public void getFilledCallByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getCallByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;
	}

	public void getFilledUpdateCallStatement(Call call) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateCallStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;

	}

	public void getFilledSaveCallStatement(Call call) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertCallStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;

	}

	// Contact Types
	public void getFilledContactTypeByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getContactTypeByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;
	}

	public void getFilledSaveContactTypeStatement(ContactType contactType) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertContactTypeStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;
	}

	public void getFilledUpdateContactTypeStatement(ContactType contactType) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateContactTypeStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		// TODO
		this.preparedStatement = preparedStatement;
	}

	//
	private void getFilledMaxIdStatement(String table) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getLastIdStatement(table);
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		this.preparedStatement = preparedStatement;
	}

	public void getFilledMaxIdContacts() throws SQLException {
		getFilledMaxIdStatement("CONTACTS");
	}

	public void getFilledMaxIdCalls() throws SQLException {
		getFilledMaxIdStatement("CALLS");
	}

	public void getFilledMaxIdContactType() throws SQLException {
		getFilledMaxIdStatement("CONTACTSTYPES");
	}

}
