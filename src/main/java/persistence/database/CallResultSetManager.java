package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import model.Call;

public class CallResultSetManager extends ABCResultSetManager<Call> {

	public CallResultSetManager(ResultSet rs) throws SQLException {
		super(rs);
		thisId = -1;
		if (rs.last()){
			lastId = rs.getInt(0);
		} else {
			lastId = -1;
		}
		rs.beforeFirst();
	}

	private int lastId;
	private int thisId;
	
	@Override
	public Call next() {
		Call call = null;
		try {
			rs.next();
			int id = rs.getInt(0);
			thisId = id;
			// TODO
			int contact = rs.getInt(1);
			String callDate = rs.getString(2);
			String subject = rs.getString(3);
			String notes = rs.getString(4);
			FacadeContactDataBase fcdb = new FacadeContactDataBase();
			call = new Call(id, fcdb.getContactById(contact), callDate, subject, notes);
		} catch (SQLException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return call;
	}
	
	@Override
	public boolean hasNext() {
		return thisId!=lastId;
	}

}
