package model;

import java.io.Serializable;

public class ContactType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String contactTypeName;
	
	
	public ContactType(int id, String name) {
		super();
		this.id = id;
		contactTypeName = name;
	}


	@Override
	public String toString() {
		return "Tipo Contacto [id Tipo Contacto = " + id
				+ ", Tipo Contacto = " + contactTypeName + "]";
	}

}
