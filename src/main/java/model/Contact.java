package model;

import java.io.Serializable;
import java.util.Iterator;

public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;

	private String  name ,  surname ,  title ,  address ,  city ,  province ,  postalCode ,  region ,
	country ,  companyName ,  workstation,  workPhone ,  workExtension ,  mobilePhone ,  faxNumber ,
	email, notes;
	
	private ContactType contactType;
	
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
	
	public Contact(int id, String name, String surname,
			String title, String address, String city, String province,
			String postalCode, String region, String country,
			String companyName, String workstation, String workPhone,
			String workExtension, String mobilePhone, String faxNumber,
			String email, String notes, ContactType contactType) {
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

	public String basicInfo(){
		return "Contacto [id = " + id + "nombre = " + name + ", apellidos = " + surname +"]";
	}

	@Override
	public String toString() {
		return "Contacto [id Contacto = " + id + ", nombre = " + name
				+ ", apellidos = " + surname + ", estimado = " + title
				+ ", direccion = " + address + ", ciudad = " + city + ", provincia = "
				+ province + ", codigo postal = " + postalCode + ", region =  " + region
				+ ", pais = " + country + ", nombre compania = " + companyName
				+ ", cargo = " + workstation + ", telefono trabajo = " + workPhone
				+ ", extension trabajo = " + workExtension + ", telefono movil = "
				+ mobilePhone + ", numero fax = " + faxNumber
				+ ", correo electronico = " + email + ", notas = "
				+ notes + ", tipo contacto = " + contactType + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWorkstation() {
		return workstation;
	}

	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getWorkExtension() {
		return workExtension;
	}

	public void setWorkExtension(String workExtension) {
		this.workExtension = workExtension;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

}
