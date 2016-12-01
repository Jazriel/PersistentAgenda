package persistence.bin;

import java.util.List;

import model.Contact;
import persistence.IFacadeContactPersistence;

public class FacadeContactBinFile implements IFacadeContactPersistence{
	// TODO Implementarlo mediante diccionarios(HashMap) deberia hacerlo sencillo 

	@Override
	public Contact getContactById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxContactId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Contact> getContacts(String disciminator, String field, String fieldValue) {
		return null;
		
	}

}
