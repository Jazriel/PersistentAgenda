package persistence;

import java.util.List;

import model.Contact;

public interface IFacadeContactPersistence {

	Contact getContactById(int i);
	void updateContact(Contact contact);
	void saveContact(Contact contact);
	List<Contact> getAllContacts();
	List<Contact> getOrderContacts(String field);
	List<Contact> getFilterContacts(String filter, String filteredField);

}
