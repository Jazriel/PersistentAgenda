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
	public List<Call> getCallsByContactId(int i) {
		List<Call> cs = readCalls();
		List<Call> calls = new ArrayList<>();
		for (Call call : cs) {
			if (call.getContact().getId() == i) {
				calls.add(call);
			}
		}
		return calls;
	}

	@Override
	public void updateCall(Call call) {
		List<Call> calls = readCalls();
		int index = -1;
		for (Call c : calls) {
			++index;
			if (c.getId() == call.getId()) {
				calls.remove(index);
				calls.add(index, call);
				break;
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
	public List<Call> getAllCalls() {
		return readCalls();
	}

	@Override
	public List<Call> getFilterCalls(String field, Timestamp timeStamp) {
		List<Call> calls = new ArrayList<>();
		if (field.equals("call_Date")) {
			calls = getFilterByDate(timeStamp);
		}
		return calls;
	}

	private List<Call> getFilterByDate(Timestamp timeStamp) {
		List<Call> calls = readCalls();
		List<Call> filter_calls = new ArrayList<>();
		for (Call call : calls) {
			if (call.getCallDate().substring(0, 10).equals(timeStamp.toString().substring(0, 10))) {
				filter_calls.add(call);
			}
		}
		return filter_calls;
	}

	@Override
	public List<Call> getFilterCalls(String field, int id) {
		List<Call> calls = new ArrayList<>();
		if (field.equals("contact_id")) {
			calls = getFilterById(id);
		}
		return calls;
	}

	private List<Call> getFilterById(int id) {
		List<Call> calls = readCalls();
		List<Call> filter_calls = new ArrayList<>();
		for (Call call : calls) {
			if (call.getContact().getId() == id) {
				filter_calls.add(call);
			}
		}
		return filter_calls;
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
