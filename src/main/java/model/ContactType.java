package model;

import java.io.Serializable;
/**
 *Clase ContactType, para los tipos de contacto.
 */
public class ContactType implements Serializable{
	/**
	 * Auto generado por eclipse.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * El id del tipo de contacto.
	 */
	private int id;
	/**
	 * En nombre del tipo de contacto.
	 */
	private String contactTypeName;
	
	/**
	 * ContactType(int id, String name).Contructor de clase.
	 * @param id, id del tipo de contacto.
	 * @param name, nombre del tipo de contacto.
	 */
	public ContactType(int id, String name) {
		super();
		this.id = id;
		contactTypeName = name;
	}

/**
 * ContactType(String name). Constructor de clase.
 * @param name, nombre del tipo de contacto.
 */
	public ContactType(String name) {
		// TODO Auto-generated constructor stub
		this.contactTypeName = name;
	}
/**
 * getId().
 * @return id
 */
	public int getId(){
		return id;
	}
	/**
	 * getContactTypeName().
	 * @return contactTypeName
	 */
	public String getContactTypeName() {
		return contactTypeName;
	}
	/**
	 * toString. Metodo que imprime los datos del tipo de contacto.
	 */
	@Override
	public String toString() {
		return "Tipo Contacto [id Tipo Contacto = " + id
				+ ", Tipo Contacto = " + contactTypeName + "]";
	}

}
