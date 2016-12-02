package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
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
		persistences.add(aPersistenceFactory.getDBPersistence());
		persistences.add(aPersistenceFactory.getBinPersistence());
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
			int id = getIncMaxContactId();
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			Connection connection = null;
			Statement stm = null;
			ResultSet rs = null;
			try {
				connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
				stm = connection.createStatement();
				rs = stm.executeQuery("SELECT * FROM CONTACTS ORDER BY id DESC");
				rs.next();
				while (id != rs.getInt(1)) {
					rs.next();
				}
				PrintAtDepth.print(3, "Id introduced was: " + id);
				assertTrue(true);
			} catch (SQLException e) {
				PrintAtDepth.err(3, e.getMessage());
				assertTrue(false);
			} finally {
				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
				}
				try {
					if (stm != null && !stm.isClosed()) {
						stm.close();
					}
				} catch (SQLException e) {
				}
				try {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
				}
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
			int id = getIncMaxContactId();
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
			int id = getIncMaxContactId();
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
			int id = getIncMaxContactId();
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
		PrintAtDepth.print(1, "getAllContacts start");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
		} catch (SQLException e) {
			PrintAtDepth.err(2, e.getMessage());
			assertTrue(false);
		}
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			List<Integer> contactIds = new LinkedList<>();
			ResultSet rs = null;
			Statement stm = null;
			try {
				stm = connection.createStatement();
				rs = stm.executeQuery("SELECT * FROM CONTACTS ORDER BY id DESC");
				while (true) {
					rs.next();
					contactIds.add(rs.getInt(0));
				}
			} catch (SQLException e) {
				// This is the iterator stop
				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e1) {
				}
				try {
					if (stm != null && !stm.isClosed()) {
						stm.close();
					}
				} catch (SQLException e1) {
				}
			}

			List<Contact> contacts = facadeContactPersistence.getAllContacts();
			for (Contact contact : contacts) {
				if (contactIds.contains(contact.getId())) {
					contactIds.remove(contact.getId());
				} else {
					assertTrue(false);
				}
			}

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getAllContacts pass");
	}

	private int getIncMaxContactId() {
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		int contact = 0;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
			stm = connection.createStatement();
			rs = stm.executeQuery("SELECT MAX(id) FROM contacts");
			rs.next();
			contact = rs.getInt(1);
		} catch (SQLException e) {
			PrintAtDepth.err(0, "getMaxContactId Error: " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				PrintAtDepth.err(0, e.getMessage());
			}
		}
		return contact + 1;
	}

	/**
	 * Only works if we can get the database in a reliable state
	 * 
	 */
	@Test
	public void testGetOrderContacts() {
		PrintAtDepth.print(1, "getOrderContacts start");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
		} catch (SQLException e) {
			PrintAtDepth.err(2, e.getMessage());
			assertTrue(false);
		}
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			String[] fields = { "id", "name", "surname", "title", "address", "city", "province", "postal_Code",
					"region", "country", "company_Name", "workstation", "work_Phone", "work_Extension", "mobile_Phone",
					"fax_Number", "contact_type_id", "email", "notes" };
			for (String field : fields) {
				PrintAtDepth.print(3, field + " start");
				IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
				List<String> attribs = new ArrayList<>();
				for (int i = 0; i < 20; i++) {
					attribs.add(null);
				}
				List<Integer> contactIdsOrdered = new LinkedList<>();
				ResultSet rs = null;
				Statement stm = null;
				try {
					stm = connection.createStatement();
					rs = stm.executeQuery("SELECT * FROM CONTACTS ORDER BY " + field + " ASC");
					while (true) {
						rs.next();
						contactIdsOrdered.add(rs.getInt(1));
					}
				} catch (SQLException e) {
					// This is the iterator stop
					try {
						if (rs != null && !rs.isClosed()) {
							rs.close();
						}
					} catch (SQLException e1) {
					}
					try {
						if (stm != null && !stm.isClosed()) {
							stm.close();
						}
					} catch (SQLException e1) {
					}
				}
				int i = 0;
				List<Contact> contacts = facadeContactPersistence.getOrderContacts(field);
				List<Integer> contIds = new ArrayList<>();
				for (Contact contact : contacts) {
					contIds.add(contact.getId());
				}

				int j = contactIdsOrdered.size() > contIds.size() ? contactIdsOrdered.size() : contIds.size();
				for (i = 0; i < j; i++) {
					assertEquals(contIds.get(i), contactIdsOrdered.get(i));
				}

				PrintAtDepth.print(3, field + " pass");
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getOrderContacts pass");
	}


	@Test
	public void testFilterContacts() {
		PrintAtDepth.print(1, "getFilterContacts start");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
		} catch (SQLException e) {
			PrintAtDepth.err(2, e.getMessage());
			assertTrue(false);
		}
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			String[] fields = { "name", "surname", "title", "address", "city", "province", "postal_Code", "region",
					"country", "company_Name", "workstation", "work_Phone", "work_Extension", "mobile_Phone",
					"fax_Number", "email", "notes" };
			for (String field : fields) {
				PrintAtDepth.print(3, field + " start");
				IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
				List<String> attribs = new ArrayList<>();
				for (int i = 0; i < 20; i++) {
					attribs.add(null);
				}
				List<Integer> contactIdsFiltered = new LinkedList<>();
				ResultSet rs = null;
				Statement stm = null;
				String str = null;
				try {
					stm = connection.createStatement();
					rs = stm.executeQuery("SELECT * FROM contacts WHERE "+field+" IS NOT NULL ORDER BY " + field + " ASC");
					rs.next();
					str = rs.getString(field);
					stm = connection.createStatement();
					rs = stm.executeQuery("SELECT * FROM contacts WHERE " + field + " = '" + str + "'");
					while (true) {
						rs.next();
						contactIdsFiltered.add(rs.getInt(1));
					}
				}

				catch (SQLException e) {
					// This is the iterator stop
					try {
						if (rs != null && !rs.isClosed()) {
							rs.close();
						}
					} catch (SQLException e1) {
					}
					try {
						if (stm != null && !stm.isClosed()) {
							stm.close();
						}
					} catch (SQLException e1) {
					}
				}
				int i = 0;
				
				List<Contact> contacts = facadeContactPersistence.getFilterContacts( field, str);
				
				List<Integer> contIds = new ArrayList<>();
				
				for (Contact contact : contacts) {
					contIds.add(contact.getId());
				}
				
				int j = contactIdsFiltered.size() > contIds.size() ? contactIdsFiltered.size() : contIds.size();
				for (i = 0; i < j; i++) {
					assertEquals(contIds.get(i), contactIdsFiltered.get(i));
				}
				
				PrintAtDepth.print(3, field + " pass");
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getFilterContacts pass");
	}

}