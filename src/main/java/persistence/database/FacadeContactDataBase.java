package persistence.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import persistence.IFacadeContactPersistence;
/**
 * Clase FacadeContactDataBase
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez 
 */
public class FacadeContactDataBase implements IFacadeContactPersistence {

	/**
	 * getContactById(int id). Metodo que devuelve un contacto por un id
	 * @param id, el id de la contacto.
	 * @return contact
	 */
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
	/**
	 * saveContact(Contact contact)). Metodo que guarda una contacto.
	 * @param contact, el contacto que guardaremos.
	 */
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
	/**
	 * updateContact(Contact contact). Metodo que actualiza un contacto con nuevos datos.
	 * @param contact, un contacto.
	 */
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
	/**
	 * getAllContacts(). Metodo que coge todos los contactos
	 * @return contacts
	 */
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
	/**
	 * getOrderContacts(String field). Metodo que coge los cotactos ordenados por campo.
	 * @param field, campo por el que ordenar
	 * @return contacts
	 */
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
	/**
	 * getFilterContacts(String field, String filteredField). Metodo que filtra los contactos.
	 * @param field, campo por el que filtrar. 
	 * @param filteredField,valor por el que filtramos.
	 * @return contacts
	 */
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
