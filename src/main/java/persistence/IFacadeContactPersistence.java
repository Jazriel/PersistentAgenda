package persistence;

import java.util.List;

import model.Contact;
/**
 * Interface IFacadeContactPersistence
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez 
 */
public interface IFacadeContactPersistence {

	Contact getContactById(int i);
	void updateContact(Contact contact);
	void saveContact(Contact contact);
	List<Contact> getAllContacts();
	List<Contact> getOrderContacts(String field);
	List<Contact> getFilterContacts(String field, String filteredField);

}
