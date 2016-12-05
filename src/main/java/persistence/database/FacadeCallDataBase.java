package persistence.database;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Call;
import persistence.IFacadeCallPersistence;

/**
 * Clase FacadeCallDataBase
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class FacadeCallDataBase implements IFacadeCallPersistence {

	/**
	 * getCallById(int id). Metodo que devuelve una llamada por un id
	 * 
	 * @param id,
	 *            el id de la llamada.
	 * @return call
	 */
	@Override
	public List<Call> getCallsByContactId(int id) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Call> resultSetManager = null;
		List<Call> call = new ArrayList<>();
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledCallsByContactIdStatement(id);
			resultSetManager = new CallResultSetManager(stmFiller.executeQuery());
			while (resultSetManager.hasNext) {
				call.add(resultSetManager.next());
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return call;
	}

	/**
	 * updateCall(Call call). Metodo que actualiza una llamada con nuevos datos.
	 * 
	 * @param call,
	 *            una llamada.
	 */
	@Override
	public void updateCall(Call call) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledUpdateCallStatement(call);
			stmFiller.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * saveCall(Call call). Metodo que guarda una llamada.
	 * 
	 * @param call,
	 *            la llamada que guardaremos.
	 */
	@Override
	public void saveCall(Call call) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledSaveCallStatement(call);
			stmFiller.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * getFilterCalls(String field, Timestamp timeStamp). Metodo que filtra las
	 * llamadas.
	 * 
	 * @param field,
	 *            campo por el que filtrar.
	 * @param timeStamp,
	 *            la fecha de la llamada.
	 * @return calls
	 */
	public List<Call> getFilterCalls(String field, Timestamp timeStamp) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Call> resultSetManager = null;
		List<Call> calls = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilterCallsStatement(field, timeStamp);
			resultSetManager = new CallResultSetManager(stmFiller.executeQuery());
			calls = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				calls.add(resultSetManager.next());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return calls;
	}

	/**
	 * getFilterCalls(String field, int id). Metodo para filtrar llamadas
	 * 
	 * @param field,
	 *            campo por el que filtrar.
	 * @param id,
	 *            id de la llamada.
	 * @return calls
	 */
	public List<Call> getFilterCalls(String field, int id) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Call> resultSetManager = null;
		List<Call> calls = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilterCallsStatement(field, id);
			resultSetManager = new CallResultSetManager(stmFiller.executeQuery());
			calls = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				calls.add(resultSetManager.next());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return calls;
	}

	/**
	 * getAllCalls(). Metodo que coge todas las llamadas
	 * 
	 * @return calls
	 */
	@Override
	public List<Call> getAllCalls() {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Call> resultSetManager = null;
		List<Call> calls = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getAllCallsStatement();
			resultSetManager = new CallResultSetManager(stmFiller.executeQuery());
			calls = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				calls.add(resultSetManager.next());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return calls;
	}

	/**
	 * getOrderCalls(String field). Metodo que coge las llamadas ordenadas por
	 * campo.
	 * 
	 * @param field,
	 *            campo por el que ordenar
	 * @return calls
	 */
	@Override
	public List<Call> getOrderCalls(String field) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Call> resultSetManager = null;
		List<Call> calls = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getOrderCallsStatement(field);
			resultSetManager = new CallResultSetManager(stmFiller.executeQuery());
			calls = new ArrayList<>();
			while (resultSetManager.hasNext()) {
				calls.add(resultSetManager.next());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (resultSetManager != null) {
				resultSetManager.close();
			}
			if (stmFiller != null) {
				stmFiller.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return calls;
	}
}
