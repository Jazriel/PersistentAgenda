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

/**
 * Clase FacadeContactBinFile. Clase que se encarga del tratamiento del fichero
 * binario de contactos.
 * 
 * @author Javier Martinez.
 * @author Daniel Puente.
 * @author Jaime Sagüillo.
 * @author Jorge Zamora.
 * @author Oscar Fernandez.
 */

public class FacadeContactBinFile implements IFacadeContactPersistence {

	/**
	 * Método readContacts. Método que se encarga de leer los contactos del
	 * archivo Contacts.
	 * 
	 * @return lista, Lista de las contactos que se encuentran dentro del
	 *         archivo de Contacts.
	 */
	@SuppressWarnings("unchecked")
	public List<Contact> readContacts() {
		List<Contact> contacts = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream input = null;
		try {
			File file = new File("BinFiles\\Contacts.dat");
			fileIn = new FileInputStream(file.getAbsolutePath());
			input = new ObjectInputStream(fileIn);
			contacts = (List<Contact>) input.readObject();

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return contacts;
	}

	/**
	 * Método writeContacts. Método que se encarga de escibir una lista de
	 * contactos en el archivo Contactos.
	 * 
	 * @param contacts
	 *            Como parámetro se le pasará una lista de contactos.
	 */
	public void writeContacts(List<Contact> contacts) {
		FileOutputStream fileOut = null;
		ObjectOutputStream output = null;
		try {
			File file = new File("BinFiles\\Contacts.dat");
			fileOut = new FileOutputStream(file.getAbsolutePath());
			output = new ObjectOutputStream(fileOut);
			output.writeObject(contacts);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Método getContactById. Método que se encarga de obtener el contacto por
	 * un id de contacto.
	 * 
	 * @param i
	 *            Es el id del cual tendremos que obtener el contacto.
	 * @return contact Es el contacto que tiene ese id.
	 */
	@Override
	public Contact getContactById(int i) {
		List<Contact> contacts = readContacts();
		for (Contact contact : contacts) {
			if (contact.getId() == i) {
				return contact;
			}
		}
		return null;
	}

	/**
	 * Método saveContact. Método que se encarga de introducir un nuevo
	 * contacto.
	 * 
	 * @param contact
	 *            Contacto a añadir.
	 */
	@Override
	public void saveContact(Contact contact) {
		List<Contact> contacts = readContacts();
		contacts.add(contact);
		writeContacts(contacts);
	}

	/**
	 * Método updateContact. Método que se encarga de actualizar un contacto,
	 * por el id del contacto.
	 * 
	 * @param contact
	 *            Contacto que queremos actualizar.
	 */
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

	/**
	 * Método getAllContacts. Método que se encarga de obtener todos los
	 * contactos.
	 * 
	 * @return lista.
	 */
	@Override
	public List<Contact> getAllContacts() {
		return readContacts();
	}

	/**
	 * Método getOrderContacts. Método que se encarga de orddenar los contactos.
	 * 
	 * @param field
	 *            Campo por el que ordenar.
	 * @return contactos.
	 */
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

	/**
	 * Método getFilterContacts. Filtra los contactos.
	 * 
	 * @param field
	 *            campo a filtrar.
	 * @param filteredField
	 *            Filtro a aplicar.
	 * @return contact, List<Contacts> Lista de los contactos filtrados.
	 */
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

	/**
	 * Método getFilterByName. Filtra las llamadas por fecha.
	 * 
	 * @param filteredField
	 *            Campo a filtrar.
	 * @return Contact, List<Contact> Lista de los contactos filtrados.
	 */
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

	/**
	 * Método getFilterBySurname. Filtra los contactos por el apellido.
	 * 
	 * @param filteredField
	 *            Filtro a aplicar.
	 * @return Contact, List<Contacts> Lista de contactos filtrados.
	 */
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
