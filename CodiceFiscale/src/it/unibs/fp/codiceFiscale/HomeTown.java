package it.unibs.fp.codiceFiscale;

/**
 * 
 * 
 *
 */
public class HomeTown {
	//Attributes
	private String name,code;

	public HomeTown(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
	public void printHomeTown() {
		System.out.println("\tNome comune: "+name);
		System.out.println("\tID comune: "+code);
	}
	
	//Getter and Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
}
