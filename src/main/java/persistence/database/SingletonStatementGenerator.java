package persistence.database;

/**
 * Clase SingletonConnection
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class SingletonStatementGenerator {
	/**
	 * instancia de la sentencia.
	 */
	private static SingletonStatementGenerator statementGeneratorInstance;

	/**
	 * SingletonStatementGenerator(). Constructor de clase
	 */
	private SingletonStatementGenerator() {
	}

	public static SingletonStatementGenerator getInstance() {
		if (statementGeneratorInstance == null)
			statementGeneratorInstance = new SingletonStatementGenerator();
		return statementGeneratorInstance;
	}

	/**
	 * insertContactStatement(). Metodo para insertar un contacto
	 * 
	 * @return consulta.
	 */
	// Contact Statements
	public String insertContactStatement() {
		return "INSERT INTO CONTACTS"
				+ "( \"ID\", \"NAME\", \"SURNAME\", \"TITLE\", \"ADDRESS\", \"CITY\", \"PROVINCE\", "
				+ "\"POSTAL_CODE\", \"REGION\", \"COUNTRY\", \"COMPANY_NAME\", \"WORKSTATION\", "
				+ "\"WORK_PHONE\", \"WORK_EXTENSION\", \"MOBILE_PHONE\", \"FAX_NUMBER\", \"EMAIL\", "
				+ "\"CONTACT_TYPE_ID\", \"NOTES\" )"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	/**
	 * updateContactStatement(). Metodo que actualiza un contacto
	 * 
	 * @return consulta.
	 */
	public String updateContactStatement() {
		return "UPDATE CONTACTS SET (name,  surname,  title,  address,  city,  "
				+ "province,  postal_Code,  region, country ,  company_Name,  "
				+ "workstation,  work_Phone,  work_Extension,  mobile_Phone,  "
				+ "fax_Number, email, contact_type_id, notes) = (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  " + "WHERE id=?";

	}

	/**
	 * getContactByIdStatement(). Metodo para coger un contacto.
	 * 
	 * @return consulta.
	 */
	public String getContactByIdStatement() {
		return "SELECT CONTACTS.ID, NAME ,  SURNAME ,  TITLE ,  ADDRESS ,  CITY ,  "
				+ "PROVINCE ,  POSTAL_CODE ,  REGION , COUNTRY ,  COMPANY_NAME ,  "
				+ "WORKSTATION,  WORK_PHONE ,  WORK_EXTENSION ,  MOBILE_PHONE ,  "
				+ "FAX_NUMBER , EMAIL, NOTES, CONTACT_TYPE_ID, CONTACT_TYPE "
				+ "FROM CONTACTS LEFT JOIN CONTACTSTYPES ON CONTACTSTYPES.ID = CONTACTS.CONTACT_TYPE_ID "
				+ "WHERE CONTACTS.ID=?;";
	}

	/**
	 * getAllContacts(). Metodo para coger los contactos.
	 * 
	 * @return consulta.
	 */
	public String getAllContacts() {
		return "SELECT * FROM CONTACTS";
	}

	/**
	 * insertCallStatement(). Metodo que inserta una llamada
	 * 
	 * @return consulta.
	 */
	// Call Statements
	public String insertCallStatement() {
		return "INSERT INTO CALLS" + "( \"ID\", \"CONTACT_ID\", \"CALL_DATE\", \"SUBJECT\", \"NOTES\" )"
				+ " VALUES ( ?, ?, ?, ?, ?)";
	}

	/**
	 * updateCallStatement(). Metodo que actualiza una llamada.
	 * 
	 * @return consulta.
	 */
	public String updateCallStatement() {
		// return "UPDATE CALLS SET (call_date, subject, notes) = ( ?, ?, ?)";
		return "UPDATE CALLS SET (subject, notes) = (?, ?) WHERE ID = ?";
	}

	/**
	 * getCallsByContactIdStatement(). Metodo que busca una llamada concreta
	 * 
	 * @return consulta.
	 */
	public String getCallsByContactIdStatement() {
		return "SELECT * FROM CALLS WHERE CONTACT_ID = ? ";
	}

	/**
	 * getAllCallsStatement(). Metodo que coge todas las llamadas.
	 * 
	 * @return consulta.
	 */
	public String getAllCallsStatement() {
		return "SELECT * FROM CALLS";
	}

	/**
	 * insertContactTypeStatement(). Metodo que inserta un tipo de contacto
	 * 
	 * @return consulta.
	 */
	// Contact types statements
	public String insertContactTypeStatement() {
		return "INSERT INTO CONTACTSTYPES(\"CONTACT_TYPE\") VALUES (?)";
	}

	/**
	 * updateContactTypeStatement(). Metodo que actualiza un tipo de contacto.
	 * 
	 * @return consulta.
	 */
	public String updateContactTypeStatement() {
		return "UPDATE CONTACTSTYPES SET (CONTACT_TYPE) = ? WHERE ID = ?";
	}

	/**
	 * getAllContactsTypes(). Metodo que coge todos los tipos de contacto
	 * 
	 * @return consulta.
	 */
	public String getAllContactsTypes() {
		return "SELECT * FROM CONTACTSTYPES";
	}

	/**
	 * getContactTypeByIdStatement(). Metodo que coge un tipo de contacto por el
	 * id.
	 * 
	 * @return consulta.
	 */
	public String getContactTypeByIdStatement() {
		return "SELECT * FROM CONTACTSTYPES WHERE ID = ?";
	}

	/**
	 * getOrderCallsStatement(String field). Metodo que ordena llamadas por un
	 * campo
	 * 
	 * @param field,
	 *            campo por el que ordenar
	 * @return consulta.
	 */
	public String getOrderCallsStatement(String field) {
		return "SELECT * FROM CALLS ORDER BY " + field;
	}

	/**
	 * getFilteredCallsStatement(String field). Metodo que filtra llamadas por
	 * id
	 * 
	 * @param field,
	 *            campo por el que ordenar
	 * @return consulta.
	 */
	public String getFilteredCallsStatement(String field) {
		return "SELECT * FROM CALLS WHERE " + field + "=?";
	}

	/**
	 * getOrderContactsStatement(String field). Metodo que ordena contactor por
	 * un campo.
	 * 
	 * @param field,
	 *            campo por el que ordenar.
	 * @return consulta.
	 */
	public String getOrderContactsStatement(String field) {
		return "SELECT * FROM CONTACTS ORDER BY " + field;
	}

	/**
	 * getFilteredContactsStatement(String field). Metodo que filtra contactor
	 * por campo
	 * 
	 * @param field,
	 *            campo por el que se filtrara
	 * @return consulta.
	 */
	public String getFilteredContactsStatement(String field) {
		return "SELECT * FROM CONTACTS WHERE " + field + "= ?";
	}

}
