package it.unibs.fp.codiceFiscale;

/**
 * 
 * Manages the different part of a fiscal code
 *
 *FST-PLA-98-M-01-B157-E: surname(3), name(3), yearBirth(2), monthBirth(1), dayBirth(2), birthPlace(1, 2), controlCharacter(1)
 */
public class FiscalCode {
	
	private String name,surname,birthCodPlace,controlCharacter, birthMonthDate, code, birthYearDate, birthDayDate;
	
	/**
	 * Constructor
	 * @param name	
	 * @param surname 
	 * @param birthCodPlace 
	 * @param controlCharacter 
	 * @param birthYearDate 
	 * @param birthMonthDate 
	 * @param birthDayDate 
	 * @param code 
	 */
	public FiscalCode(String name, String surname, String birthCodPlace, String controlCharacter, String birthYearDate,
			String birthMonthDate, String birthDayDate, String code) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthCodPlace = birthCodPlace;
		this.controlCharacter = controlCharacter;
		this.birthYearDate = birthYearDate;
		this.birthMonthDate = birthMonthDate;
		this.birthDayDate = birthDayDate;
		this.code = code;
	}
	
	/**
	 * Prints fiscal code (for development uses)
	 */
	public void printFiscalCode() {
		System.out.println("\tCodice fiscale: "+ code);

		/*
		 if(incorrect == 0) {
			System.out.println("\tCodice fiscale: "+ code);
		}
		else {
			System.out.println("\tCodice fiscale: "+ MSG_ASSENTE);
			incorrect = 0;
		}	
		 */
		
	}
	
	/**
	 * Prints all attributes of fiscal code (for development uses)
	 */
	public void printFiscalCodeAndSubString(){
		System.out.println("\tCod. nome codic fiscale: " + name);
		System.out.println("\tCod. cognome codic fiscale: " + surname);
		System.out.println("\tCod. giorno di nascita codic fiscale: " + birthDayDate);
		System.out.println("\tCod. mese di nascita codic fiscale: " + birthMonthDate);
		System.out.println("\tCod. anno di nascita codic fiscale: " + birthYearDate);	
		System.out.println("\tCod. luogo di nascita codic fiscale: " + birthCodPlace);
		System.out.println("\tCod. carattere di controllo codic fiscale: " + controlCharacter);
		System.out.println("\tCodic fiscale: " + code);

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
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	/**
	 * @return code 
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * @return birthCodPlace
	 */
	public String getBirthCodPlace() {
		return birthCodPlace;
	}
	
	/**
	 * @param birthCodPlace 
	 */
	public void setBirthCodPlace(String birthCodPlace) {
		this.birthCodPlace = birthCodPlace;
	}
	
	/**
	 * @return controlCharacter
	 */
	public String getControlCharacter() {
		return controlCharacter;
	}
	
	/**
	 * @param controlCharacter 
	 */
	public void setControlCharacter(String controlCharacter) {
		this.controlCharacter = controlCharacter;
	}
	
	/**
	 * @return birthYearDate
	 */
	public String getBirthYearDate() {
		return birthYearDate;
	}
	
	/**
	 * @param birthYearDate 
	 */
	public void setBirthYearDate(String birthYearDate) {
		this.birthYearDate = birthYearDate;
	}
	
	/**
	 * @return birthMonthDate
	 */
	public String getBirthMonthDate() {
		return birthMonthDate;
	}
	
	/**
	 * @param birthMonthDate
	 */
	public void setBirthMonthDate(String birthMonthDate) {
		this.birthMonthDate = birthMonthDate;
	}
	
	/**
	 * @return birthDayDate
	 */
	public String getBirthDayDate() {
		return birthDayDate;
	}
	
	/**
	 * @param birthDayDate 
	 */
	public void setBirthDayDate(String birthDayDate) {
		this.birthDayDate = birthDayDate;
	}

	
	
}
