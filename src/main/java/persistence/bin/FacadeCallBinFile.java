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

/**
 * Clase FacadeCallBinFile. Clase que se encarga del tratamiento del fichero
 * binario de llamadas.
 * 
 * @author Javier Martinez.
 * @author Daniel Puente.
 * @author Jaime Sagüillo.
 * @author Jorge Zamora.
 * @author Oscar Fernandez.
 */
public class FacadeCallBinFile implements IFacadeCallPersistence {

	/**
	 * Método readCalls. Método que se encarga de leer las llamadas del archivo
	 * Calls.
	 * 
	 * @return lista, Lista de las llamadas que se encuentran dentro del archivo
	 *         de Calls.
	 */
	@SuppressWarnings("unchecked")
	public List<Call> readCalls() {
		List<Call> calls = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream input = null;
		try {
			File file = new File("BinFiles\\Calls.dat");
			fileIn = new FileInputStream(file.getAbsolutePath());
			input = new ObjectInputStream(fileIn);
			calls = (List<Call>) input.readObject();

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return calls;
	}

	/**
	 * Método writeCalls. Método que se encarga de escibir una lista de llamadas
	 * en el archivo Calls.
	 * 
	 * @param calls
	 *            Como parámetro se le pasará una lista de llamadas.
	 */
	public void writeCalls(List<Call> calls) {
		FileOutputStream fileOut = null;
		ObjectOutputStream output = null;
		try {
			File file = new File("BinFiles\\Calls.dat");
			fileOut = new FileOutputStream(file.getAbsolutePath());
			output = new ObjectOutputStream(fileOut);
			output.writeObject(calls);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Método getCallById. Método que se encarga de obtener la llamada por un id
	 * de llamada.
	 * 
	 * @param i
	 *            Es el id del cual tendremos que obtener la llamada.
	 * @return call Es la llamada que tiene ese id.
	 */
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

	/**
	 * Método updateCall. Método que se encarga de actualizar una llamada, por
	 * el id de la llamada.
	 * 
	 * @param call
	 *            Llamada que queremos actualizar.
	 */
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

	/**
	 * Método saveCall. Método que se encarga de introducir una nueva llamada.
	 * 
	 * @param call
	 *            LLamada a añadir.
	 */
	@Override
	public void saveCall(Call call) {
		List<Call> calls = readCalls();
		calls.add(call);
		writeCalls(calls);
	}

	/**
	 * Método getAllCalls. Método que se encarga de obtener todas las llamadas.
	 * 
	 * @return lista, Lista de todas las llamadas.
	 */
	@Override
	public List<Call> getAllCalls() {
		return readCalls();
	}

	/**
	 * Método getFilterCalls. Filtra las llamadas.
	 * 
	 * @param field
	 *            campo a filtrar.
	 * @param timeStamp
	 *            fecha a filtrar.
	 * @return calls.
	 */
	@Override
	public List<Call> getFilterCalls(String field, Timestamp timeStamp) {
		List<Call> calls = new ArrayList<>();
		if (field.equals("call_Date")) {
			calls = getFilterByDate(timeStamp);
		}
		return calls;
	}

	/**
	 * Método getFilterCalls. Filtra las llamadas por fecha.
	 * 
	 * @param timeStamp.
	 * @return Lista de las llamadas filtradas.
	 */
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

	/**
	 * Método getFilterClass. Método que se encarga de filtrar por id.
	 * 
	 * @param field
	 *            Campo a filtrar.
	 * @param id
	 *            Id a filtrar.
	 */
	@Override
	public List<Call> getFilterCalls(String field, int id) {
		List<Call> calls = new ArrayList<>();
		if (field.equals("contact_id")) {
			calls = getFilterById(id);
		}
		return calls;
	}

	/**
	 * Método getFilterById Método que se encarga de filtrar por id.
	 * 
	 * @param id
	 *            Id por el que filtrar.
	 * @return filter_calls Flamadas filtradas.
	 */
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

	/**
	 * Método getOrderCalls. Método que se encarga de ordenar o por id o por
	 * fecha.
	 * 
	 * @param field
	 *            Campo por el que ordenar.
	 * @return calls Lista de llamadas ordenadas.
	 */
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
