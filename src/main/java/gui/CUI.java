package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.Call;
import model.Contact;
import model.ContactType;
import persistence.AbstractPersistenceFactory;
import persistence.IAbstractPersistenceFactory;
import persistence.IFacadeCallPersistence;
import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;

public class CUI {
	private static BufferedReader bufferedReader = null;
	private static IFactoryPersistence persistence = null;
	private static IFacadeContactPersistence contactPersitence = null;
	private static IFacadeCallPersistence callPersitence = null;
	private static IFacadeContactTypePersistence contactTypePersitence = null;

	public static void main(String[] args) {
		// Choose the persistence system
		int option = -1;
		IAbstractPersistenceFactory abstractPersistenceFactory = new AbstractPersistenceFactory();
		do {
			System.out.println("* Elija el sistema de persistencia: ");
			System.out.println("** 1) Base datos SQL");
			System.out.println("** 2) Sistema de persistencia mediante ficheros binarios");
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {
				option = new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido <Base datos SQL>");
					persistence = abstractPersistenceFactory.getDBPersistence();
					break;
				case 2:
					System.out.println("La opcion elegida ha sido <Ficheros binarios>");
					persistence = abstractPersistenceFactory.getBinPersistence();
					break;
				default:
					System.out.println("Opci�n incorrecta. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 && option != 2);

		// Initialize the persistences
		contactPersitence = persistence.createContactPersistence();
		callPersitence = persistence.createCallPersistence();
		contactTypePersitence = persistence.createContactTypePersistence();

		// Choose the action
		option = -1;
		do {
			System.out.println("*****************Menu*****************");
			System.out.println("* 1) Insertar: ");
			System.out.println("* 2) Actualizar: ");
			System.out.println("* 3) Consultar: ");
			System.out.println("* 0) Salir: ");
			try {
				option = new Integer(bufferedReader.readLine());
				switch (option) {
				case 0:
					System.out.println("Saliendo...");
					break;
				case 1:
					System.out.println("La opcion elegida ha sido <Insertar>");
					insertMenu();
					break;
				case 2:
					System.out.println("La opcion elegida ha sido <Actualizar>");
					updateMenu();
					break;
				case 3:
					System.out.println("La opcion elegida ha sido <Consultar>");
					getMenu();
					break;
				default:
					System.out.println("Opci�n incorrecta en Menu. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 0);
	}

	private static void insertMenu() {
		int option = 0;
		int pos;
		do {
			System.out.println("** 1) Nuevo contacto");
			System.out.println("** 2) Nueva llamada");
			System.out.println("** 3) Nuevo tipo de contacto");
			try {
				option = new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido crear un nuevo contacto");
					// Obtenemos los campos del contacto
					List<String> attribs = insertContactFields();
					// Obtenemos los tipos de contacto
					List<ContactType> contactTypes = contactTypePersitence.getAllContactTypes();
					// Mostramos los tipos de contacto
					showContactTypes(contactTypes);
					// Obtenemos el tipo de contacto que se desea
					pos = new Integer(bufferedReader.readLine()) - 1;
					// Guardamos el contacto
					contactPersitence.saveContact(new Contact(attribs, contactTypes.get(pos)));
					break;
				case 2:
					System.out.println("La opcion elegida ha sido crear una nueva llamada");
					// Obtenemos los contactos
					List<Contact> contacts = contactPersitence.getAllContacts();
					// Mostramos los contactos
					showContacts(contacts);
					// Obtenemos el contacto que se desea
					pos = new Integer(bufferedReader.readLine()) - 1;

					System.out.println("Introduzca el tema de conversaci�n:");
					String subject = bufferedReader.readLine();

					System.out.println("Introduzca alguna anotaci�n sobre la llamada:");
					String notes = bufferedReader.readLine();

					// Guardamos la llamada
					callPersitence.saveCall(
							new Call(contacts.get(pos), subject, notes));
					break;
				case 3:
					System.out.println("La opcion elegida ha sido crear un nuevo tipo de contacto");
					System.out.println("Introduzca el nombre del tipo de contacto:");
					String name = bufferedReader.readLine();
					contactTypePersitence.saveContactType(new ContactType(name));
					break;
				default:
					System.out.println("Opci�n incorrecta en Menu insertar. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 && option != 2 && option != 3);
	}

	private static void updateMenu() {
		int option = 0, id, pos;
		do {
			System.out.println("** 1) Actualizar contacto");
			System.out.println("** 2) Actualizar llamada");
			System.out.println("** 3) Actualizar tipo de contacto");
			try {
				option = new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido actualizar un contacto");

					// Obtenemos los contactos
					List<Contact> contacts = contactPersitence.getAllContacts();
					// Mostramos los contactos
					showContacts(contacts);
					// Escogemos el contacto
					pos = new Integer(bufferedReader.readLine()) - 1;
					id = contacts.get(pos).getId();
					// Mostramos los campos actuales del contacto
					System.out.println(contacts.get(pos).toString());
					// Insertamos los campos del contacto
					List<String> attribs = insertContactFields();

					// Mostramos los tipos de contactos
					List<ContactType> contactTypes = contactTypePersitence.getAllContactTypes();
					showContactTypes(contactTypes);
					// Escogemos el tipo de contacto
					pos = new Integer(bufferedReader.readLine()) - 1;

					// Actualizamos
					contactPersitence.updateContact(new Contact(id, attribs, contactTypes.get(pos)));
					break;
				case 2:
					System.out.println("La opcion elegida ha sido actualizar una llamada");
					List<Call> calls = callPersitence.getAllCalls();
					showCalls(calls);
					// Escogemos la llamada entre las mostradas
					pos = new Integer(bufferedReader.readLine()) - 1;

					// Introducimos nuevos valores
					System.out.println("Introduzca el tema de conversaci�n:");
					String subject = bufferedReader.readLine();

					System.out.println("Introduzca alguna anotaci�n sobre la llamada:");
					String notes = bufferedReader.readLine();

					// Actualizamos
					callPersitence.updateCall(new Call(calls.get(pos).getId(), subject, notes));
					break;
				case 3:
					System.out.println("La opcion elegida ha sido actualizar un tipo de contacto");
					// Mostramos los tipos de contactos
					List<ContactType> contactTypes2 = contactTypePersitence.getAllContactTypes();
					showContactTypes(contactTypes2);
					// Escogemos el tipo de contacto
					pos = new Integer(bufferedReader.readLine()) - 1;

					System.out.println("Introduzca el nombre del tipo de contacto:");
					String name = bufferedReader.readLine();

					// Actualizamos
					contactTypePersitence.updateContactType(new ContactType(contactTypes2.get(pos).getId(), name));
					break;
				default:
					System.out.println("Opci�n incorrecta en Menu actualizar. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 && option != 2 && option != 3);
	}

	private static void getMenu() {
		int option = 0;
		do {
			System.out.println("** 1) Los contactos");
			System.out.println("** 2) Las llamadas");
			System.out.println("** 3) Los tipos de contactos");
			try {
				option = new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido los contactos");
					filterOrOrderContactsMenu();
					break;
				case 2:
					System.out.println("La opcion elegida ha sido las llamadas");
					filterOrOrderCallsMenu();
					break;
				case 3:
					System.out.println("La opcion elegida ha sido todos los tipos de contacto");
					showContactTypes(contactTypePersitence.getAllContactTypes());
					break;
				default:
					System.out.println("Opci�n incorrecta en Menu consultar. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 && option != 2 && option != 3);
	}

	private static void filterOrOrderContactsMenu() {
		int option = 0;
		String name;
		String surname;
		do {
			System.out.println("** 1) Ordenar por nombre");
			System.out.println("** 2) Ordenar por apellido");
			System.out.println("** 3) Filtrar por nombre");
			System.out.println("** 4) Filtrar por apellido");
			try {
				option = new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido ordenar por nombre");
					name = bufferedReader.readLine();
					contactPersitence.getContacts("order by", "name", name);
					break;
				case 2:
					System.out.println("La opcion elegida ha sido ordenar por apellido");
					surname = bufferedReader.readLine();
					contactPersitence.getContacts("order by", "surname", surname);
					break;
				case 3:
					System.out.println("La opcion elegida ha sido filtrar por nombre");
					name = bufferedReader.readLine();
					contactPersitence.getContacts("where", "name", name);
					break;
				case 4:
					System.out.println("La opcion elegida ha sido filtrar por apellido");
					surname = bufferedReader.readLine();
					contactPersitence.getContacts("where", "surname", surname);
					break;
				default:
					System.out.println("Opci�n incorrecta en Menu ordenar/filtrar contactos. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 || option != 2 || option != 3 || option != 4);
	}

	private static void filterOrOrderCallsMenu() {
		int option = 0;
		do {
			System.out.println("** 1) Filtrar por contacto");
			System.out.println("** 2) Filtrar por fecha de realizaci�n");
			try {
				option = new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido filtrar por contacto");
					System.out.println("Introduzca el id del contacto:");
					int id = new Integer(bufferedReader.readLine());
					callPersitence.getCalls("where", "id", id);
					break;
				case 2:
					System.out.println("La opcion elegida ha sido filtrar por fecha de realizaci�n");
					System.out.println("Introduzca la fecha de realizaci�n:");
					System.out.print("D�a:");
					int day = new Integer(bufferedReader.readLine());
					int month = new Integer(bufferedReader.readLine());
					int year = new Integer(bufferedReader.readLine());
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateFormat
							.parse(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
					long time = date.getTime();
					Timestamp timeStamp = new Timestamp(time);
					callPersitence.getCalls("where", "call_date", timeStamp);
					break;
				default:
					System.out.println("Opci�n incorrecta en Menu llamadas. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 && option != 2);
	}

	private static void showContacts(List<Contact> contacts) {
		for (int i = 0; i < contacts.size(); i++) {
			System.out.println(
					i + 1 + ") Nombre: " + contacts.get(i).getName() + " , Apellidos: " + contacts.get(i).getSurname());
		}
	}

	private static void showCalls(List<Call> calls) {
		for (int i = 0; i < calls.size(); i++) {
			System.out.println(i + 1 + ") " + calls.get(i).toString());
		}
	}

	private static void showContactTypes(List<ContactType> contactTypes) {
		for (int i = 0; i < contactTypes.size(); i++) {
			System.out.println(i + 1 + ") " + contactTypes.get(i).getContactTypeName());
		}
	}

	private static List<String> insertContactFields() {
		List<Field> contactFields = new ArrayList<>(Arrays.asList(Contact.class.getDeclaredFields()));
		List<String> values = new ArrayList<>();
		contactFields.remove(0);
		contactFields.remove(0);
		contactFields.remove(contactFields.size()-1);
		for (Field field : contactFields) {
			System.out.println("Introduzca el " + field.getName().toString());
			try {
				values.add(bufferedReader.readLine());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return values;
	}

}
