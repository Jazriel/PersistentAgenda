package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ContactType;

public class ContactTypeResultSetManager extends ABCResultSetManager<ContactType> {

	public ContactTypeResultSetManager(ResultSet rs) {
		super(rs);
	}

	@Override
	public ContactType getNext() throws SQLException {
		rs.next();
		return new ContactType(rs.getInt(0), rs.getString(1));
	}

}
