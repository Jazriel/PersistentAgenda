package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import model.Call;
import model.Contact;
import persistence.util.PrintAtDepth;

public class IFacadeCallPersistenceTest {

	private List<IFactoryPersistence> persistences;

	@Before
	public void beforeTest() {
		PrintAtDepth.print(0, "FacadeCallPersistenceTest start");
		IAbstractPersistenceFactory aPersistenceFactory = new AbstractPersistenceFactory();
		persistences = new ArrayList<>();
		persistences.add(aPersistenceFactory.getDBPersistence());
		persistences.add(aPersistenceFactory.getBinPersistence());
	}

	@Test
	public void testSaveCall() {
		PrintAtDepth.print(1, "saveCall start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getIncMaxCallId(facadeCallPersistence);
			facadeCallPersistence.saveCall(new Call(id, new Contact(1, attribs, null),
					new Timestamp(System.currentTimeMillis()).toString(), "Subject", ""));
			boolean b = false;
			for (Call call : facadeCallPersistence.getAllCalls()) {
				if (call.getId() == id) {
					b = true;
				}
			}
			assertTrue(b);
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "saveCall pass");
	}

	@Test
	public void testGetCallsByContactId() {
		PrintAtDepth.print(1, "getCallById start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
			int contactId = 1;
			List<Call> calls = facadeCallPersistence.getCallsByContactId(contactId);
			assertTrue(calls.stream().filter(c -> c.getContact().getId()==contactId).collect(Collectors.toList()).size()==calls.size());
			for (Call call : calls) {
				assertTrue(call.getContact().getId()==contactId);
			}

			List<Call> allCalls = facadeCallPersistence.getAllCalls();
			assertTrue(allCalls.stream().filter(c -> c.getContact().getId()== contactId).collect(Collectors.toList()).size()==calls.size());
			
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getCallById pass");
	}

	@Test
	public void testUpdateCall() {
		PrintAtDepth.print(1, "updateCall start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			String subject = "Sujeto";
			int id = getIncMaxCallId(facadeCallPersistence);
			facadeCallPersistence.saveCall(new Call(id, new Contact(1, attribs, null),
					new Timestamp(System.currentTimeMillis()).toString(), "SubjectFalso", ""));
			Call call = facadeCallPersistence.getCallsByContactId(1).stream().filter(c -> c.getId() == id)
					.collect(Collectors.toList()).get(0);
			
			assertTrue(call != null);
			PrintAtDepth.print(3, "First check on call was not null");
			call.setSubject(subject);

			facadeCallPersistence.updateCall(call);
			call = null;
			call = facadeCallPersistence.getAllCalls().stream().filter(c -> c.getId() == id)
					.collect(Collectors.toList()).get(0);
			assertTrue(call != null);
			PrintAtDepth.print(3, "Second check on call was not null");
			assertTrue(call.getSubject().equals(subject));
			PrintAtDepth.print(3, "Id was correct.");
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "updateCall pass");
	}

	@Test
	public void testGetAllCalls() {
		PrintAtDepth.print(1, "getAllCalls start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();

			List<Call> callsBefore = facadeCallPersistence.getAllCalls();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getIncMaxCallId(facadeCallPersistence);
			facadeCallPersistence.saveCall(new Call(id, new Contact(1, attribs, null),
					new Timestamp(System.currentTimeMillis()).toString(), "Subject", ""));
			List<Call> callsAfter = facadeCallPersistence.getAllCalls();
			List<Integer> callIds = new ArrayList<Integer>();
			for (Call call : callsAfter) {
				callIds.add(call.getId());
			}
			for (Call call : callsBefore) {
				if (callIds.contains(call.getId())) {
					callIds.remove(Integer.valueOf(call.getId()));
				} else {
					assertTrue(false);
				}
			}
			assertTrue(callIds.remove(Integer.valueOf(id)));
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getAllCalls pass");
	}

	private int getIncMaxCallId(IFacadeCallPersistence persistence) {
		int ret = 0;
		List<Call> calls = persistence.getAllCalls();
		for (Call call : calls) {
			if (call.getId() > ret) {
				ret = call.getId();
			}
		}

		return ret + 1;
	}

	@Test
	public void testGetOrderCalls() {
		PrintAtDepth.print(1, "getOrderCalls start");

		for (IFactoryPersistence persistence : persistences) {
			String field = "id";
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
			List<Call> calls = facadeCallPersistence.getOrderCalls(field);
			List<Integer> contIds = new ArrayList<>();

			contIds.add(calls.get(0).getId());
			for (int i = 1; i < calls.size(); i++) {
				if (calls.get(i - 1).getId() <= calls.get(i).getId()) {
					contIds.add(calls.get(i).getId());
				} else {
					assertTrue(false);
				}
			}

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getOrderCalls pass");
	}

	@Test
	public void testFilterCallsId() {
		PrintAtDepth.print(1, "getFilterCalls start");

		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
			Call callForId = facadeCallPersistence.getAllCalls().get(0);

			List<Call> calls = facadeCallPersistence.getFilterCalls("contact_id", callForId.getContact().getId());
			for (Call call : calls) {
				assertEquals(call.getContact().getId(), callForId.getContact().getId());
			}

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getFilterCalls pass");
	}

	@Test
	public void testFilterCallsTimestamp() {
		PrintAtDepth.print(1, "getFilterCalls start");

		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();

			Call callForDate = facadeCallPersistence.getAllCalls().get(0);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			String str = callForDate.getCallDate();
			Date date = null;
			try {
				date = dateFormat.parse(str);
			} catch (ParseException e) {
				PrintAtDepth.err(0, "testFilterCallsTimestamp didnt parse.");
			}
			long time = date.getTime();
			Timestamp timeStamp = new Timestamp(time);

			List<Call> calls = facadeCallPersistence.getFilterCalls("call_Date", timeStamp);
			for (Call call : calls) {
				assertEquals(call.getCallDate(), str);
			}

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getFilterCalls pass");
	}

}
