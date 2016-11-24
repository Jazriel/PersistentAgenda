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

	//
	public String getLastIdStatement(String table) {
		return "SELECT * FROM " + table + " order by id desc";
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
				+ "fax_Number, email, notes) = (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  " + "WHERE id=?";

	}

	public String getContactByIdStatement() {
		return "SELECT CONTACTS.ID, NAME ,  SURNAME ,  TITLE ,  ADDRESS ,  CITY ,  "
				+ "PROVINCE ,  POSTAL_CODE ,  REGION , COUNTRY ,  COMPANY_NAME ,  "
				+ "WORKSTATION,  WORK_PHONE ,  WORK_EXTENSION ,  MOBILE_PHONE ,  "
				+ "FAX_NUMBER , EMAIL, NOTES, CONTACTSTYPES.ID, CONTACT_TYPE "
				+ "FROM CONTACTS JOIN CONTACTSTYPES ON CONTACTSTYPES.ID = CONTACTS.ID " + "WHERE CONTACTS.ID=?;";
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
		return "UPDATE CALLS SET (call_date, subject, notes) = ( ?, ?, ?)";
	}

	public String getCallByIdStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAllCallsStatement() {
		return "SELECT * FROM CALLS";
	}

	// Contact types statements
	public String insertContactTypeStatement() {
		return "INSERT INTO CONTACTSTYPES( \"ID\", \"CONTACT_TYPE\" ) VALUES ( ?, ?)";
	}

	public String updateContactTypeStatement() {
		return "UPDATE CONTACTSTYPES SET (CONTACT_TYPE) = ? WHERE ID = ?";
	}

	public String getAllContactsTypes() {
		return "SELECT * FROM CONTACTSTYPES";
	}

	public String getContactTypeByIdStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
