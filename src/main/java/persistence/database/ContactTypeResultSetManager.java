package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import model.ContactType;

/**
 * Clase ContactTypeResultSetManager
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class ContactTypeResultSetManager extends ABCResultSetManager<ContactType> {
	/**
	 * ContactTypeResultSetManager(ResultSet rs).Constructor de clase.
	 * 
	 * @param rs,
	 *            registros de las tablas
	 * @throws SQLException
	 *             excepción.
	 */
	public ContactTypeResultSetManager(ResultSet rs) throws SQLException {
		super(rs);
		try {
			rs.next();
			next = getContactTypesFromResultSet(rs);
		} catch (Exception e) {
			hasNext = false;
		}
	}

	/**
	 * next(). Metodo que devuelve el siguiente elemento.
	 * 
	 * @return thisContactType
	 */
	@Override
	public ContactType next() {
		if (!hasNext) {
			throw new NoSuchElementException("No more");
		}
		ContactType thisContactType = next;
		try {
			rs.next();
			next = getContactTypesFromResultSet(rs);
		} catch (Exception e) {
			hasNext = false;
		}

		return thisContactType;
	}

	/**
	 * getContactTypesFromResultSet(ResultSet rs). Metodo que nos devuelve un
	 * tipo decontacto.
	 * 
	 * @param rs,
	 *            lista de la tabla.
	 * @return contactType
	 */
	private ContactType getContactTypesFromResultSet(ResultSet rs) {
		ContactType contactType = null;
		try {
			contactType = new ContactType(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return contactType;
	}

	/**
	 * hasNext(). Metodo que nos dice si hay siguiente.
	 * 
	 * @return hasNext
	 */
	@Override
	public boolean hasNext() {
		return hasNext;
	}
}
