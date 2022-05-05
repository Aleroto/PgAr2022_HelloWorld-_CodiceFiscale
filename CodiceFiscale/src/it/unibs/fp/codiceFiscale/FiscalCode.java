package it.unibs.fp.codiceFiscale;

/*/**
 * 
 *<h1>Fiscal Code</h1>
 *<p>This class is use to manage the different part of a fiscal code.</p>
 *<p>FST-PLA-98-M-01-B157-E:
 *<dl>
 * <li>surname, 3 letters</li>
 * <li>name, 3 letters</li>
 * <li>year of birth, 2 number</li>
 * <li>month of birth, 1 letter</li>
 * <li>day of birth, 2 number</li>
 * <li>birth place, 1 letter and 2 number</li>
 * <li>control character, 1 letter</li>
 *</dl>
 *</p>
 *
 */

/**
 * 
 * Class is use to manage the different part of a fiscal code
 *
 *FST-PLA-98-M-01-B157-E: surname(3), name(3), yearBirth(2), monthBirth(1), dayBirth(2), birthPlace(1 letter, 2 number), controlCharacter(1)
 */
public class FiscalCode {
	
	private String name,surname,birthCodPlace,controlCharacter, birthMonthDate, code, birthYearDate, birthDayDate;
	
	private static final String MSG_ASSENTE = "ASSENTE";

	
	//constructor
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBirthCodPlace() {
		return birthCodPlace;
	}
	public void setBirthCodPlace(String birthCodPlace) {
		this.birthCodPlace = birthCodPlace;
	}
	public String getControlCharacter() {
		return controlCharacter;
	}
	public void setControlCharacter(String controlCharacter) {
		this.controlCharacter = controlCharacter;
	}
	public String getBirthYearDate() {
		return birthYearDate;
	}
	public void setBirthYearDate(String birthYearDate) {
		this.birthYearDate = birthYearDate;
	}
	public String getBirthMonthDate() {
		return birthMonthDate;
	}
	public void setBirthMonthDate(String birthMonthDate) {
		this.birthMonthDate = birthMonthDate;
	}
	public String getBirthDayDate() {
		return birthDayDate;
	}
	public void setBirthDayDate(String birthDayDate) {
		this.birthDayDate = birthDayDate;
	}

	
	
}
