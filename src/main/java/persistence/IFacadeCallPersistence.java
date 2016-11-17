package persistence;

import model.Call;

public interface IFacadeCallPersistence {

	Call getCallById(int i);
	void updateCall(Call call);
	void saveCall(Call call);

}
