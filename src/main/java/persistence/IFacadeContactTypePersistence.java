package persistence;

import java.util.List;

import model.ContactType;

public interface IFacadeContactTypePersistence {

	ContactType getContactTypeById(int i);
	void updateContactType(ContactType contact);
	void saveContactType(ContactType contact);
	int getMaxContactTypeId();
	List<ContactType> getAllContactTypes();

}
