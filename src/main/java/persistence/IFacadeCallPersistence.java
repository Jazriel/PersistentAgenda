package persistence;

import java.sql.Timestamp;
import java.util.List;

import model.Call;

public interface IFacadeCallPersistence {

	Call getCallById(int i);

	void updateCall(Call call);

	void saveCall(Call call);

	int getMaxCallId();

	List<Call> getAllCalls();

	List<Call> getFilterCalls(String field, Timestamp timeStamp);

	List<Call> getFilterCalls(String field, int id);

	List<Call> getOrderCalls(String string);

}
