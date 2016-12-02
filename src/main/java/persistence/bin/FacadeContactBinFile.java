package persistence.bin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import persistence.IFacadeContactPersistence;

public class FacadeContactBinFile implements IFacadeContactPersistence{
	// TODO Implementarlo mediante diccionarios(HashMap) deberia hacerlo sencillo 

	public List<Contact> readContacts() {
		List<Contact> contacts = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream entrada = null;
		try {
			fileIn = new FileInputStream("D:\\GitRepositorio\\DisManSof\\PR1\\BinFiles\\Contacts.txt");
			entrada = new ObjectInputStream(fileIn);
			contacts = (List<Contact>)entrada.readObject();

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
		return contacts;
	}

	
	public void writeContacts(List<Contact> contacts) {
		FileOutputStream fileOut = null;
		ObjectOutputStream salida = null;
		try {
			fileOut = new FileOutputStream("D:\\GitRepositorio\\DisManSof\\PR1\\BinFiles\\Contacts.txt");
			salida = new ObjectOutputStream(fileOut);
			salida.writeObject(contacts);
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
	public Contact getContactById(int i) {
		List<Contact> contacts = readContacts();
		for (Contact c : contacts) {
			if (c.getId() == i) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void saveContact(Contact contact) {
		List<Contact> contacts = readContacts();
		contacts.add(contact);
		writeContacts(contacts);	
	}

	@Override
	public void updateContact(Contact contact) {
		List<Contact> contacts = readContacts();
		for (Contact c : contacts) {
			if (c.getId() == contact.getId()) {
				c = contact;
			}
		}
		writeContacts(contacts);	
	}


	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Contact> getOrderContacts(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Contact> getFilterContacts(String string, String filteredField) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
