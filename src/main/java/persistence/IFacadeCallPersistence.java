package persistence;

import java.sql.Timestamp;
import java.util.List;

import model.Call;

public interface IFacadeCallPersistence {

	Call getCallById(int i);

	void updateCall(Call call);

	void saveCall(Call call);

	int getMaxCallId();

	List<Call> getCalls(String discriminator, String field, Timestamp timeStamp);

	List<Call> getCalls(String discriminator, String field, int id);

}
