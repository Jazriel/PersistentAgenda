package persistence;

import model.Contact;

public interface IFacadeContactPersistence {

	Contact getContactById(int i);
	void updateContact(Contact contact);
	void saveContact(Contact contact);
	int getMaxContactId();
	void getContacts(String disciminator, String field, String fieldValue);

}
