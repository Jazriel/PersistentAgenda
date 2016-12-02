package persistence.bin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Call;
import persistence.IFacadeCallPersistence;

public class FacadeCallBinFile implements IFacadeCallPersistence {
	// TODO Implementarlo mediante diccionarios(HashMap) deberia hacerlo
	// sencillo
	public List<Call> readCalls() {
		List<Call> calls = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream entrada = null;
		try {
			fileIn = new FileInputStream("D:\\GitRepositorio\\DisManSof\\PR1\\BinFiles\\Calls.txt");
			entrada = new ObjectInputStream(fileIn);
			calls = (List<Call>)entrada.readObject();

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				entrada.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return calls;
	}

	public void writeCalls(List<Call> calls) {
		FileOutputStream fileOut = null;
		ObjectOutputStream salida = null;
		try {
			fileOut = new FileOutputStream("D:\\GitRepositorio\\DisManSof\\PR1\\BinFiles\\Calls.txt");
			salida = new ObjectOutputStream(fileOut);
			salida.writeObject(calls);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				salida.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public Call getCallById(int i) {
		List<Call> calls = readCalls();
		for (Call call : calls) {
			if (call.getId() == i) {
				return call;
			}
		}
		return null;
	}

	@Override
	public void updateCall(Call call) {
		List<Call> calls = readCalls();
		for (Call c : calls) {
			if (c.getId() == call.getId()) {
				c = call;
			}
		}
		writeCalls(calls);
	}

	@Override
	public void saveCall(Call call) {
		List<Call> calls = readCalls();
		calls.add(call);
		writeCalls(calls);
	}

	@Override
	public int getMaxCallId() {
		return readCalls().size();
	}

	@Override
	public List<Call> getAllCalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Call> getFilterCalls(String field, Timestamp timeStamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Call> getFilterCalls(String field, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Call> getOrderCalls(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
