package it.unibs.fp.codiceFiscale;

import java.util.ArrayList;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		ArrayList<String> nomi = new ArrayList<String>();
		XML proova = new XML();
		
		
		System.out.println(proova.peopleReader());
		proova.peopleReader();		
		//XML.printXML();
		
		
	}
	
	private void print(ArrayList<String> nomi) {
		for(int i = 0; i<nomi.size();i++) {
			System.out.println(nomi.get(i));
		}
	}

}
