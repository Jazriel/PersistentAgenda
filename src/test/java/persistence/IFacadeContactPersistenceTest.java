package persistence;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Contact;
import persistence.database.ABCResultSetManager;
import persistence.database.ContactResultSetManager;
import persistence.database.SingletonConnection;
import persistence.database.StatementManager;
import persistence.util.PrintAtDepth;

public class IFacadeContactPersistenceTest {

	private List<IFactoryPersistence> persistences;

	@Before
	public void beforeTest() {
		PrintAtDepth.print(0, "FacadeContactPersistenceTest start");
		IAbstractPersistenceFactory aPersistenceFactory = new AbstractPersistenceFactory();
		persistences = new ArrayList<>();
		persistences.add(aPersistenceFactory.getBinPersistence());
		persistences.add(aPersistenceFactory.getDBPersistence());
	}

	@Test
	public void testSaveContact() {
		PrintAtDepth.print(1, "saveContact start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getMaxContactId();
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			try {
				Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM CONTACTS ORDER BY id DESC");
				rs.next();
				while (id != rs.getInt(1)) {
					rs.next();
				}
				PrintAtDepth.print(3, "Id introduced was: " + id);
				assertTrue(true);
			} catch (SQLException e) {
				PrintAtDepth.err(3, e.getMessage());
				assertTrue(false);
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "saveContact pass");
	}

	@Test
	public void testGetContactById() {
		PrintAtDepth.print(1, "getContactById start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getMaxContactId();
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			Contact contact = facadeContactPersistence.getContactById(id);
			assertTrue(contact != null);
			PrintAtDepth.print(3, "Contact was not null");
			assertTrue(contact.getId() == id);
			PrintAtDepth.print(3, "Id was correct.");
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getContactById pass");
	}

	@Test
	public void testUpdateContact() {
		PrintAtDepth.print(1, "updateContact start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			String name = "Pepitico";
			int id = getMaxContactId();
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			Contact contact = facadeContactPersistence.getContactById(id);
			assertTrue(contact != null);
			PrintAtDepth.print(3, "First check on contact was not null");
			contact.setName(name);
			facadeContactPersistence.updateContact(contact);
			contact = null;
			contact = facadeContactPersistence.getContactById(id);
			assertTrue(contact != null);
			PrintAtDepth.print(3, "Second check on contact was not null");
			assertTrue(contact.getName().equals(name));
			PrintAtDepth.print(3, "Id was correct.");
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "updateContact pass");
	}

	@Test
	public void testGetContacts() {
		PrintAtDepth.print(1, "getContacts start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getMaxContactId();
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			try {
				Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM CONTACTS ORDER BY id DESC");
				rs.next();
				while (id != rs.getInt(1)) {
					rs.next();
				}
				PrintAtDepth.print(3, "Id introduced was: " + id);
				assertTrue(true);
			} catch (SQLException e) {
				PrintAtDepth.err(3, e.getMessage());
				assertTrue(false);
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getContacts pass");
	}
	
	@Test
	public void testGetAllContacts() {
		PrintAtDepth.print(1, "saveContact start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getMaxContactId();
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			try {
				Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM CONTACTS ORDER BY id DESC");
				rs.next();
				while (id != rs.getInt(1)) {
					rs.next();
				}
				PrintAtDepth.print(3, "Id introduced was: " + id);
				assertTrue(true);
			} catch (SQLException e) {
				PrintAtDepth.err(3, e.getMessage());
				assertTrue(false);
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "saveContact pass");
	}
	
	private int getMaxContactId() {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Contact> resultSetManager = null;
		Contact contact = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledMaxIdContacts();
			resultSetManager = new ContactResultSetManager(stmFiller.executeQuery());
			if (resultSetManager.hasNext()) {
				contact = resultSetManager.next();
			}
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
			return 0;
		}
	}
}
