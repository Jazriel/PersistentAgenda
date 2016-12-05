package persistence.database;

public class SingletonStatementGenerator {

	private static SingletonStatementGenerator statementGeneratorInstance;

	private SingletonStatementGenerator() {
	}

	public static SingletonStatementGenerator getInstance() {
		if (statementGeneratorInstance == null)
			statementGeneratorInstance = new SingletonStatementGenerator();
		return statementGeneratorInstance;
	}


	// Contact Statements
	public String insertContactStatement() {
		return "INSERT INTO CONTACTS"
				+ "( \"ID\", \"NAME\", \"SURNAME\", \"TITLE\", \"ADDRESS\", \"CITY\", \"PROVINCE\", "
				+ "\"POSTAL_CODE\", \"REGION\", \"COUNTRY\", \"COMPANY_NAME\", \"WORKSTATION\", "
				+ "\"WORK_PHONE\", \"WORK_EXTENSION\", \"MOBILE_PHONE\", \"FAX_NUMBER\", \"EMAIL\", "
				+ "\"CONTACT_TYPE_ID\", \"NOTES\" )"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	public String updateContactStatement() {
		return "UPDATE CONTACTS SET (name,  surname,  title,  address,  city,  "
				+ "province,  postal_Code,  region, country ,  company_Name,  "
				+ "workstation,  work_Phone,  work_Extension,  mobile_Phone,  "
				+ "fax_Number, email, contact_type_id, notes) = (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  " + "WHERE id=?";

	}

	public String getContactByIdStatement() {
		return "SELECT CONTACTS.ID, NAME ,  SURNAME ,  TITLE ,  ADDRESS ,  CITY ,  "
				+ "PROVINCE ,  POSTAL_CODE ,  REGION , COUNTRY ,  COMPANY_NAME ,  "
				+ "WORKSTATION,  WORK_PHONE ,  WORK_EXTENSION ,  MOBILE_PHONE ,  "
				+ "FAX_NUMBER , EMAIL, NOTES, CONTACT_TYPE_ID, CONTACT_TYPE "
				+ "FROM CONTACTS LEFT JOIN CONTACTSTYPES ON CONTACTSTYPES.ID = CONTACTS.CONTACT_TYPE_ID "
				+ "WHERE CONTACTS.ID=?;";
	}

	public String getAllContacts() {
		return "SELECT * FROM CONTACTS";
	}

	// Call Statements
	public String insertCallStatement() {
		return "INSERT INTO CALLS" + "( \"ID\", \"CONTACT_ID\", \"CALL_DATE\", \"SUBJECT\", \"NOTES\" )"
				+ " VALUES ( ?, ?, ?, ?, ?)";
	}

	public String updateCallStatement() {
		// return "UPDATE CALLS SET (call_date, subject, notes) = ( ?, ?, ?)";
		return "UPDATE CALLS SET (subject, notes) = (?, ?) WHERE ID = ?";
	}

	public String getCallsByContactIdStatement() {
		return "SELECT * FROM CALLS WHERE CONTACT_ID = ? ";
	}

	public String getAllCallsStatement() {
		return "SELECT * FROM CALLS";
	}

	// Contact types statements
	public String insertContactTypeStatement() {
		return "INSERT INTO CONTACTSTYPES(\"CONTACT_TYPE\") VALUES (?)";
	}

	public String updateContactTypeStatement() {
		return "UPDATE CONTACTSTYPES SET (CONTACT_TYPE) = ? WHERE ID = ?";
	}

	public String getAllContactsTypes() {
		return "SELECT * FROM CONTACTSTYPES";
	}

	public String getContactTypeByIdStatement() {
		return "SELECT * FROM CONTACTSTYPES WHERE ID = ?";
	}

	public String getOrderCallsStatement(String field) {
		return "SELECT * FROM CALLS ORDER BY " + field;
	}

	public String getFilteredCallsStatement(String field) {
		return "SELECT * FROM CALLS WHERE " + field + "=?";
	}

	public String getOrderContactsStatement(String field) {
		return "SELECT * FROM CONTACTS ORDER BY " + field;
	}

	public String getFilteredContactsStatement(String field) {
		return "SELECT * FROM CONTACTS WHERE " + field + "= ?";
	}

}
