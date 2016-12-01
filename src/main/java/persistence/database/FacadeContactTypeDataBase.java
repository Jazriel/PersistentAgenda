package persistence.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ContactType;
import persistence.IFacadeContactTypePersistence;

public class FacadeContactTypeDataBase implements IFacadeContactTypePersistence {

	@Override
	public int getMaxContactTypeId() {
		// TODO Auto-generated method stub
		return -1;
	}

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
