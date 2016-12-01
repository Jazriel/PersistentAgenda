package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public abstract class ABCResultSetManager<E> implements Iterator<E> {

	
	protected E next;
	protected boolean hasNext;
	protected ResultSet rs;
	
	public ABCResultSetManager(ResultSet rs) {
		this.rs = rs;
		hasNext = true;
	}
	
	@Override
	public abstract E next();
	
	@Override
	public abstract boolean hasNext();
	
	public void close() {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			rs = null;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
