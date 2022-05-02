package it.unibs.fp.codiceFiscale;

/**
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

public class FiscalCode {
	
	//Attributes
	private String name,surname,birthCodPlace,controlCharacter;
	private int birthYearDate, birthMonthDate, birthDayDate;
	
	//constructor
	
	
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
	public int getBirthYearDate() {
		return birthYearDate;
	}
	public void setBirthYearDate(int birthYearDate) {
		this.birthYearDate = birthYearDate;
	}
	public int getBirthMonthDate() {
		return birthMonthDate;
	}
	public void setBirthMonthDate(int birthMonthDate) {
		this.birthMonthDate = birthMonthDate;
	}
	public int getBirthDayDate() {
		return birthDayDate;
	}
	public void setBirthDayDate(int birthDayDate) {
		this.birthDayDate = birthDayDate;
	}
	public FiscalCode(String name, String surname, String birthCodPlace, String controlCharacter, int birthYearDate,
			int birthMonthDate, int birthDayDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthCodPlace = birthCodPlace;
		this.controlCharacter = controlCharacter;
		this.birthYearDate = birthYearDate;
		this.birthMonthDate = birthMonthDate;
		this.birthDayDate = birthDayDate;
	}
	
	
}
