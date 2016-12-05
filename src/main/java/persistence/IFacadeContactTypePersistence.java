package persistence;

import java.util.List;

import model.ContactType;
/**
 * Interface IFacadeContactTypePersistence
 * @author Javier Martinez, Daniel Puente, Jaime Sagüillo, Jorge Zamora y Oscar Fernandez
 *
 */
public interface IFacadeContactTypePersistence {

	ContactType getContactTypeById(int i);
	void updateContactType(ContactType contact);
	void saveContactType(ContactType contact);
	List<ContactType> getAllContactTypes();

}
