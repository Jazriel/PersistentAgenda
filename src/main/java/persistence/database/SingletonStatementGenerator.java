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

	public String getContactByIdStatement() {
		return "SELECT CONTACTS.ID, NAME ,  SURNAME ,  TITLE ,  ADDRESS ,  CITY ,  PROVINCE ,  POSTAL_CODE ,  REGION , COUNTRY ,  COMPANY_NAME ,  WORKSTATION,  WORK_PHONE ,  WORK_EXTENSION ,  MOBILE_PHONE ,  FAX_NUMBER , EMAIL, NOTES, CONTACTSTYPES.ID, CONTACT_TYPE FROM CONTACTS JOIN CONTACTSTYPES ON CONTACTSTYPES.ID = CONTACTS.ID WHERE CONTACTS.ID=?;";
	}

	public String updateContactStatement() {
		return "UPDATE NAME=?,  SURNAME=? ,  TITLE=?,  ADDRESS=? ,  CITY=? ,  PROVINCE=? ,  POSTAL_CODE=? ,  REGION=? , COUNTRY=? ,  COMPANY_NAME=? ,  WORKSTATION=?,  WORK_PHONE=? ,  WORK_EXTENSION=? ,  MOBILE_PHONE=? ,  FAX_NUMBER=? , EMAIL=?, NOTES=?, CONTACT_TYPE_ID=? FROM CONTACTS WHERE ID=?;";
	}

	public String insertContactStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
