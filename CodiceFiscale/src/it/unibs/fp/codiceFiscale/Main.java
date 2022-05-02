package it.unibs.fp.codiceFiscale;

import java.util.ArrayList;

public class Main {
	
	
	
	public static void main(String[] args) {
		System.out.println(XML.finder("nome"));
		System.out.println(XML.finder("cognome"));
		System.out.println(XML.finder("sesso"));
		System.out.println(XML.finder("data_nascita"));
		System.out.println(XML.nameReader());
		System.out.println(XML.peopleReader().get(5).getName());
		
	}
	
	private void print(ArrayList<String> nomi) {
		for(int i = 0; i<nomi.size();i++) {
			System.out.println(nomi.get(i));
		}
	}

}
