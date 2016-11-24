package persistence;

import model.ContactType;

public interface IFacadeContactTypePersistence {

	ContactType getContactTypeById(int i);
	void updateContactType(ContactType contact);
	void saveContactType(ContactType contact);
	int getMaxContactTypeId();

}
