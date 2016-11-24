package persistence.database;

import java.sql.SQLException;
import model.Contact;
import persistence.IFacadeContactPersistence;

public class FacadeContactDataBase implements IFacadeContactPersistence {

	@Override
	public int getMaxContactId() {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Contact> resultSetManager = null;
		Contact contact = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledMaxIdContacts();
			resultSetManager = new ContactResultSetManager(stmFiller.executeQuery());
			contact = resultSetManager.next();
		} catch (SQLException e) {
			System.err.println("getMaxContactId Error: " + e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		if (contact != null) {
			return contact.getId();
		} else {
			return -1;
		}
	}

	@Override
	public Contact getContactById(int id) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Contact> resultSetManager = null;
		Contact contact = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledContactByIdStatement(id);
			resultSetManager = new ContactResultSetManager(stmFiller.executeQuery());
			contact = resultSetManager.next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return contact;
	}

	@Override
	public void saveContact(Contact contact) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledSaveContactStatement(contact);
			stmFiller.executeQuery();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public void updateContact(Contact contact) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledUpdateContactStatement(contact);
			stmFiller.executeQuery();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

}
