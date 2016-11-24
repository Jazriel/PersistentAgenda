package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import model.ContactType;

public class ContactTypeResultSetManager extends ABCResultSetManager<ContactType> {

	public ContactTypeResultSetManager(ResultSet rs) throws SQLException {
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
	public ContactType next() {
		ContactType contactType = null;
		try {
			rs.next();
			thisId = rs.getInt(0);
			contactType = new ContactType(thisId, rs.getString(1));
		}catch (SQLException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return contactType;
	}

	@Override
	public boolean hasNext() {
		return lastId!=thisId;
	}
}
