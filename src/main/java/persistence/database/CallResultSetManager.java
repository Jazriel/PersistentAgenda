package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import model.Call;
import model.Contact;

public class CallResultSetManager extends ABCResultSetManager<Call> {

	public CallResultSetManager(ResultSet rs) throws SQLException {
		super(rs);
		try{
			rs.next();
			next = getCallFromResultSet(rs);
			hasNext = true;
		}catch (Exception e) {
			hasNext = false;
		}
	}

	@Override
	public Call next() {
		if (!hasNext){
			throw new NoSuchElementException("No more");
		}
		Call thisCall = next;
		try {
			rs.next();
			next = getCallFromResultSet(rs);
		}catch (Exception e) {
			hasNext = false;
		}
		
		return thisCall;
	}
	
	@Override
	public boolean hasNext() {
		return hasNext;
	}
	
	private Call getCallFromResultSet(ResultSet rs) {
		Call call = null;
		try {
			Contact contact = new FactoryDataBase().
					createContactPersistence().getContactById(rs.getInt(2));
			call = new Call(rs.getInt(1), contact, rs.getDate(3).toString(),
					rs.getString(4), rs.getString(5));
		} catch (SQLException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return call;
	}

}
