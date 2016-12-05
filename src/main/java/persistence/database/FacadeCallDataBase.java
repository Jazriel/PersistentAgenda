package persistence.database;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Call;
import persistence.IFacadeCallPersistence;

public class FacadeCallDataBase implements IFacadeCallPersistence {

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
