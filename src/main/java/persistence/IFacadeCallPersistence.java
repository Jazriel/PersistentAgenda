package persistence;

import java.sql.Timestamp;
import java.util.List;

import model.Call;
/**
 * Interface IFacadeCallPersistence
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez 
 */
public interface IFacadeCallPersistence {

	Call getCallById(int i);

	void updateCall(Call call);

	void saveCall(Call call);


	List<Call> getAllCalls();

	List<Call> getFilterCalls(String field, Timestamp timeStamp);

	List<Call> getFilterCalls(String field, int id);

	List<Call> getOrderCalls(String string);

}
