package persistence;

import java.util.List;

import model.Contact;

public interface IFacadeContactPersistence {

	Contact getContactById(int i);
	void updateContact(Contact contact);
	void saveContact(Contact contact);
	int getMaxContactId();
	List<Contact> getContacts(String disciminator, String field, String fieldValue);
	List<Contact> getAllContacts();

}
