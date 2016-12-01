package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import model.ContactType;

public class ContactTypeResultSetManager extends ABCResultSetManager<ContactType> {

	public ContactTypeResultSetManager(ResultSet rs) throws SQLException {
		super(rs);
		try{
			rs.next();
			next = getContactTypesFromResultSet(rs);
		}catch (Exception e) {
			hasNext = false;
		}
	}
	
	@Override
	public ContactType next() {
		if (!hasNext){
			throw new NoSuchElementException("No more");
		}
		ContactType thisContactType = next;
		try {
			rs.next();
			next = getContactTypesFromResultSet(rs);
		}catch (Exception e) {
			hasNext = false;
		}
		
		return thisContactType;
	}
	
	private ContactType getContactTypesFromResultSet(ResultSet rs) {
		ContactType contactType = null;
		try {
			contactType = new ContactType(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return contactType;
	}
	
	@Override
	public boolean hasNext() {
		return hasNext;
	}
}
