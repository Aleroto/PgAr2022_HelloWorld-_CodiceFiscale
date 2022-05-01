package it.unibs.fp.codiceFiscale;

public class HomeTown {
	//Attributes
	private String name,code;

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

	public HomeTown(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
}
