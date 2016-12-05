package persistence;

import java.util.List;

import model.Contact;
/**
 * Interface IFacadeContactPersistence
 * @author Javier Martinez, Daniel Puente, Jaime Sagüillo, Jorge Zamora y Oscar Fernandez
 *
 */
public interface IFacadeContactPersistence {

	Contact getContactById(int i);
	void updateContact(Contact contact);
	void saveContact(Contact contact);
	List<Contact> getAllContacts();
	List<Contact> getOrderContacts(String field);
	List<Contact> getFilterContacts(String field, String filteredField);

}
