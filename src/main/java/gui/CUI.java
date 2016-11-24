package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		int opcion = -1;
		do {
			System.out.println("Elija el sistema de persistencia: ");
			System.out.println("\t1) Base datos SQL");
			System.out.println("\t1) Sistema de persistencia mediante ficheros binarios");
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {
				opcion = bufferedReader.read();
				switch (opcion) {
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
				System.err.println(e.getStackTrace());
			}
		} while (opcion != 1 || opcion != 2);

		// Initialize the persistences
		contactPersitence = persistence.createContactPersistence();
		callPersitence = persistence.createCallPersistence();
		contactTypePersitence = persistence.createContactTypePersistence();

		// Choose the action
		opcion = -1;
		do {
			System.out.println("*****************Menu*****************");
			System.out.println("* 1) Insertar: ");
			System.out.println("* 2) Actualizar: ");
			System.out.println("* 3) Consultar: ");
			System.out.println("* 0) Salir: ");
			try {
				opcion = bufferedReader.read();
				switch (opcion) {
				case 0:
					System.out.println("Saliendo...");
					break;
				case 1:
					System.out.println("La opcion elegida ha sido <Insertar>");
					menuInsertar();
					break;
				case 2:
					System.out.println("La opcion elegida ha sido <Actualizar>");
					menuActualizar();
					break;
				case 3:
					System.out.println("La opcion elegida ha sido <Consultar>");
					menuConsultar();
					break;
				default:
					System.out.println("Opción incorrecta en Menu. Vuelva a intentarlo!");
					break;
				}
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
			}
		} while (opcion != 0);
	}

	private static void menuInsertar() {
		int opcion = 0;
		do {
			System.out.println("** 1) Nuevo contacto");
			System.out.println("** 2) Nueva llamada");
			System.out.println("** 3) Nuevo tipo de contacto");
			try {
				opcion = bufferedReader.read();
				switch (opcion) {
				case 1:
					System.out.println("La opcion elegida ha sido crear un nuevo contacto");
					List<String> attribs = new ArrayList<>();
					ContactType contactType = returnContactType();
					insertarCamposContacto();
					contactPersitence.saveContact(new Contact(attribs, contactType));
					break;
				case 2:
					System.out.println("La opcion elegida ha sido crear una nueva llamada");
					Contact contact = returnContact();
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
				System.err.println(e.getStackTrace());
			}
		} while (opcion != 1 || opcion != 2 || opcion != 3);
	}

	private static void menuActualizar() {
		int opcion = 0;
		int id = 0;
		do {
			System.out.println("** 1) Actualizar contacto");
			System.out.println("** 2) Actualizar llamada");
			System.out.println("** 3) Actualizar tipo de contacto");
			try {
				opcion = bufferedReader.read();
				switch (opcion) {
				case 1:
					System.out.println("La opcion elegida ha sido actualizar un contacto");
					System.out.println("Introduzca el id del contacto:");
					id = bufferedReader.read();
					List<String> attribs = new ArrayList<>();
					ContactType contactType = returnContactType();
					insertarCamposContacto();
					contactPersitence.updateContact(new Contact(id, attribs, contactType));
					break;
				case 2:
					System.out.println("La opcion elegida ha sido actualizar una llamada");
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
				System.err.println(e.getStackTrace());
			}
		} while (opcion != 1 || opcion != 2 || opcion != 3);
	}

	private static void menuConsultar() {
		int opcion;
		System.out.println("** 1) Todos los contactos");
		System.out.println("** 2) Todas las llamadas");
		System.out.println("** 3) Todos los tipos de contactos");
		try {
			opcion = bufferedReader.read();
			switch (opcion) {
			case 1:
				System.out.println("La opcion elegida ha sido todos los contactos");
				menuFiltrarOrdenarContactos(bufferedReader);
				break;
			case 2:
				System.out.println("La opcion elegida ha sido todas las llamadas");
				menuFiltrarOrdenarLlamadas(bufferedReader);
				break;
			case 3:
				System.out.println("La opcion elegida ha sido todos los tipos de contacto");
				menuFiltrarOrdenarTiposDeContactos(bufferedReader);
				break;
			default:
				System.out.println("Opción incorrecta en Menu consultar. Vuelva a intentarlo!");
				break;
			}
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	private static boolean menuFiltrarOrdenarContactos(BufferedReader bufferedReader) {
		int opcion;
		System.out.println("** 1) Filtrar por apellido");
		System.out.println("** 2) Filtrar por nombre");
		try {
			opcion = bufferedReader.read();
			switch (opcion) {
			case 1:
				System.out.println("La opcion elegida ha sido todos los contactos");
				// TODO
				break;
			case 2:
				System.out.println("La opcion elegida ha sido todas las llamadas");
				// TODO
				break;
			default:
				System.out.println("Opción incorrecta en Menu ordenar contactos. Vuelva a intentarlo!");
				break;
			}
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return false;
	}

	private static void menuFiltrarOrdenarTiposDeContactos(BufferedReader bufferedReader) {
		//TODO
	}

	private static void menuFiltrarOrdenarLlamadas(BufferedReader bufferedReader) {
		int opcion;
		System.out.println("** 1) Filtrar por contacto");
		System.out.println("** 2) Filtrar por fecha de realización");
		try {
			opcion = bufferedReader.read();
			switch (opcion) {
			case 1:
				System.out.println("La opcion elegida ha sido filtrar por contacto");
				// TODO
				break;
			case 2:
				System.out.println("La opcion elegida ha sido filtrar por fecha de realización");
				// TODO
				break;
			default:
				System.out.println("Opción incorrecta en Menu llamadas. Vuelva a intentarlo!");
				break;
			}
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	private static Contact returnContact() {
		// TODO Auto-generated method stub
		return null;
	}

	private static ContactType returnContactType() {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<String> insertarCamposContacto() {
		// TODO Auto-generated method stub
		return null;
	}

}
