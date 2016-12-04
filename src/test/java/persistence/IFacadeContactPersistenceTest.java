package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
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
			int id = getIncMaxContactId(facadeContactPersistence);
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			Contact contact = facadeContactPersistence.getContactById(id);
			assertEquals(id, contact.getId());
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
			int id = getIncMaxContactId(facadeContactPersistence);
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			Contact contact = facadeContactPersistence.getContactById(id);
			assertTrue(contact != null);
			// PrintAtDepth.print(3, "Contact was not null");
			assertTrue(contact.getId() == id);
			// PrintAtDepth.print(3, "Id was correct.");
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
			int id = getIncMaxContactId(facadeContactPersistence);
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
	public void testGetAllContacts() {
		PrintAtDepth.print(1, "getAllContacts start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<Contact> contactsBefore = facadeContactPersistence.getAllContacts();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getIncMaxContactId(facadeContactPersistence);
			facadeContactPersistence.saveContact(new Contact(id, attribs, null));
			List<Contact> contactsAfter = facadeContactPersistence.getAllContacts();
			for (Contact contact : contactsBefore) {
				assertTrue(contactsAfter.remove(contact));
			}
			assertEquals(contactsAfter.get(0).getId(), id);

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getAllContacts pass");
	}

	@Test
	public void testGetOrderContacts() {
		PrintAtDepth.print(1, "getOrderContacts start");
		// , "contact_type_id"
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			String[] fields = { "id", "name", "surname", "title", "address", "city", "province", "postal_Code",
					"region", "country", "company_Name", "workstation", "work_Phone", "work_Extension", "mobile_Phone",
					"fax_Number", "email", "notes" };
			for (String fieldString : fields) {
				PrintAtDepth.print(3, fieldString + " start");
				IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
				List<Contact> contacts = facadeContactPersistence.getOrderContacts(fieldString);
				Field field = fieldString2field(fieldString);
				int i = 0;
				do {
					String fieldVal = fieldValue(field, contacts.get(i));
					i++;
					String fieldValAux = fieldValue(field, contacts.get(i));
					assertTrue(0 >= fieldVal.compareTo(fieldValAux));
				} while (i < contacts.size());

				PrintAtDepth.print(3, fieldString + " pass");
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getOrderContacts pass");
	}

	private String fieldValue(Field field, Contact contact) {
		try {
			return (String) field.get(contact);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Field fieldString2field(String field) {
		try {
			switch (field) {
			case "postal_Code":
				return Contact.class.getField("getPostalCode");
			case "company_Name":
				return Contact.class.getField("getCompanyName");
			case "work_Phone":
				return Contact.class.getField("getWorkPhone");
			case "work_Extension":
				return Contact.class.getField("getWorkExtension");
			case "mobile_Phone":
				return Contact.class.getField("getMobilePhone");
			case "fax_Number":
				return Contact.class.getField("getFaxNumber");
			case "contact_type_id":
				return Contact.class.getField("getContactTypeId");
			default:
				return Contact.class.getField(field);
			}
		} catch (Exception e) {
			PrintAtDepth.err(0, "dbField2objectMethod FAILED");
			return null;
		}

	}

	@Test
	public void testFilterContacts() {
		PrintAtDepth.print(1, "getFilterContacts start");
		
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			String[] fields = { "name", "surname", "title", "address", "city", "province", "postal_Code", "region",
					"country", "company_Name", "workstation", "work_Phone", "work_Extension", "mobile_Phone",
					"fax_Number", "email", "notes" };
			for (String fieldString : fields) {
				PrintAtDepth.print(3, fieldString + " start");
				IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
				Contact contactForField = facadeContactPersistence.getAllContacts().get(0);
				Field field = fieldString2field(fieldString);
				String filterString = fieldValue(field, contactForField);
				List<Contact> contactsFiltered = facadeContactPersistence.getFilterContacts(fieldString, filterString);
				//  check all the list field == filterString
				for (Contact contact : contactsFiltered) {
					assertEquals(filterString, fieldValue(field, contact));
				}
				//  check all in getAllContacts with field == filterString -> in contactsFiltered
				List<Contact> allContacts = facadeContactPersistence.getAllContacts();
				for (Contact contact : allContacts) {
					if(filterString==fieldValue(field, contact)){
						assertTrue(contactsFiltered.remove(contact));
					}
				}
				PrintAtDepth.print(3, field + " pass");
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getFilterContacts pass");
	}

	private int getIncMaxContactId(IFacadeContactPersistence persistence) {
		if (persistence instanceof persistence.database.FacadeContactDataBase) {
			return getIncMaxContactIdDB();
		} else if (persistence instanceof persistence.bin.FacadeContactBinFile) {
			return getIncMaxContactIdBinFile(persistence);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private int getIncMaxContactIdBinFile(IFacadeContactPersistence persistence) {
		int ret;
		try {
			ret = persistence.getOrderContacts("id").get(0).getId();
		} catch (NullPointerException e) {
			return 1;
		}
		return ret;
	}

	private int getIncMaxContactIdDB() {
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		int contact = 0;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
			stm = connection.createStatement();
			rs = stm.executeQuery("SELECT COALESCE(MAX(id), 0) FROM contacts");
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

}