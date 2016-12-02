package persistence.bin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Contact;
import persistence.IFacadeContactPersistence;

public class FacadeContactBinFile implements IFacadeContactPersistence {
	public List<Contact> readContacts() {
		List<Contact> contacts = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream entrada = null;
		try {
			File fichero=new File("BinFiles\\Contacts.txt");
			fileIn = new FileInputStream(fichero.getAbsolutePath());
			entrada = new ObjectInputStream(fileIn);
			contacts = (List<Contact>) entrada.readObject();

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
			File fichero=new File("BinFiles\\Contacts.txt");
			fileOut = new FileOutputStream(fichero.getAbsolutePath());
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
	public int getMaxContactId() {
		return readContacts().size();
	}

	@Override
	public List<Contact> getAllContacts() {
		return readContacts();
	}

	@Override
	public List<Contact> getOrderContacts(String string) {
		List<Contact> contacts = readContacts();
		Class<?> contactClass;
		Field contactField;
		try {
			contactClass = Class.forName("model.Contact");
			contactField = contactClass.getField(string);
		} catch (NoSuchFieldException e) {
			System.err.println(e.getMessage());
		} catch (SecurityException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Contact> getFilterContacts(String string, String filteredField) {
		// TODO Auto-generated method stub
		return null;
	}
	

		
}
