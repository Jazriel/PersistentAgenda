package persistence.bin;

import java.sql.Timestamp;
import java.util.List;

import model.Call;
import persistence.IFacadeCallPersistence;

public class FacadeCallBinFile implements IFacadeCallPersistence{
	// TODO Implementarlo mediante diccionarios(HashMap) deberia hacerlo sencillo 
	@Override
	public Call getCallById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCall(Call call) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCall(Call call) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxCallId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Call> getCalls(String discriminator, String field, Timestamp timeStamp) {
		return null;
		
	}

	@Override
	public List<Call> getCalls(String discriminator, String field, int id) {
		return null;
		
	}

	

}
