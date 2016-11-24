package persistence.database;

import java.sql.SQLException;

import model.ContactType;
import persistence.IFacadeContactTypePersistence;

public class FacadeContactTypeDataBase implements IFacadeContactTypePersistence {

	@Override
	public ContactType getContactTypeById(int id) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<ContactType> resultSetManager = null;
		ContactType contactType = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			// TODO : Evaluate if result set manager and statement manager need multiple implementations one for each kind of object.
			stmFiller.getFilledContactTypeByIdStatement(id);
			resultSetManager = new ContactTypeResultSetManager(stmFiller.executeQuery());
			contactType = resultSetManager.getNext();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			resultSetManager.close();
			stmFiller.close();
			connection.close();
		}
		return contactType;
	}

	@Override
	public void updateContactType(ContactType contactType) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			// TODO : Evaluate if result set manager and statement manager need multiple implementations one for each kind of object.
			stmFiller.getFilledSaveContactTypeStatement(contactType);
			stmFiller.executeQuery();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			stmFiller.close();
			connection.close();
		}
	}

	@Override
	public void saveContactType(ContactType contactType) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			// TODO : Evaluate if result set manager and statement manager need multiple implementations one for each kind of object.
			stmFiller.getFilledUpdateContactTypeStatement(contactType);
			stmFiller.executeQuery();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			stmFiller.close();
			connection.close();
		}

	}

}
