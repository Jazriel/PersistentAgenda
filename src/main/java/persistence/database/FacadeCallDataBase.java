package persistence.database;

import java.sql.SQLException;

import model.Call;
import persistence.IFacadeCallPersistence;

public class FacadeCallDataBase implements IFacadeCallPersistence {


	@Override
	public int getMaxCallId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Call getCallById(int id) {
		SingletonConnection connection = null;
		StatementManager stmFiller = null;
		ABCResultSetManager<Call> resultSetManager = null;
		Call call = null;
		try {
			connection = SingletonConnection.getInstance();
			stmFiller = new StatementManager();
			stmFiller.getFilledCallByIdStatement(id);
			resultSetManager = new CallResultSetManager(stmFiller.executeQuery());
			call = resultSetManager.next();
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			if(resultSetManager!=null){
			resultSetManager.close();}
			if(stmFiller!=null){
			stmFiller.close();}
			if(connection!=null){
			connection.close();}
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
			stmFiller.executeQuery();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			if(stmFiller!=null){
			stmFiller.close();}
			if(connection!=null){
			connection.close();}
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
			stmFiller.executeQuery();
		}catch (SQLException e) {
			System.err.println(e.getStackTrace());
		}finally {
			if(stmFiller!=null){
			stmFiller.close();}
			if(connection!=null){
			connection.close();}
		}
	}


}
