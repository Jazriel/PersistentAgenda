package persistence.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import model.Call;
import model.Contact;
import persistence.FacadePersistence;

public class FacadeDataBase implements FacadePersistence {

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
	public List<Call> getCallsByContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCall(Call call) {
		// TODO Auto-generated method stub

	}

}
