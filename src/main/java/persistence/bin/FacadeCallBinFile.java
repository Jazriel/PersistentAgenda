package persistence.bin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Call;
import persistence.IFacadeCallPersistence;

public class FacadeCallBinFile implements IFacadeCallPersistence {

	@SuppressWarnings("unchecked")
	public List<Call> readCalls() {
		List<Call> calls = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream entrada = null;
		try {
			File fichero = new File("BinFiles\\Calls.dat");
			fileIn = new FileInputStream(fichero.getAbsolutePath());
			entrada = new ObjectInputStream(fileIn);
			calls = (List<Call>) entrada.readObject();

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(entrada!=null)
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
			File fichero = new File("BinFiles\\Calls.dat");
			fileOut = new FileOutputStream(fichero.getAbsolutePath());
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
		return readCalls();
	}

	@Override
	public List<Call> getFilterCalls(String field, Timestamp timeStamp) {
		List<Call> calls = null;
		if (field.equals("call_date")) {
			calls = getFilterByDate(timeStamp);
		}
		return calls;
	}

	private List<Call> getFilterByDate(Timestamp timeStamp) {
		List<Call> calls = readCalls();
		for (Call call : calls) {
			if (!call.getCallDate().equals(timeStamp)) {
				calls.remove(call);
			}
		}
		return calls;
	}

	@Override
	public List<Call> getFilterCalls(String field, int id) {
		List<Call> calls = null;
		if (field.equals("id")) {
			calls = getFilterById(id);
		}
		return calls;
	}

	private List<Call> getFilterById(int id) {
		List<Call> calls = readCalls();
		for (Call call : calls) {
			if (call.getId() != id) {
				calls.remove(call);
			}
		}
		return calls;
	}

	@Override
	public List<Call> getOrderCalls(String field) {
		List<Call> calls = readCalls();
		if (field.equals("id")) {
			Collections.sort(calls, Call.getOrderById());
		} else if (field.equals("call_date")) {
			Collections.sort(calls, Call.getOrderByDate());
		}
		return calls;
	}

}
