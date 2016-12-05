package persistence.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Call;
import model.Contact;
import model.ContactType;

/**
 * Clase StatementManager. Clase que se encarga de manejo de los statements.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class StatementManager {

	/**
	 * Sentencia preparada a utilizar
	 */
	private PreparedStatement preparedStatement;

	/**
	 * Conexión a utilizar
	 */
	private Connection conn;

	/**
	 * Método StatementManager. Método que llama a la conexión a la base de datos.
	 * 
	 * @throws SQLException
	 *             Excepción de sql
	 */
	public StatementManager() throws SQLException {
		// Open connection
		this.conn = SingletonConnection.getInstance().getConnection();
	}

	/**
	 * Método executeQuery. Método que se encarga de ejecutar una consulta.
	 * 
	 * @return executeQuery Resultado del executeQuery();
	 * @throws SQLException
	 *             Excepción de sql
	 */
	public ResultSet executeQuery() throws SQLException {
		return preparedStatement.executeQuery();
	}

	/**
	 * Método executeUpdate. Método que se encarga de actualizar.
	 * 
	 * @throws SQLException
	 *             Excepción de sql.
	 */
	public void executeUpdate() throws SQLException {
		preparedStatement.executeUpdate();
	}

	/**
	 * Método close. Método que se encarga de cerrar una preparedStetement.
	 */
	public void close() {
		try {
			if (preparedStatement != null && !preparedStatement.isClosed())
				preparedStatement.close();
			preparedStatement = null;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Método getFilleContacByIdStetements. Método que se encarga de generar el
	 * preparedStetement para buscar por id un contacto.
	 * 
	 * @param id
	 *            Id por el que filtrar.
	 * @throws SQLException
	 *             Escepción sql.
	 */
	// Contacts
	public void getFilledContactByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getContactByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getFilledUpdateContacStetements. Método que se encarga de realizar
	 * el preparedStatement para realizar la actualización de un contacto.
	 * 
	 * @param contact
	 *            Contacto a actualizar
	 * @throws SQLException
	 *             Excepción sql
	 */
	public void getFilledUpdateContactStatement(Contact contact) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateContactStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setString(1, contact.getName());
		preparedStatement.setString(2, contact.getSurname());
		preparedStatement.setString(3, contact.getTitle());
		preparedStatement.setString(4, contact.getAddress());
		preparedStatement.setString(5, contact.getCity());
		preparedStatement.setString(6, contact.getProvince());
		preparedStatement.setString(7, contact.getPostalCode());
		preparedStatement.setString(8, contact.getRegion());
		preparedStatement.setString(9, contact.getCountry());
		preparedStatement.setString(10, contact.getCompanyName());
		preparedStatement.setString(11, contact.getWorkstation());
		preparedStatement.setString(12, contact.getWorkPhone());
		preparedStatement.setString(13, contact.getWorkExtension());
		preparedStatement.setString(14, contact.getMobilePhone());
		preparedStatement.setString(15, contact.getFaxNumber());
		preparedStatement.setString(16, contact.getEmail());
		int i = -1;
		if (contact != null) {
			ContactType ct = contact.getContactType();
			if (ct != null) {
				i = ct.getId();
			}
		}
		preparedStatement.setInt(17, i);
		preparedStatement.setString(18, contact.getNotes());
		preparedStatement.setInt(19, contact.getId());
		this.preparedStatement = preparedStatement;

	}

	/**
	 * Método getFilledSaveContactSatement. Método que se encarga de generar el
	 * preparedStatement para añadir un nuevo contacto.
	 * 
	 * @param contact
	 *            Contacto para añadir.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getFilledSaveContactStatement(Contact contact) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertContactStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, contact.getId());
		preparedStatement.setString(2, contact.getName());
		preparedStatement.setString(3, contact.getSurname());
		preparedStatement.setString(4, contact.getTitle());
		preparedStatement.setString(5, contact.getAddress());
		preparedStatement.setString(6, contact.getCity());
		preparedStatement.setString(7, contact.getProvince());
		preparedStatement.setString(8, contact.getPostalCode());
		preparedStatement.setString(9, contact.getRegion());
		preparedStatement.setString(10, contact.getCountry());
		preparedStatement.setString(11, contact.getCompanyName());
		preparedStatement.setString(12, contact.getWorkstation());
		preparedStatement.setString(13, contact.getWorkPhone());
		preparedStatement.setString(14, contact.getWorkExtension());
		preparedStatement.setString(15, contact.getMobilePhone());
		preparedStatement.setString(16, contact.getFaxNumber());
		preparedStatement.setString(17, contact.getEmail());
		int i = -1;
		if (contact != null) {
			ContactType ct = contact.getContactType();
			if (ct != null) {
				i = ct.getId();
			}
		}
		preparedStatement.setInt(18, i);
		preparedStatement.setString(19, contact.getNotes());
		this.preparedStatement = preparedStatement;

	}

	/**
	 * Método getFilledCallByIdStetement. Método que se encarga de generar el
	 * preparedStatement para obtener una llamada por Id.
	 * 
	 * @param id
	 *            Id por el que buscar la llamada.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	// Calls
	public void getFilledCallByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getCallsByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getFilledUpdateCallStetement. Método que se encarga de generar el
	 * preparedStatement para actualizar una llamada.
	 * 
	 * @param call
	 *            LLamada a actualizar.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getFilledUpdateCallStatement(Call call) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateCallStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		preparedStatement.setString(1, call.getSubject());
		preparedStatement.setString(2, call.getNotes());
		preparedStatement.setInt(3, call.getId());
		this.preparedStatement = preparedStatement;

	}

	/**
	 * Método getFilledSaveCallStatement. Método que se encarga de generar la
	 * preparedStatement para añadir una llamada.
	 * 
	 * @param call
	 *            LLamada a introducir.
	 * @throws SQLException
	 *             Escepción sql.
	 */
	public void getFilledSaveCallStatement(Call call) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertCallStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, call.getId());
		preparedStatement.setInt(2, call.getContact().getId());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(call.getCallDate().substring(0, 10));
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		long time = date.getTime();
		Timestamp timeStamp = new Timestamp(time);
		preparedStatement.setTimestamp(3, timeStamp);
		preparedStatement.setString(4, call.getSubject());
		preparedStatement.setString(5, call.getNotes());
		this.preparedStatement = preparedStatement;

	}

	/**
	 * Método getFilledContactTypeByIdStatement. Método que se encarga de
	 * generar la preparedStatement para buscar una llamada por id.
	 * 
	 * @param id
	 *            Id de la llamada que queremos encontrar.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	// Contact Types
	public void getFilledContactTypeByIdStatement(int id) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getContactTypeByIdStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getFilledSaveContacTypeStatement. Método que se encarga de generar
	 * la preparedStatement para la inserción de un nuevo topo de contacto.
	 * 
	 * @param contactType
	 *            Tipo de contacto a añadir.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getFilledSaveContactTypeStatement(ContactType contactType) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().insertContactTypeStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setString(1, contactType.getContactTypeName());
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getFilledUpdateContactTypeStatement. Método que se encarga de
	 * generar la preparedStatement para la actualización de un contacto
	 * 
	 * @param contactType
	 *            Tipo de contacto que queremos actualizar.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getFilledUpdateContactTypeStatement(ContactType contactType) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().updateContactTypeStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setString(1, contactType.getContactTypeName());
		preparedStatement.setInt(2, contactType.getId());
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getOrderCallsSatement. Método que se encarga de generar la
	 * preparedStatement para obtener las llamadas ordenadas por campo.
	 * 
	 * @param field
	 *            Campo por el que ordenar.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getOrderCallsStatement(String field) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getOrderCallsStatement(field);
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getFilterCallsStatementGeneral. Método que se encarga de generar
	 * la preparedStatement para el filtrado de llamadas.
	 * 
	 * @param field
	 *            Campo que filtrar
	 * @return prepareStatement prepareStatement generada.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	private PreparedStatement getFilterCallsStamentGeneral(String field) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getFilteredCallsStatement(field);
		// Create PreparedStatement
		return conn.prepareStatement(textStatement);
	}

	/**
	 * Método getFilterCallsStatement. Método que se encarga de generar la
	 * preparedStatement para filtrar llamadas.
	 * 
	 * @param field
	 *            Campo por el que filtrar.
	 * @param id
	 *            Dato para filtrar.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getFilterCallsStatement(String field, int id) throws SQLException {
		PreparedStatement preparedStatement = getFilterCallsStamentGeneral(field);
		// Prepare statement
		preparedStatement.setInt(1, id);
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getFilterCallsStatement. Método que se encarga de generar la
	 * preparedStatement para filtrar llamadas por campo y tiempo.
	 * 
	 * @param field
	 *            Campo a filtrar.
	 * @param timeStamp
	 *            Tiempo a filtrar.
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getFilterCallsStatement(String field, Timestamp timeStamp) throws SQLException {
		PreparedStatement preparedStatement = getFilterCallsStamentGeneral(field);
		// Prepare statement
		preparedStatement.setTimestamp(1, timeStamp);
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getAllCallsStatement. Método que se encarga de generar la
	 * preparedStatement para obtener todas las llamadas.
	 * 
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getAllCallsStatement() throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getAllCallsStatement();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getAllContactsStatement. Método que se encarga de generar la
	 * preparedStatement para obtener todos los contactos.
	 * 
	 * @throws SQLException
	 *             Excepción sql.
	 */
	public void getAllContactsStatement() throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getAllContacts();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getAllContactsTypesStatement. Método que se encarga de generar la
	 * preparedStatement para obtener todos los tipos de contactos.
	 * 
	 * @throws SQLException Excepción sql.
	 */
	public void getAllContactTypesStatement() throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getAllContactsTypes();
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getOrderContactsStatement. Método que se encarga de generar la preparedStatement para la ordenacion de los contactos.
	 * @param field Campo por el que ordenar.
	 * @throws SQLException Excepción sql.
	 */
	public void getOrderContactsStatement(String field) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getOrderContactsStatement(field);
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		this.preparedStatement = preparedStatement;
	}

	/**
	 * Método getFilterContactsStatement. Método que se encarga de filtrar los contactos por campo.
	 * @param field Campo por el que filtrar.
	 * @param filteredField Valor por el  que filtrar.
	 * @throws SQLException Excepción sql.
	 */
	public void getFilterContactsStatement(String field, String filteredField) throws SQLException {
		// Get text statement
		String textStatement = SingletonStatementGenerator.getInstance().getFilteredContactsStatement(field);
		// Create PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(textStatement);
		// Prepare statement
		preparedStatement.setString(1, filteredField);
		this.preparedStatement = preparedStatement;
	}

}
