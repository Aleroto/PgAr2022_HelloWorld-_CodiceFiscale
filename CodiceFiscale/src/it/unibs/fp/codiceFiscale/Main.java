package it.unibs.fp.codiceFiscale;

import java.util.ArrayList;

public class Main {
	
	
	
	public static void main(String[] args) {
		XML.printXML();
		Person person = new Person(0,null,null,null,null,null,null);
		person = XML.findPerson(998);
		person.printPerson();
		
		
		
	}
}
