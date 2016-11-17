package persistence.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Contact;
import model.ContactType;

public class ResultSetManager {

	private ResultSet rs;

	public ResultSetManager(ResultSet rs) {
		this.rs = rs;
	}

	public Contact getContact() throws SQLException {
		rs.next();
		ResultSetMetaData rsMD = rs.getMetaData();
		String lastColumnName = rsMD.getColumnTypeName(rsMD.getColumnCount() - 2);
		List<String> attribs = new LinkedList<>();
		int id = rs.getInt(0);
		for (int i = 1; lastColumnName != rsMD.getColumnTypeName(i); ++i) {
			attribs.add(rs.getString(i));
		}
		ContactType ct = new ContactType(rs.getInt(rsMD.getColumnCount() - 2), rs.getString(rsMD.getColumnCount() - 1));
		return new Contact(id, attribs, ct);
	}

	public void close() {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			rs = null;
		} catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}
	}

}
