package persistence.bin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Call;
import model.ContactType;
import persistence.IFacadeContactTypePersistence;
import persistence.database.ContactTypeResultSetManager;

public class FacadeContactTypeBinFile implements IFacadeContactTypePersistence{
	
	public List<ContactType> readContactType() {
		List<ContactType> contactTypes = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream entrada = null;
		try {
			fileIn = new FileInputStream("\\BinFiles\\ContactTypes.txt");
			entrada = new ObjectInputStream(fileIn);
			contactTypes = (List<ContactType>)entrada.readObject();

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				entrada.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return contactTypes;
	}

	public void writeContacTypes(List<ContactType> contactTypes) {
		FileOutputStream fileOut = null;
		ObjectOutputStream salida = null;
		try {
			fileOut = new FileOutputStream("\\BinFiles\\ContactTypes.txt");
			salida = new ObjectOutputStream(fileOut);
			salida.writeObject(contactTypes);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				salida.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public ContactType getContactTypeById(int i) {
		List<ContactType> contactTypes = readContactType();
		for (ContactType contactType : contactTypes) {
			if (contactType.getId() == i) {
				return contactType;
			}
		}
		return null;
	}

	@Override
	public void updateContactType(ContactType contact) {
		List<ContactType> contactTypes = readContactType();
		for (ContactType c : contactTypes) {
			if (c.getId() == contact.getId()) {
				c = contact;
			}
		}
		writeContacTypes(contactTypes);
	}

	@Override
	public void saveContactType(ContactType contact) {
		List<ContactType> contactTypes = readContactType();
		contactTypes.add(contact);
		writeContacTypes(contactTypes);
		
	}

	@Override
	public List<ContactType> getAllContactTypes() {
		return readContactType();
	}


}
