package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
/**
 * Clase Call. Son las llamadas de los contactos.
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez */
public class Call implements Serializable {
	/**
	 * Auto generado por eclipse.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id de las llamada.
	 */
	private int id;
	/**
	 * El contacto que ha hecho esa llamada.
	 */
	private Contact contact;
	/**
	 * Fecha de la llamada.
	 */
	private String callDate;
	/**
	 * Tema de la llamada y notas de la misma.
	 */
	private String subject, notes;
	
/**
 * Call. Contructor de clase.
 * 
 * @param id, id de la llamada.
 * @param contact, contacto que ha efectuado la llamada.
 * @param callDate, fecha de la llamada.
 * @param subject, tema de la llamada.
 * @param notes, notas de la llamada.
 */
	public Call(int id, Contact contact, String callDate, String subject, String notes) {
		super();
		this.id = id;
		this.contact = contact;
		this.callDate = callDate;
		this.subject = subject;
		this.notes = notes;
	}
/**
 * Call. Constructor de clase.
 * @param contact, contacto que ha hecho la llamada.
 * @param subject, tema de la llamada.
 * @param notes, notas de la llamada.
 */
	public Call(Contact contact, String subject, String notes) {
		this.contact = contact;
		this.callDate = new Timestamp(System.currentTimeMillis()).toString();
		this.subject = subject;
		this.notes = notes;
	}
/**
 * toString. Metodo que imprime los datos de la llamada.
 */
	@Override
	public String toString() {
		return "Llamada [id llamada = " + id + ", contacto = " + contact.basicInfo() + ", fecha llamada = " + callDate
				+ ", asunto = " + subject + ", notas = " + notes + "]";
	}
/**
 * getId().
 * @return id
 */
	public int getId() {
		return id;
	}
/**
 * setId(int id).
 * @param id, id de llamada.
 */
	public void setId(int id) {
		this.id = id;
	}
/**
 * getContact().
 * @return contact.
 */
	public Contact getContact() {
		return contact;
	}
/**
 * setContact(Contact contact).
 * @param contact, contacto.
 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
/**
 * getCallDate().
 * @return callDate.
 */
	public String getCallDate() {
		return callDate;
	}
/**
 * setCallDate(String callDate).
 * @param callDate, fecha de la llamada.
 */
	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
/**
 * getSubject().
 * @return subject
 */
	public String getSubject() {
		return subject;
	}
/**
 * setSubject(String subject).
 * @param subject, tema de la llamada.
 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
/**
 * getNotes().
 * @return notes.
 */
	public String getNotes() {
		return notes;
	}
/**
 * setNotes(String notes).
 * @param notes, notas de la llamada
 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
/**
 * getOrderById(). Metodo comparador para poder ordenar por id.
 * @return comparador.
 */
	public static Comparator<Call> getOrderById() {
		return new Comparator<Call>() {
			/**
			 * compare(Call call1, Call call2)
			 * @return int, 0 o 1 .
			 */
			@Override
			public int compare(Call call1, Call call2) {
				if (call1.getId() < call2.getId()) {
					return -1;
				} else if (call1.getId() > call2.getId()) {

					return 1;
				} else {
					return 0;
				}
			}
		};
	}
/**
 * getOrderByDate()
 * @return call1.getCallDate().compareTo(call2.getCallDate()).
 */
	public static Comparator<Call> getOrderByDate() {
		return new Comparator<Call>() {

			@Override
			public int compare(Call call1, Call call2) {
				return call1.getCallDate().compareTo(call2.getCallDate());
			}
		};
	}

}
