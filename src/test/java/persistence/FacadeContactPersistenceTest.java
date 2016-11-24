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
		int ret = maxId;
		maxId++;
		return ret;
	}

	@Before
	public void initializeDB() {
		facadeContactPersistence = new FacadeContactDataBase();
		maxId = facadeContactPersistence.getMaxContactId();
	}

	@Test
	public void testSaveContact() {
		List<String> attribs = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			attribs.add(null);
		}
		int id = getMaxId();
		facadeContactPersistence.saveContact(new Contact(id, attribs, null));
		try {
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM CONTACTS order by id desc");
			rs.next();
			while (id != rs.getInt(0)) {
				rs.next();
			}
			assertTrue(true);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}

	/*@Test
	public void testUpdateContact() {
		List<String> attribs = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			attribs.add(null);
		}
		int id = getMaxId();
		String name = "Pepito";
		facadeContactPersistence.saveContact(new Contact(id, attribs, null));
		Contact contact = facadeContactPersistence.getContactById(id);
		assertTrue(contact != null);
		contact.setName(name);
		facadeContactPersistence.updateContact(contact);
		contact = facadeContactPersistence.getContactById(id);
		assertTrue(contact != null);
		assertTrue(contact.getId() == id);
		assertTrue(contact.getName().equals(name));
	}*/
}
