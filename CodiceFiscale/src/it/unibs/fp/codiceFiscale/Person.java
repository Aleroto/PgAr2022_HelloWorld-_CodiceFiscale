package it.unibs.fp.codiceFiscale;

/**
 *Collects the data of a person
 */
public class Person {
	
	//Attributes
	private int id;
	private String name,surname, gender;
	private HomeTown birthPlace;
	private String birthDate;
	private FiscalCode fc;
	
	/**
	 * Constructor
	 * @param id 
	 * @param name 
	 * @param surname 
	 * @param gender 
	 * @param birthPlace 
	 * @param birthDate 
	 * @param fc 
	 */
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
	
	/**
	 * Prints person information
	 */
	public void printPerson() {
		System.out.println("Person ID: " + id);
		System.out.println("\tNome: " + name);
		System.out.println("\tCognome: " + surname);
		System.out.println("\tSesso: " + gender);
		System.out.println("\tData di nascita: "+birthDate);
		birthPlace.printHomeTown();
		fc.printFiscalCode();
	}
	
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname
	 */
	
	/**
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return birthPlace
	 */
	public HomeTown getBirthPlace() {
		return birthPlace;
	}
	/**
	 * @param birthPlace
	 */
	public void setBirthPlace(HomeTown birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	/**
	 * @return birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return fc
	 */
	public FiscalCode getFc() {
		return fc;
	}
	/**
	 * @param fc
	 */
	public void setFc(FiscalCode fc) {
		this.fc = fc;
	}

}
