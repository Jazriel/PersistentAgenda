package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
			boolean b = false;
			for (Contact contact : facadeContactPersistence.getAllContacts()) {
				if (contact.getId() == id) {
					b = true;
				}
			}
			assertTrue(b);
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "saveContact pass");
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
			List<Integer> contactIds = new ArrayList<Integer>();
			for (Contact contact : contactsAfter) {
				contactIds.add(contact.getId());
			}
			for (Contact contact : contactsBefore) {
				if (contactIds.contains(contact.getId())) {
					contactIds.remove(Integer.valueOf(contact.getId()));
				} else {
					assertTrue(false);
				}
			}
			assertTrue(contactIds.remove(Integer.valueOf(id)));
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getAllContacts pass");
	}

	private int getIncMaxContactId(IFacadeContactPersistence persistence) {
		int ret = 0;
		List<Contact> contacts = persistence.getAllContacts();
		for (Contact contact : contacts) {
			if (contact.getId() > ret) {
				ret = contact.getId();
			}
		}

		return ret + 1;
	}

	@Test
	public void testGetOrderContacts() {
		PrintAtDepth.print(1, "getOrderContacts start");

		for (IFactoryPersistence persistence : persistences) {
			String field = "name";
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();
			List<Contact> contacts = facadeContactPersistence.getOrderContacts(field);
			for (int i = 1; i < contacts.size(); i++) {
				if (contacts.get(i - 1).getName() != null && contacts.get(i).getName() != null) {
					if (0 < contacts.get(i - 1).getName().compareTo(contacts.get(i).getName())) {
						assertTrue(false);
					}
				}
			}

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getOrderContacts pass");
	}

	@Test
	public void testFilterContacts() {
		PrintAtDepth.print(1, "getFilterContacts start");

		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			String field = "name";
			IFacadeContactPersistence facadeContactPersistence = persistence.createContactPersistence();

			String str = "Pepitico";

			List<Contact> contacts = facadeContactPersistence.getFilterContacts(field, str);

			for (Contact contact : contacts) {
				assertEquals(contact.getName(), str);
			}

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getFilterContacts pass");
	}

}