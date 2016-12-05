package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Call;
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
			facadeCallPersistence.saveCall(new Call(id, attribs, null));
			Call call = facadeCallPersistence.getCallById(id);
			assertEquals(id, call.getId());
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "saveCall pass");
	}

	@Test
	public void testGetCallById() {
		PrintAtDepth.print(1, "getCallById start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
			List<String> attribs = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				attribs.add(null);
			}
			int id = getIncMaxCallId(facadeCallPersistence);
			facadeCallPersistence.saveCall(new Call(id, attribs, null));
			Call call = facadeCallPersistence.getCallById(id);
			assertTrue(call != null);
			// PrintAtDepth.print(3, "Call was not null");
			assertTrue(call.getId() == id);
			// PrintAtDepth.print(3, "Id was correct.");
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
			String name = "Pepitico";
			int id = getIncMaxCallId(facadeCallPersistence);
			facadeCallPersistence.saveCall(new Call(id, attribs, null));
			Call call = facadeCallPersistence.getCallById(id);
			assertTrue(call != null);
			PrintAtDepth.print(3, "First check on call was not null");
			call.setName(name);
			facadeCallPersistence.updateCall(call);
			call = null;
			call = facadeCallPersistence.getCallById(id);
			assertTrue(call != null);
			PrintAtDepth.print(3, "Second check on call was not null");
			assertTrue(call.getName().equals(name));
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
			facadeCallPersistence.saveCall(new Call(id, attribs, null));
			List<Call> callsAfter = facadeCallPersistence.getAllCalls();
			for (Call call : callsBefore) {
				assertTrue(callsAfter.remove(call));
			}
			assertEquals(callsAfter.get(0).getId(), id);

			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getAllCalls pass");
	}

	@Test
	public void testGetOrderCalls() {
		PrintAtDepth.print(1, "getOrderCalls start");
		// , "call_type_id"
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			String[] fields = { "id", "name", "surname", "title", "address", "city", "province", "postal_Code",
					"region", "country", "company_Name", "workstation", "work_Phone", "work_Extension", "mobile_Phone",
					"fax_Number", "email", "notes" };
			for (String fieldString : fields) {
				PrintAtDepth.print(3, fieldString + " start");
				IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
				List<Call> calls = facadeCallPersistence.getOrderCalls(fieldString);
				Field field = fieldString2field(fieldString);
				int i = 0;
				do {
					String fieldVal = fieldValue(field, calls.get(i));
					i++;
					String fieldValAux = fieldValue(field, calls.get(i));
					assertTrue(0 >= fieldVal.compareTo(fieldValAux));
				} while (i < calls.size());

				PrintAtDepth.print(3, fieldString + " pass");
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getOrderCalls pass");
	}

	private String fieldValue(Field field, Call call) {
		try {
			return (String) field.get(call);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Field fieldString2field(String field) {
		try {
			switch (field) {
			case "postal_Code":
				return Call.class.getField("getPostalCode");
			case "company_Name":
				return Call.class.getField("getCompanyName");
			case "work_Phone":
				return Call.class.getField("getWorkPhone");
			case "work_Extension":
				return Call.class.getField("getWorkExtension");
			case "mobile_Phone":
				return Call.class.getField("getMobilePhone");
			case "fax_Number":
				return Call.class.getField("getFaxNumber");
			case "call_type_id":
				return Call.class.getField("getCallTypeId");
			default:
				return Call.class.getField(field);
			}
		} catch (Exception e) {
			PrintAtDepth.err(0, "dbField2objectMethod FAILED");
			return null;
		}

	}

	@Test
	public void testFilterCalls() {
		PrintAtDepth.print(1, "getFilterCalls start");
		
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			String[] fields = { "name", "surname", "title", "address", "city", "province", "postal_Code", "region",
					"country", "company_Name", "workstation", "work_Phone", "work_Extension", "mobile_Phone",
					"fax_Number", "email", "notes" };
			for (String fieldString : fields) {
				PrintAtDepth.print(3, fieldString + " start");
				IFacadeCallPersistence facadeCallPersistence = persistence.createCallPersistence();
				Call callForField = facadeCallPersistence.getAllCalls().get(0);
				Field field = fieldString2field(fieldString);
				String filterString = fieldValue(field, callForField);
				List<Call> callsFiltered = facadeCallPersistence.getFilterCalls(fieldString, filterString);
				//  check all the list field == filterString
				for (Call call : callsFiltered) {
					assertEquals(filterString, fieldValue(field, call));
				}
				//  check all in getAllCalls with field == filterString -> in callsFiltered
				List<Call> allCalls = facadeCallPersistence.getAllCalls();
				for (Call call : allCalls) {
					if(filterString==fieldValue(field, call)){
						assertTrue(callsFiltered.remove(call));
					}
				}
				PrintAtDepth.print(3, field + " pass");
			}
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getFilterCalls pass");
	}

	private int getIncMaxCallId(IFacadeCallPersistence persistence) {
		if (persistence instanceof persistence.database.FacadeCallDataBase) {
			return getIncMaxCallIdDB();
		} else if (persistence instanceof persistence.bin.FacadeCallBinFile) {
			return getIncMaxCallIdBinFile(persistence);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private int getIncMaxCallIdBinFile(IFacadeCallPersistence persistence) {
		int ret;
		try {
			ret = persistence.getOrderCalls("id").get(0).getId();
		} catch (NullPointerException e) {
			return 1;
		}
		return ret;
	}

	private int getIncMaxCallIdDB() {
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		int call = 0;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/phonebook", "SA", "");
			stm = connection.createStatement();
			rs = stm.executeQuery("SELECT COALESCE(MAX(id), 0) FROM calls");
			rs.next();
			call = rs.getInt(1);
		} catch (SQLException e) {
			PrintAtDepth.err(0, "getMaxCallId Error: " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				PrintAtDepth.err(0, e.getMessage());
			}
		}
		return call + 1;
	}

}