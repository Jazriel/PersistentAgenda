package gui;

import java.util.ArrayList;
import java.util.List;

import model.Call;
import model.Contact;
import model.ContactType;
import persistence.IFacadeCallPersistence;
import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;
import persistence.bin.FactoryBinFile;

public class BinFilesInitialize {

	public static void main(String[] args) {
		// Insertamos los tipos de contacto
		List<ContactType> contactTypes = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			contactTypes.add(new ContactType(i, "Tipo00" + i));
		}
		contactTypes.add(new ContactType(10, "Tipo0" + 10));

		// Insertamos los contactos
		List<Contact> contacts = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			contacts.add(new Contact(1, "Nombre00" + i, "Apellidos00" + i, "Estimado000" + i, " Direccion00" + i,
					"Ciudad00" + i, "Prov00" + i, "CodPostal000" + i, "Region000" + i, "Pais000" + i,
					"NombreCompania000" + i, "Cargo00" + i, "TelefonoTrabajo00" + i, "ExtensionTrabajo00" + i,
					"TelefonoMovil00" + i, "NumFax00" + i, " NomCorreoElectronico00" + i + "@ubu.es", "Notas00" + i,
					contactTypes.get(i-1)));
		}
		contacts.add(new Contact(10, "Nombre00" + 10, "Apellidos00" + 10, "Estimado000" + 10, " Direccion00" + 10,
				"Ciudad00" + 10, "Prov00" + 10, "CodPostal000" + 10, "Region000" + 10, "Pais000" + 10,
				"NombreCompania000" + 10, "Cargo00" + 10, "TelefonoTrabajo00" + 10, "ExtensionTrabajo00" + 10,
				"TelefonoMovil00" + 10, "NumFax00" + 10, " NomCorreoElectronico00" + 10 + "@ubu.es", "Notas00" + 10,
				contactTypes.get(9)));
		
		//Insertamos las llamadas
		List<Call> calls = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			calls.add(new Call(contacts.get(i-1), "AsuntoLlamada00" + i , "NotaLlamada" + i));
		}
		calls.add(new Call(contacts.get(9), "AsuntoLlamada00" + 9 , "NotaLlamada" + 9));

		IFactoryPersistence binFactoryPersistence = new FactoryBinFile();

		IFacadeCallPersistence callPersistence = binFactoryPersistence.createCallPersistence();
		IFacadeContactPersistence contactPersistence = binFactoryPersistence.createContactPersistence();
		IFacadeContactTypePersistence contactTypePersistence = binFactoryPersistence.createContactTypePersistence();

		for(int i = 0;i<=9;i++)
		{
			callPersistence.saveCall(calls.get(i));
			contactPersistence.saveContact(contacts.get(i));
			contactTypePersistence.saveContactType(contactTypes.get(i));
		}
	}
}
