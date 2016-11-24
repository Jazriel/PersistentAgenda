package persistence;

import java.sql.Timestamp;

import model.Call;

public interface IFacadeCallPersistence {

	Call getCallById(int i);
	void updateCall(Call call);
	void saveCall(Call call);
	int getMaxCallId();
	void getCalls(String discriminator, String field, Timestamp timeStamp);
	void getCalls(String discriminator, String field, int id);

}
