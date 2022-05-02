package it.unibs.fp.codiceFiscale;

/**
 * 
 *<h1>Person class</h1>
 *<p>This class is use to collect the data of a person</p>
 *
 */

public class Person {
	
	//Attributes
	private String name,surname, gender;
	private HomeTown birthPlace;
	private String birthDate;
	private FiscalCode fc;
	
	//Getter and Setter
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public HomeTown getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(HomeTown birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public FiscalCode getFc() {
		return fc;
	}
	public void setFc(FiscalCode fc) {
		this.fc = fc;
	}
	
	public Person(String name, String surname, String gender, HomeTown birthPlace, Date birthDate, FiscalCode fc) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.fc = fc;
	}
	
	
}
