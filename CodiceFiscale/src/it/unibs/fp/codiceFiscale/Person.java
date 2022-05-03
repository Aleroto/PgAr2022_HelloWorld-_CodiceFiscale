package it.unibs.fp.codiceFiscale;

/**
 * 
 *<h1>Person class</h1>
 *<p>This class is use to collect the data of a person</p>
 *
 */

public class Person {
	
	//Attributes
	private int id;
	private String name,surname, gender;
	private HomeTown birthPlace;
	private String birthDate;
	private FiscalCode fc;
	
	public Person(int id,String name, String surname, String gender, HomeTown birthPlace, String birthDate, FiscalCode fc) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.fc = fc;
	}
	
	public void printPerson() {
		System.out.println("Person ID: " + id);
		System.out.println("\tNome: " + name);
		System.out.println("\tCognome: " + surname);
		System.out.println("\tSesso: " + gender);
		System.out.println("\tData di nascita: "+birthDate);
		birthPlace.printHomeTown();


	}
	
	//Getter and Setter
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
	
	
	

	
	
}
