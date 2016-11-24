package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Call;

public class CallResultSetManager extends ABCResultSetManager<Call> {

	public CallResultSetManager(ResultSet rs) {
		super(rs);
	}

	@Override
	public Call getNext() throws SQLException {
		rs.next();
		int id = rs.getInt(0);
		// TODO 
		int contact = rs.getInt(1);
		String callDate = rs.getString(2);
		String subject  = rs.getString(3);
		String notes    = rs.getString(4);
		FacadeContactDataBase fcdb = new FacadeContactDataBase();
		return new Call(id, fcdb.getContactById(contact), callDate, subject, notes);
	}

}
