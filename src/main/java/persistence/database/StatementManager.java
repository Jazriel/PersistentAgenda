package persistence.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public void executeUpdate() throws SQLException {
		preparedStatement.executeUpdate();
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
		preparedStatement.setString(1, contact.getName());
		preparedStatement.setString(2, contact.getSurname());
		preparedStatement.setString(3, contact.getTitle());
		preparedStatement.setString(4, contact.getAddress());
		preparedStatement.setString(5, contact.getCity());
		preparedStatement.setString(6, contact.getProvince());
		preparedStatement.setString(7, contact.getPostalCode());
		preparedStatement.setString(8, contact.getRegion());
		preparedStatement.setString(9, contact.getCountry());
		preparedStatement.setString(10, contact.getCompanyName());
		preparedStatement.setString(11, contact.getWorkstation());
		preparedStatement.setString(12, contact.getWorkPhone());
		preparedStatement.setString(13, contact.getWorkExtension());
		preparedStatement.setString(14, contact.getMobilePhone());
		preparedStatement.setString(15, contact.getFaxNumber());
		preparedStatement.setString(16, contact.getEmail());
		int i = -1;
		if (contact != null) {
			ContactType ct = contact.getContactType();
			if (ct != null) {
				i = ct.getId();
			}
		}
		preparedStatement.setInt(17, i);
		preparedStatement.setString(18, contact.getNotes());
		preparedStatement.setInt(19, contact.getId());
		this.preparedStatement = preparedStatement;

	}

	public void getFilledSaveContactStatement(Contact contact) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertContactStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, contact.getId());
		preparedStatement.setString(2, contact.getName());
		preparedStatement.setString(3, contact.getSurname());
		preparedStatement.setString(4, contact.getTitle());
		preparedStatement.setString(5, contact.getAddress());
		preparedStatement.setString(6, contact.getCity());
		preparedStatement.setString(7, contact.getProvince());
		preparedStatement.setString(8, contact.getPostalCode());
		preparedStatement.setString(9, contact.getRegion());
		preparedStatement.setString(10, contact.getCountry());
		preparedStatement.setString(11, contact.getCompanyName());
		preparedStatement.setString(12, contact.getWorkstation());
		preparedStatement.setString(13, contact.getWorkPhone());
		preparedStatement.setString(14, contact.getWorkExtension());
		preparedStatement.setString(15, contact.getMobilePhone());
		preparedStatement.setString(16, contact.getFaxNumber());
		preparedStatement.setString(17, contact.getEmail());
		int i = -1;
		if (contact != null) {
			ContactType ct = contact.getContactType();
			if (ct != null) {
				i = ct.getId();
			}
		}
		preparedStatement.setInt(18, i);
		preparedStatement.setString(19, contact.getNotes());
		this.preparedStatement = preparedStatement;

	}

	// Calls
	public void getFilledCallByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getCallsByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	public void getFilledUpdateCallStatement(Call call) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateCallStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		preparedStatement.setString(1, call.getSubject());
		preparedStatement.setString(2, call.getNotes());
		preparedStatement.setInt(3, call.getId());
		this.preparedStatement = preparedStatement;

	}

	public void getFilledSaveCallStatement(Call call) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertCallStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, call.getId());
		preparedStatement.setInt(2, call.getContact().getId());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(call.getCallDate().substring(0, 10));
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		long time = date.getTime();
		Timestamp timeStamp = new Timestamp(time);
		preparedStatement.setTimestamp(3, timeStamp);
		preparedStatement.setString(4, call.getSubject());
		preparedStatement.setString(5, call.getNotes());
		this.preparedStatement = preparedStatement;

	}

	// Contact Types
	public void getFilledContactTypeByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getContactTypeByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	public void getFilledSaveContactTypeStatement(ContactType contactType) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertContactTypeStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setString(1, contactType.getContactTypeName());
		this.preparedStatement = preparedStatement;
	}

	public void getFilledUpdateContactTypeStatement(ContactType contactType) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateContactTypeStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setString(1, contactType.getContactTypeName());
		preparedStatement.setInt(2, contactType.getId());
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

	public void getOrderCallsStatement(String field) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getOrderCallsStatement(field);
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	private PreparedStatement getFilterCallsStamentGeneral(String field) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getFilteredCallsStatement(field);
		// Create PreparedStatement
		return conn.prepareStatement(textStatement);
	}

	public void getFilterCallsStatement(String field, int id) throws SQLException {
		PreparedStatement preparedStatement = getFilterCallsStamentGeneral(field);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	public void getFilterCallsStatement(String field, Timestamp timeStamp) throws SQLException {
		PreparedStatement preparedStatement = getFilterCallsStamentGeneral(field);
		// Prepare statement
		preparedStatement.setTimestamp(1, timeStamp);
		this.preparedStatement = preparedStatement;
	}

	public void getAllCallsStatement() throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getAllCallsStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	public void getAllContactsStatement() throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getAllContacts();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	public void getAllContactTypesStatement() throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getAllContactsTypes();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	public void getOrderContactsStatement(String field) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getOrderContactsStatement(field);
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	public void getFilterContactsStatement(String field, String filteredField) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getFilteredContactsStatement(field);
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setString(1, filteredField);
		this.preparedStatement = preparedStatement;
	}

}
