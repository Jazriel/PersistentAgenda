package persistence.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Contact;
import model.ContactType;

public class ContactResultSetManager extends ABCResultSetManager<Contact> {

	public ContactResultSetManager(ResultSet rs) throws SQLException {
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
	public Contact next() {
		Contact contact = null;
		try {
			rs.next();
			ResultSetMetaData rsMD = rs.getMetaData();
			String lastColumnName = rsMD.getColumnTypeName(rsMD.getColumnCount() - 2);
			List<String> attribs = new LinkedList<>();
			int id = rs.getInt(0);
			thisId = id;
			for (int i = 1; lastColumnName != rsMD.getColumnTypeName(i); ++i) {
				attribs.add(rs.getString(i));
			}
			ContactType ct = new ContactType(rs.getInt(rsMD.getColumnCount() - 2),
					rs.getString(rsMD.getColumnCount() - 1));
			contact = new Contact(id, attribs, ct);
		} catch (SQLException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return contact;
	}

	@Override
	public boolean hasNext() {
		return thisId!=lastId;
	}

}
