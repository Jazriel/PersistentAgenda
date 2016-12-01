package persistence;

import java.util.List;

import model.Contact;

public interface IFacadeContactPersistence {

	Contact getContactById(int i);
	void updateContact(Contact contact);
	void saveContact(Contact contact);
	int getMaxContactId();
	List<Contact> getAllContacts();
	List<Contact> getOrderContacts(String string);
	List<Contact> getFilterContacts(String string, String filteredField);

}
