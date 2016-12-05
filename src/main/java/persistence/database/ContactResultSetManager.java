package persistence.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Contact;
import model.ContactType;

/**
 * Clase ContactResultSetManager
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sag�illo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class ContactResultSetManager extends ABCResultSetManager<Contact> {
	/**
	 * ContactResultSetManager(ResultSet rs).Constructor de clase.
	 * 
	 * @param rs,
	 *            registros de las tablas.
	 * @throws SQLException
	 *             excepci�n de SQL.
	 */
	public ContactResultSetManager(ResultSet rs) throws SQLException {
		super(rs);
		try {
			rs.next();
			next = getContactFromResultSet(rs);
		} catch (Exception e) {
			hasNext = false;
		}
	}

	/**
	 * next(). Metodo que devuelve el siguiente elemento.
	 * 
	 * @return thisContact.
	 */
	@Override
	public Contact next() {
		if (!hasNext) {
			throw new NoSuchElementException("No more");
		}
		Contact thisContact = next;
		try {
			rs.next();
			next = getContactFromResultSet(rs);
		} catch (Exception e) {
			hasNext = false;
		}

		return thisContact;
	}

	/**
	 * getContactFromResultSet(ResultSet rs). Metodo que nos devuelve un
	 * contacto.
	 * 
	 * @param rs,
	 *            lista de la tabla.
	 * @return contact.
	 */
	private Contact getContactFromResultSet(ResultSet rs) {
		Contact contact = null;
		try {
			ResultSetMetaData rsMD = rs.getMetaData();
			String beforeLastColumnName = rsMD.getColumnName(rsMD.getColumnCount() - 1);
			List<String> attribs = new LinkedList<>();
			int id = rs.getInt(1);
			for (int i = 2; beforeLastColumnName != rsMD.getColumnName(i); ++i) {
				attribs.add(rs.getString(i));
			}
			ContactType ct = new ContactType(rs.getInt(rsMD.getColumnCount() - 1), null); // TODO
			attribs.add(rs.getString(rsMD.getColumnCount()));
			contact = new Contact(id, attribs, ct);
		} catch (SQLException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		return contact;
	}

	/**
	 * hasNext(). Metodo que nos dice si hay siguiente.
	 * 
	 * @return hasNext.
	 */
	@Override
	public boolean hasNext() {
		return hasNext;
	}

}
