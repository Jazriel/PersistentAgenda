package persistence.bin;

import java.sql.Timestamp;

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
	public void getCalls(String discriminator, String field, Timestamp timeStamp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCalls(String discriminator, String field, int id) {
		// TODO Auto-generated method stub
		
	}

	

}
