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
		//Choose the persistence system
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
		
		//Initialize the persistences
		contactPersitence = persistence.createContactPersistence();
		callPersitence = persistence.createCallPersistence();
		contactTypePersitence = persistence.createContactTypePersistence();
		
		//Choose the action
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
		boolean retorno = false;
		do{
		System.out.println("** 1) Nuevo contacto");
		System.out.println("** 2) Nueva llamada");
		System.out.println("** 3) Nuevo tipo de contacto");
		try {
			opcion = bufferedReader.read();
			switch (opcion) {
			case 1:
				System.out.println("La opcion elegida ha sido crear un nuevo contacto");
				Integer id = new Integer(0);
				List<String> attribs = new ArrayList<>();
				ContactType contactType = returnContactType();
				insertarCamposContacto();
				//TODO ¿Cuales el id?
				contactPersitence.saveContact(new Contact(id, attribs, contactType));
				break;
			case 2:
				System.out.println("La opcion elegida ha sido crear una nueva llamada");
				Integer id1 = new Integer(0);
				Contact contact = returnContact();
				System.out.println("Introduzca el tema de conversación:");
				String subject = bufferedReader.readLine();
				System.out.println("Introduzca alguna anotación sobre la llamada:");
				String notes = bufferedReader.readLine();
				//TODO ¿null en datetime?
				callPersitence.saveCall(new Call(id1, contact, null, subject, notes));
				break;
			case 3:
				System.out.println("La opcion elegida ha sido crear un nuevo tipo de contacto");
				Integer id2 = new Integer(0);
				System.out.println("Introduzca el nombre del tipo de contacto:");
				String name = bufferedReader.readLine();
				contactTypePersitence.saveContactType(new ContactType(id2, name));
				break;
			default:
				System.out.println("Opción incorrecta en Menu insertar. Vuelva a intentarlo!");
				break;
			}
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		}while(opcion!= 1 || opcion!= 2 || opcion!= 3);
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

	private static boolean menuActualizar() {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean menuConsultar() {
		int opcion;
		boolean retorno = false;
		System.out.println("** 1) Todos los contactos");
		System.out.println("** 2) Todas las llamadas");
		System.out.println("** 3) Todos los tipos de contactos");
		try {
			opcion = bufferedReader.read();
			switch (opcion) {
			case 1:
				System.out.println("La opcion elegida ha sido todos los contactos");
				retorno = menuFiltrarOrdenarContactos(bufferedReader);
				break;
			case 2:
				System.out.println("La opcion elegida ha sido todas las llamadas");
				retorno= menuFiltrarOrdenarLlamadas(bufferedReader);
				break;
			case 3:
				System.out.println("La opcion elegida ha sido todos los tipos de contacto");
				retorno = menuFiltrarOrdenarTiposDeContactos(bufferedReader);
				break;
			default:
				System.out.println("Opción incorrecta en Menu consultar. Vuelva a intentarlo!");
				break;
			}
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return retorno;
	}

	private static boolean menuFiltrarOrdenarTiposDeContactos(BufferedReader bufferedReader) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean menuFiltrarOrdenarLlamadas(BufferedReader bufferedReader) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean menuFiltrarOrdenarContactos(BufferedReader bufferedReader) {
		// TODO Auto-generated method stub
		return false;
	}
	}
