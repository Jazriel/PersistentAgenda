package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Call implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Contact contact;
	private String callDate ;
	private String subject, notes;

	public Call(int id, Contact contact, String callDate,
			String subject, String notes) {
		super();
		this.id = id;
		this.contact = contact;
		this.callDate = callDate;
		this.subject = subject;
		this.notes = notes;
	}
	
	public Call(Contact contact, String subject, String notes) {
		// TODO Auto-generated constructor stub
		this.contact = contact;
		this.callDate =  new Timestamp(System.currentTimeMillis()).toString();
		this.subject = subject;
		this.notes = notes;
	}

	public Call(int id, String subject, String notes) {
		this.id = id;
		this.subject = subject;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Llamada [id llamada = " + id + ", contacto = " + contact.basicInfo()
				+ ", fecha llamada = " + callDate + ", asunto = " + subject
				+ ", notas = " + notes + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
