package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ABCResultSetManager<E> {

	protected ResultSet rs;
	
	public ABCResultSetManager(ResultSet rs) {
		this.rs = rs;
	}

	public abstract E getNext() throws SQLException;
	
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
