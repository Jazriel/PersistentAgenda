package persistence.bin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.ContactType;
import persistence.IFacadeContactTypePersistence;

public class FacadeContactTypeBinFile implements IFacadeContactTypePersistence{
	
	@SuppressWarnings("unchecked")
	public List<ContactType> readContactType() {
		List<ContactType> contactTypes = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream entrada = null;
		try {
			File fichero=new File("BinFiles\\ContactTypes.dat");
			fileIn = new FileInputStream(fichero.getAbsolutePath());
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
				if(entrada!=null)
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
			File fichero=new File("BinFiles\\ContactTypes.dat");
			fileOut = new FileOutputStream(fichero.getAbsolutePath());
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
	public int getMaxContactTypeId(){
		return readContactType().size();
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
