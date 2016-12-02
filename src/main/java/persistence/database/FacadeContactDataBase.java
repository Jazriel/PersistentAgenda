package persistence.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import persistence.IFacadeContactPersistence;

public class FacadeContactDataBase implements IFacadeContactPersistence {


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
			if (resultSetManager.hasNext()) {
				contact = resultSetManager.next();
			}
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
			stmFiller.executeUpdate();
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
			stmFiller.executeUpdate();
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
	public List<Contact> getAllContacts() {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Contact> resultSetManager = null;
		List<Contact> contacts = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getAllContactsStatement();
			resultSetManager = new ContactResultSetManager(stmFiller.executeQuery());
			contacts = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				contacts.add(resultSetManager.next());
			}
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
		return contacts;
	}

	@Override
	public List<Contact> getOrderContacts(String field) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Contact> resultSetManager = null;
		List<Contact> contacts = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getOrderContactsStatement(field);
			resultSetManager = new ContactResultSetManager(stmFiller.executeQuery());
			contacts = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				contacts.add(resultSetManager.next());
			}
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
		return contacts;
}

	@Override
	public List<Contact> getFilterContacts(String field, String filteredField) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Contact> resultSetManager = null;
		List<Contact> contacts = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilterContactsStatement(field, filteredField);
			resultSetManager = new ContactResultSetManager(stmFiller.executeQuery());
			contacts = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				contacts.add(resultSetManager.next());
			}
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
		return contacts;

	}

}
