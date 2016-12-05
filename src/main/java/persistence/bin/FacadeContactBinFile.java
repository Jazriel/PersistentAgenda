package persistence.bin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Contact;
import persistence.IFacadeContactPersistence;

public class FacadeContactBinFile implements IFacadeContactPersistence {
	
	@SuppressWarnings("unchecked")
	public List<Contact> readContacts() {
		List<Contact> contacts = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream entrada = null;
		try {
			File fichero = new File("BinFiles\\Contacts.dat");
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
				if (entrada != null)
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
			File fichero = new File("BinFiles\\Contacts.dat");
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
		int index = -1;
		for (Contact c : contacts) {
			++index;
			if (c.getId() == contact.getId()) {
				contacts.remove(index);
				contacts.add(index, contact);
				break;
			}
		}
		writeContacts(contacts);
	}


	@Override
	public List<Contact> getAllContacts() {
		return readContacts();
	}

	@Override
	public List<Contact> getOrderContacts(String field) {
		List<Contact> contacts = readContacts();
		if (field.equals("name")) {
			Collections.sort(contacts, Contact.getOrderByName());
		} else if (field.equals("surname")) {
			Collections.sort(contacts, Contact.getOrderBySurname());
		}

		return contacts;
	}

	@Override
	public List<Contact> getFilterContacts(String field, String filteredField) {
		List<Contact> contacts = null;
		if (field.equals("name")) {
			contacts = getFilterByName(filteredField);
		} else if (field.equals("surname")) {
			contacts = getFilterBySurname(filteredField);
		}
		return contacts;
	}

	private List<Contact> getFilterByName(String filteredField) {
		List<Contact> contacts = readContacts();
		List<Contact> filter_contacts = new ArrayList<>();
		for (Contact contact : contacts) {
			if (contact.getName().equals(filteredField)) {
				filter_contacts.add(contact);
			}
		}
		return filter_contacts;
	}

	private List<Contact> getFilterBySurname(String filteredField) {
		List<Contact> contacts = readContacts();
		List<Contact> filter_contacts = new ArrayList<>();
		for (Contact contact : contacts) {
			if (contact.getSurname().equals(filteredField)) {
				filter_contacts.add(contact);
			}
		}
		return filter_contacts;
	}


	

}
