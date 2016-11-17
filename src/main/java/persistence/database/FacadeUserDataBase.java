package persistence.database;

import java.sql.SQLException;
import model.Contact;
import persistence.IFacadeContactPersistence;

public class FacadeUserDataBase implements IFacadeContactPersistence {

	@Override
	public Contact getContactById(int id) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ResultSetManager resultSetManager = null;
		Contact contact = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledContactByIdStatement(id);
			resultSetManager = new ResultSetManager(stmFiller.executeQuery());
			contact = resultSetManager.getContact();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			resultSetManager.close();
			stmFiller.close();
			connection.close();
		}
		return contact;
	}

	@Override
	public void saveContact(Contact contact) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ResultSetManager resultSetManager = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledSaveContactStatement(contact.getId());
			stmFiller.executeQuery();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			stmFiller.close();
			connection.close();
		}
	}

	@Override
	public void updateContact(Contact contact) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledUpdateContactStatement(contact.getId());
			stmFiller.executeQuery();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			stmFiller.close();
			connection.close();
		}
	}



}
