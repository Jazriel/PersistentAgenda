package persistence;

import java.util.List;

import model.Call;
import model.Contact;

public interface FacadePersistence {

	Contact getContactById(int i);

	List<Call> getCallsByContact(Contact contact);

	void saveContact(Contact contact);

	void saveCall(Call call);

}
