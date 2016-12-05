package persistence.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ContactType;
import persistence.IFacadeContactTypePersistence;
/**
 * Clase FacadeContactTypeDataBase
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez 
 */
public class FacadeContactTypeDataBase implements IFacadeContactTypePersistence {
	/**
	 * getContactTypeById(int id). Metodo que devuelve un tipo decontacto por un id
	 * @param id, el id del tipo de contacto.
	 * @return contactType
	 */
	@Override
	public ContactType getContactTypeById(int id) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<ContactType> resultSetManager = null;
		ContactType contactType = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledContactTypeByIdStatement(id);
			resultSetManager = new ContactTypeResultSetManager(stmFiller.executeQuery());
			contactType = resultSetManager.next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return contactType;
	}
	/**
	 * updateContactType(ContactType contactType). Metodo que actualiza un tipo de contacto con nuevos datos.
	 * @param contact, un tipo de contacto.
	 */
	@Override
	public void updateContactType(ContactType contactType) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledUpdateContactTypeStatement(contactType);
			stmFiller.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	/**
	 * saveContactType(ContactType contactType). Metodo que guarda un tipo de contacto.
	 * @param contactType, el tipo de contacto que guardaremos.
	 */
	@Override
	public void saveContactType(ContactType contactType) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledSaveContactTypeStatement(contactType);
			stmFiller.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}
	/**
	 * getAllContactTypes(). Metodo que coge todos los tipos de contactos.
	 * @return contactTypes
	 */
	@Override
	public List<ContactType> getAllContactTypes() {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<ContactType> resultSetManager = null;
		List<ContactType> contactTypes = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getAllContactTypesStatement();
			resultSetManager = new ContactTypeResultSetManager(stmFiller.executeQuery());
			contactTypes = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				contactTypes.add(resultSetManager.next());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return contactTypes;
	}

}
