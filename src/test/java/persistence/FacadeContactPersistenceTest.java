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
import persistence.database.FacadeContactDataBase;

public class FacadeContactPersistenceTest {

	private IFacadeContactPersistence facadeContactPersistence;

	private int maxId;

	private int getMaxId() {
		maxId++;
		int ret = maxId;
		return ret;
	}

	@Before
	public void initializeDB() {
		facadeContactPersistence = new FacadeContactDataBase();
		maxId = facadeContactPersistence.getMaxContactId();
	}

	@Test
	public void testSaveContact() {
		System.out.println("saveContact Test");
		List<String> attribs = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			attribs.add(null);
		}
		int id = getMaxId();
		facadeContactPersistence.saveContact(new Contact(id, attribs, null));
		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM CONTACTS ORDER BY id DESC");
			rs.next();
			while (id != rs.getInt(1)) {
				rs.next();
			}
			System.out.println("\tId introduced was: "+ id);
			assertTrue(true);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			assertTrue(false);
		}
	}
	
	/**
	 * Rely on testSaveContact() to work. (If it doesn't this should fail.)
	 */
	@Test
	public void testGetContactById() {
		System.out.println("getContactById Test");
		List<String> attribs = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			attribs.add(null);
		}
		int id = getMaxId();
		facadeContactPersistence.saveContact(new Contact(id, attribs, null));
		Contact contact = facadeContactPersistence.getContactById(id);
		assertTrue(contact != null);
		System.out.println("\tContact was not null");
		assertTrue(contact.getId() == id);
		System.out.println("\tId was correct.");
	}
	
	/**
	 * Rely on testSaveContact() and testGetContactById() to work. (If it doesn't this should fail.)
	 */
	@Test
	public void testUpdateContact() {
		System.out.println("updateContact Test");
		List<String> attribs = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			attribs.add(null);
		}
		String name = "Pepitico";
		int id = getMaxId();
		facadeContactPersistence.saveContact(new Contact(id, attribs, null));
		Contact contact = facadeContactPersistence.getContactById(id);
		assertTrue(contact != null);
		System.out.println("\tFirst check on contact was not null");
		contact.setName(name);
		facadeContactPersistence.updateContact(contact);
		contact = null;
		contact = facadeContactPersistence.getContactById(id);
		assertTrue(contact != null);
		System.out.println("\tSecond check on contact was not null");
		assertTrue(contact.getName().equals(name));
		System.out.println("\tId was correct.");
	}
}
