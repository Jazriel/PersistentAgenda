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

/**
 * Clase FacadeContactTypeBinFile. Clase que se encarga del tratamiento del
 * fichero binario de tipos de contactos.
 * 
 * @author Javier Martinez.
 * @author Daniel Puente.
 * @author Jaime Sagüillo.
 * @author Jorge Zamora.
 * @author Oscar Fernandez.
 */
public class FacadeContactTypeBinFile implements IFacadeContactTypePersistence {

	/**
	 * Método readContactsType. Método que se encarga de leer los contactos del
	 * archivo ContactTypes.
	 * 
	 * @return List<ContactType> Lista de las contactos que se encuentran dentro
	 *         del archivo de ContactTypes.
	 */
	@SuppressWarnings("unchecked")
	public List<ContactType> readContactType() {
		List<ContactType> contactTypes = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream input = null;
		try {
			File file = new File("BinFiles\\ContactTypes.dat");
			fileIn = new FileInputStream(file.getAbsolutePath());
			input = new ObjectInputStream(fileIn);
			contactTypes = (List<ContactType>) input.readObject();

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
		return contactTypes;
	}

	/**
	 * Método writeContactTypes. Método que se encarga de escibir una lista de
	 * tipos de contacto en el archivo ContactTypes.
	 * 
	 * @param contactTypes
	 *            Como parámetro se le pasará una lista de tipos de contacto.
	 */
	public void writeContacTypes(List<ContactType> contactTypes) {
		FileOutputStream fileOut = null;
		ObjectOutputStream output = null;
		try {
			File file = new File("BinFiles\\ContactTypes.dat");
			fileOut = new FileOutputStream(file.getAbsolutePath());
			output = new ObjectOutputStream(fileOut);
			output.writeObject(contactTypes);
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
	 * Método getContactTypeById. Método que se encarga de obtener el tipo de
	 * contacto por un el id del tipo de contacto.
	 * 
	 * @param i
	 *            Es el id del cual tendremos que obtener el tipo de contacto.
	 * @return contactType Es el tipo de contacto que tiene ese id.
	 */
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

	/**
	 * Método updateContactType. Método que se encarga de actualizar un tipo de
	 * contacto, por el id del tipo de contacto.
	 * 
	 * @param contact
	 *            Tipo de contacto que queremos actualizar.
	 */
	@Override
	public void updateContactType(ContactType contactType) {
		List<ContactType> contactTypes = readContactType();
		int index = -1;
		for (ContactType c : contactTypes) {
			++index;
			if (c.getId() == contactType.getId()) {
				contactTypes.remove(index);
				contactTypes.add(index, contactType);
				break;
			}
		}
		writeContacTypes(contactTypes);
	}

	/**
	 * Método saveContactType. Método que se encarga de introducir un nuevo tipo
	 * de contacto.
	 * 
	 * @param contactType
	 *            Tipo de contacto a añadir.
	 */
	@Override
	public void saveContactType(ContactType contactType) {
		List<ContactType> contactTypes = readContactType();
		contactTypes.add(contactType);
		writeContacTypes(contactTypes);
	}

	/**
	 * Método getAllContactTypes. Método que se encarga de obtener todos los
	 * tipos de contacto.
	 * 
	 * @return List<TypeContact> Lista de todos los tipos de contacto.
	 */
	@Override
	public List<ContactType> getAllContactTypes() {
		return readContactType();
	}
}
