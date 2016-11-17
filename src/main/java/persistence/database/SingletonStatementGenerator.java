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
		return "UPDATE CONTACTS SET (name,  surname,  title,  address,  city,  province,  postal_Code,  region, country ,  company_Name,  workstation,  work_Phone,  work_Extension,  mobile_Phone,  fax_Number, email, notes) = (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  WHERE id=?";

	}

	public String insertContactStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
