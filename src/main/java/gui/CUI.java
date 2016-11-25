package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Call;
import model.Contact;
import model.ContactType;
import persistence.FactoryPersistence;
import persistence.IFacadeCallPersistence;
import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.bin.FactoryBinFile;
import persistence.database.FactoryDataBase;

public class CUI {
	private static BufferedReader bufferedReader = null;
	private static FactoryPersistence persistence = null;
	private static IFacadeContactPersistence contactPersitence = null;
	private static IFacadeCallPersistence callPersitence = null;
	private static IFacadeContactTypePersistence contactTypePersitence = null;

	public static void main(String[] args) {
		// Choose the persistence system
		int option = -1;
		do {
			System.out.println("* Elija el sistema de persistencia: ");
			System.out.println("** 1) Base datos SQL");
			System.out.println("** 2) Sistema de persistencia mediante ficheros binarios");
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {
				option =new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido <Base datos SQL>");
					persistence = new FactoryDataBase();
					break;
				case 2:
					System.out.println("La opcion elegida ha sido <Ficheros binarios>");
					persistence = new FactoryBinFile();
					break;
				default:
					System.out.println("Opción incorrecta. Vuelva a intentarlo!");
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
				option =new Integer(bufferedReader.readLine());
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
					System.out.println("Opción incorrecta en Menu. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 0);
	}

	private static void insertMenu() {
		int option = 0;
		do {
			System.out.println("** 1) Nuevo contacto");
			System.out.println("** 2) Nueva llamada");
			System.out.println("** 3) Nuevo tipo de contacto");
			try {
				option =new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido crear un nuevo contacto");
					List<String> attribs = insertContactFields();
					ContactType contactType = chooseContactType();
					contactPersitence.saveContact(new Contact(attribs, contactType));
					break;
				case 2:
					System.out.println("La opcion elegida ha sido crear una nueva llamada");
					Contact contact = chooseContact();
					System.out.println("Introduzca el tema de conversación:");
					String subject = bufferedReader.readLine();
					System.out.println("Introduzca alguna anotación sobre la llamada:");
					String notes = bufferedReader.readLine();
					callPersitence.saveCall(new Call(contact, subject, notes));
					break;
				case 3:
					System.out.println("La opcion elegida ha sido crear un nuevo tipo de contacto");
					System.out.println("Introduzca el nombre del tipo de contacto:");
					String name = bufferedReader.readLine();
					contactTypePersitence.saveContactType(new ContactType(name));
					break;
				default:
					System.out.println("Opción incorrecta en Menu insertar. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 || option != 2 || option != 3);
	}

	private static void updateMenu() {
		int option = 0;
		int id = 0;
		do {
			System.out.println("** 1) Actualizar contacto");
			System.out.println("** 2) Actualizar llamada");
			System.out.println("** 3) Actualizar tipo de contacto");
			try {
				option =new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido actualizar un contacto");
					// TODO Mejorable, mostrando los contactos y eligiendo entre
					// ellos
					System.out.println("Introduzca el id del contacto:");
					id = bufferedReader.read();
					List<String> attribs = new ArrayList<>();
					ContactType contactType = chooseContactType();
					insertContactFields();
					contactPersitence.updateContact(new Contact(id, attribs, contactType));
					break;
				case 2:
					System.out.println("La opcion elegida ha sido actualizar una llamada");
					// TODO igual que el anterior
					System.out.println("Introduzca el id de la llamada:");
					id = bufferedReader.read();
					System.out.println("Introduzca el tema de conversación:");
					String subject = bufferedReader.readLine();
					System.out.println("Introduzca alguna anotación sobre la llamada:");
					String notes = bufferedReader.readLine();
					callPersitence.updateCall(new Call(id, subject, notes));
					break;
				case 3:
					System.out.println("La opcion elegida ha sido actualizar un tipo de contacto");
					// TODO igual que el anterior
					System.out.println("Introduzca el id del tipo de contacto:");
					id = bufferedReader.read();
					System.out.println("Introduzca el nombre del tipo de contacto:");
					String name = bufferedReader.readLine();
					contactTypePersitence.updateContactType(new ContactType(id, name));
					break;
				default:
					System.out.println("Opción incorrecta en Menu actualizar. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 || option != 2 || option != 3);
	}

	private static void getMenu() {
		int option = 0;
		do {
			System.out.println("** 1) Los contactos");
			System.out.println("** 2) Las llamadas");
			System.out.println("** 3) Los tipos de contactos");
			try {
				option =new Integer(bufferedReader.readLine());
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
					contactTypePersitence.getAllContactTypes();
					break;
				default:
					System.out.println("Opción incorrecta en Menu consultar. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 || option != 2 || option != 3);
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
				option =new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido ordenar por nombre");
					name = bufferedReader.readLine();
					contactPersitence.getContacts("order by","name", name);
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
					System.out.println("Opción incorrecta en Menu ordenar/filtrar contactos. Vuelva a intentarlo!");
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
			System.out.println("** 2) Filtrar por fecha de realización");
			try {
				option =new Integer(bufferedReader.readLine());
				switch (option) {
				case 1:
					System.out.println("La opcion elegida ha sido filtrar por contacto");
					System.out.println("Introduzca el id del contacto:");
					int id = bufferedReader.read();
					callPersitence.getCalls("where", "id", id);
					break;
				case 2:
					System.out.println("La opcion elegida ha sido filtrar por fecha de realización");
					System.out.println("Introduzca la fecha de realización:");
					System.out.print("Día:");
					int day = bufferedReader.read();
					int month = bufferedReader.read();
					int year = bufferedReader.read();
					//TODO Repasar con apuntes APBD
					Timestamp timeStamp = new Timestamp(year, month, day, 0, 0, 0, 0);
					callPersitence.getCalls("where", "call_date", timeStamp);
					break;
				default:
					System.out.println("Opción incorrecta en Menu llamadas. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (option != 1 || option != 2);
	}

	private static Contact chooseContact() {
		// TODO Auto-generated method stub
		return null;
	}

	private static ContactType chooseContactType() {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<String> insertContactFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
