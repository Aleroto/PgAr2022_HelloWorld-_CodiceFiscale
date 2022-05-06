package it.unibs.fp.codiceFiscale;

/**
 * Used for support in other classes
 */
public class HomeTown {
	//Attributes
	private String name,code;
	
	/**
	 * Constructor
	 * @param name 
	 * @param code 
	 */
	public HomeTown(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
	/**
	 * Prints HomeTown
	 */
	public void printHomeTown() {
		System.out.println("\tNome comune: "+name);
		System.out.println("\tID comune: "+code);
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
	
}
