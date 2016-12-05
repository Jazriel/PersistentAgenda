package persistence;

import java.util.List;

import model.ContactType;
/**
 * Interface IFacadeContactTypePersistence
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez 
 */
public interface IFacadeContactTypePersistence {

	ContactType getContactTypeById(int i);
	void updateContactType(ContactType contact);
	void saveContactType(ContactType contact);
	List<ContactType> getAllContactTypes();

}
