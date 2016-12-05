package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public class Contact implements Serializable {
	/**
	 * Auto generado por eclipse.
	 * @author Javier Martinez, Daniel Puente, Jaime Sagüillo, Jorge Zamora y Oscar Fernandez
	 */
	private static final long serialVersionUID = 1L;
/**
 * id de contacto.
 */
	private int id;
/**
 * Diferentes atributos de contacto.
 */
	private String name, surname, title, address, city, province, postalCode, region, country, companyName, workstation,
			workPhone, workExtension, mobilePhone, faxNumber, email, notes;
/**
 * Tipo de contacto.
 */
	private ContactType contactType;
/**
 * Contact(int id, Iterable<String> attribs, ContactType contactType). Contructor de clase.
 * @param id, id de contactos.
 * @param attribs, diferentes atributos de contacto
 * @param contactType, tipo de contacto.
 */
	public Contact(int id, Iterable<String> attribs, ContactType contactType) {
		super();
		this.id = id;
		Iterator<String> its = attribs.iterator();
		this.name = its.next();
		this.surname = its.next();
		this.title = its.next();
		this.address = its.next();
		this.city = its.next();
		this.province = its.next();
		this.postalCode = its.next();
		this.region = its.next();
		this.country = its.next();
		this.companyName = its.next();
		this.workstation = its.next();
		this.workPhone = its.next();
		this.workExtension = its.next();
		this.mobilePhone = its.next();
		this.faxNumber = its.next();
		this.email = its.next();
		this.notes = its.next();
		this.contactType = contactType;
	}
/**
 * Contact(). Constructor de clase.
 * @param id, atributo de contacto.
 * @param name, atributo de contacto.
 * @param surname, atributo de contacto.
 * @param title, atributo de contacto.
 * @param address, atributo de contacto.
 * @param city, atributo de contacto.
 * @param province, atributo de contacto.
 * @param postalCode, atributo de contacto. 
 * @param region, atributo de contacto.
 * @param country, atributo de contacto.
 * @param companyName, atributo de contacto.
 * @param workstation, atributo de contacto.
 * @param workPhone, atributo de contacto.
 * @param workExtension, atributo de contacto.
 * @param mobilePhone, atributo de contacto.
 * @param faxNumber, atributo de contacto.
 * @param email, atributo de contacto.
 * @param notes, atributo de contacto.
 * @param contactType, atributo de contacto.
 */
	public Contact(int id, String name, String surname, String title, String address, String city, String province,
			String postalCode, String region, String country, String companyName, String workstation, String workPhone,
			String workExtension, String mobilePhone, String faxNumber, String email, String notes,
			ContactType contactType) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.title = title;
		this.address = address;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.region = region;
		this.country = country;
		this.companyName = companyName;
		this.workstation = workstation;
		this.workPhone = workPhone;
		this.workExtension = workExtension;
		this.mobilePhone = mobilePhone;
		this.faxNumber = faxNumber;
		this.email = email;
		this.notes = notes;
		this.contactType = contactType;
	}
	/**
	 * Contact(Iterable<String> attribs, ContactType contactType). Contructor de clase.
	 * @param attribs, diferentes atributos de contacto
	 * @param contactType, tipo de contacto.
	 */
	public Contact(Iterable<String> attribs, ContactType contactType) {
		// TODO Auto-generated constructor stub
		Iterator<String> its = attribs.iterator();
		this.name = its.next();
		this.surname = its.next();
		this.title = its.next();
		this.address = its.next();
		this.city = its.next();
		this.province = its.next();
		this.postalCode = its.next();
		this.region = its.next();
		this.country = its.next();
		this.companyName = its.next();
		this.workstation = its.next();
		this.workPhone = its.next();
		this.workExtension = its.next();
		this.mobilePhone = its.next();
		this.faxNumber = its.next();
		this.email = its.next();
		this.notes = its.next();
		this.contactType = contactType;

	}
/**
 * basicInfo(). Metodo que muestra la información basica de contacto.
 * @return "Contacto [id = " + id + "nombre = " + name + ", apellidos = " + surname + "]".
 */
	public String basicInfo() {
		return "Contacto [id = " + id + "nombre = " + name + ", apellidos = " + surname + "]";
	}
	/**
	 * toString. Metodo que imprime los datos del contacto.
	 */
	@Override
	public String toString() {
		return "Contacto [id Contacto = " + id + ", nombre = " + name + ", apellidos = " + surname + ", estimado = "
				+ title + ", direccion = " + address + ", ciudad = " + city + ", provincia = " + province
				+ ", codigo postal = " + postalCode + ", region =  " + region + ", pais = " + country
				+ ", nombre compania = " + companyName + ", cargo = " + workstation + ", telefono trabajo = "
				+ workPhone + ", extension trabajo = " + workExtension + ", telefono movil = " + mobilePhone
				+ ", numero fax = " + faxNumber + ", correo electronico = " + email + ", notas = " + notes
				+ ", tipo contacto = " + contactType + "]";
	}
/**
 * getId().
 * @return id
 */
	public int getId() {
		return id;
	}
/**
 * setId(int id)
 * @param id, id del contacto
 */
	public void setId(int id) {
		this.id = id;
	}
/**
 * getName().
 * @return name
 */
	public String getName() {
		return name;
	}
/**
 * setName(String name).
 * @param name, nombre contacto.
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * getSurname().
 * @return surname
 */
	public String getSurname() {
		return surname;
	}
/**
 * setSurname(String surname)
 * @param surname, apellidos de contacto.
 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
/**
 * getTitle().
 * @return title
 */
	public String getTitle() {
		return title;
	}
/**
 * setTitle(String title)
 * @param title, titulo contacto.
 */
	public void setTitle(String title) {
		this.title = title;
	}
/**
 * getAddress().
 * @return address
 */
	public String getAddress() {
		return address;
	}
/**
 * setAddress(String address)
 * @param address, direccion contacto.
 */
	public void setAddress(String address) {
		this.address = address;
	}
/**
 * getCity().
 * @return city
 */
	public String getCity() {
		return city;
	}
/**
 * setCity(String city).
 * @param city, ciudad del contacto.
 */
	public void setCity(String city) {
		this.city = city;
	}
/**
 * getProvince().
 * @return province
 */
	public String getProvince() {
		return province;
	}
/**
 * setProvince(String province)
 * @param province, provincia
 */
	public void setProvince(String province) {
		this.province = province;
	}
/**
 * getPostalCode().
 * @return postalCode
 */
	public String getPostalCode() {
		return postalCode;
	}
/**
 * setPostalCode(String postalCode).
 * @param postalCode, código postal.
 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
/**
 * getRegion()
 * @return region
 */
	public String getRegion() {
		return region;
	}
/**
 * setRegion(String region)
 * @param region, region
 */
	public void setRegion(String region) {
		this.region = region;
	}
/**
 * getCountry()
 * @return country
 */
	public String getCountry() {
		return country;
	}
/**
 * setCountry(String country)
 * @param country, país.
 */
	public void setCountry(String country) {
		this.country = country;
	}
/**
 * getCompanyName()
 * @return companyName
 */
	public String getCompanyName() {
		return companyName;
	}
/**
 * setCompanyName(String companyName)
 * @param companyName, compañia.
 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
/**
 * getWorkstation()
 * @return workstation
 */
	public String getWorkstation() {
		return workstation;
	}
/**
 * setWorkstation(String workstation)
 * @param workstation, puesto de trabajo.
 */
	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}
/**
 * getWorkPhone()
 * @return workPhone
 */
	public String getWorkPhone() {
		return workPhone;
	}
/**
 * setWorkPhone(String workPhone)
 * @param workPhone, teléfono trabajo.
 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
/**
 * getWorkExtension()
 * @return workExtension
 */
	public String getWorkExtension() {
		return workExtension;
	}
/**
 * setWorkExtension(String workExtension)
 * @param workExtension, estensión. 
 */
	public void setWorkExtension(String workExtension) {
		this.workExtension = workExtension;
	}
/**
 * getMobilePhone()
 * @return mobilePhone
 */
	public String getMobilePhone() {
		return mobilePhone;
	}
/**
 * setMobilePhone(String mobilePhone)
 * @param mobilePhone, teléfono.
 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
/**
 * getFaxNumber().
 * @return faxNumber
 */
	public String getFaxNumber() {
		return faxNumber;
	}
/**
 * setFaxNumber(String faxNumber).
 * @param faxNumber, fax
 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
/**
 * getEmail()
 * @return email
 */
	public String getEmail() {
		return email;
	}
/**
 * setEmail(String email)
 * @param email, email
 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * getNotes().
	 * @return notes
	 */

	public String getNotes() {
		return notes;
	}
/**
 * setNotes(String notes)
 * @param notes, notas.
 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
/**
 * getContactType().
 * @return contactType
 */
	public ContactType getContactType() {
		return contactType;
	}
/**
 * setContactType(ContactType contactType)
 * @param contactType, tipo de contacto.
 */
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	/**
	 * getOrderByName(). Metodo comparador para poder ordenar por nombre.
	 * @return new Comparator<Call>()
	 */
	public static Comparator<Contact> getOrderByName() {
		return new Comparator<Contact>() {
			/**
			 * compare(Call call1, Call call2)
			 * @return int, 0 o contact1.getName().compareTo(contact2.getName()) .
			 */
			@Override
			public int compare(Contact contact1, Contact contact2) {
				if(contact1.getName() == null || contact2.getName() == null)
					return 0;
				return contact1.getName().compareTo(contact2.getName());
			}

		};

	}
	/**
	 * getOrderBySurname(). Metodo comparador para poder ordenar por nombre.
	 * @return new Comparator<Call>()
	 */
	public static Comparator<Contact> getOrderBySurname() {
		return new Comparator<Contact>() {
			/**
			 * compare(Call call1, Call call2)
			 * @return int, 0 o contact1.getSurname().compareTo(contact2.getSurname()).
			 */
			@Override
			public int compare(Contact contact1, Contact contact2) {
				if(contact1.getSurname() == null || contact2.getSurname() == null)
					return 0;
				return contact1.getSurname().compareTo(contact2.getSurname());
			}

		};

	}

}
