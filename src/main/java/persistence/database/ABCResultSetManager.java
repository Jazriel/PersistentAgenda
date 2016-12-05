package persistence.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * Clase abstracta ABCResultSetManager.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 * @param <E>,
 *            generico.
 */
public abstract class ABCResultSetManager<E> implements Iterator<E> {

	/**
	 * Coge el siguiente elemento.
	 */
	protected E next;
	/**
	 * Comprueba que hay mas elementos.
	 */
	protected boolean hasNext;
	/**
	 * las filas de la BD
	 */
	protected ResultSet rs;

	/**
	 * ABCResultSetManager(ResultSet rs). Metodo que nos dice si hay un
	 * siguiente
	 * 
	 * @param rs,
	 *            tablas de BD
	 */
	public ABCResultSetManager(ResultSet rs) {
		this.rs = rs;
		hasNext = true;
	}

	/**
	 * next().
	 */
	@Override
	public abstract E next();

	/**
	 * hasNext().
	 */
	@Override
	public abstract boolean hasNext();

	/**
	 * close(). MEtodo que cierra la lectura del ResultSet
	 */
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
