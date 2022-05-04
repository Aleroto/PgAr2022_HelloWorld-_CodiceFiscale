package it.unibs.fp.codiceFiscale;

import java.util.ArrayList;
import it.unibs.fp.Support.*;

public class UI {

	private static ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<FiscalCode> fcXml = new ArrayList<FiscalCode>(); // arraylist with fiscal code take from XML
	private ArrayList<HomeTown> cities = new ArrayList<HomeTown>(); // arraylist with fiscal code take from XML //TODO lettura file xml dei comuni e importazione nell'arraylist
	//TODO controllo codici fiscali presenti in people con quelli presenti in fcXml, assegnazione di un tag per capire se è corretto e presente, assente, sbagliato
	//TODO parte finale di salvataggio in un xml diviso in due tag
	//todo 
	// method for run the main program in a user interface
	public static void runProgram() {
		people = XML.peopleReader();
		
		 for (int i = 0; i < 999; i++) { 
		  Person person = new Person(0,null,null,null,null,null,null);
		  person = XML.findPerson(i);
		  PersoFcGenerator(person);
		  person.printPerson();
		 
		 } //TODO Ale prova la differenza di tempo, con peopleReader è più veloce del finder, con il finder poi non si sapeva la quantità di persone nel file e mettere 999 nel for non se po vede'. Altra cosa secondo te è corretto a linea 8 l'arraylist come statico. Ho dovuto metterlo altrimenti non riuscivo  usare i metodi
		
		 
/*
		fcGenerator(people);
		for (Person person : people) {
			person.printPerson();
		}
		*/
	}

	// method for generate all the fiscal code by data from the XML collect in the
	// arraylist
	private static void fcGenerator(ArrayList<Person> people) {
		// chack if the people list is null
		if (people.equals(null)) {
			System.out.println("ERRORE");
		}
		// see the person in the list of people
		for (Person person : people) {
			FiscalCode fc = new FiscalCode(null, null, null, null, null, null, null, null);
			// check if the fiscal code is empty or not
			fc.setSurname(CodeGenerator.surnameGenerator(person.getSurname()));
			fc.setName(CodeGenerator.nameGenerator(person.getName()));
			fc.setBirthYearDate(CodeGenerator.yearGenerator(person.getBirthDate()));
			fc.setBirthMonthDate(CodeGenerator.monthGenerator(person.getBirthDate()));
			fc.setBirthDayDate(CodeGenerator.dayGenerator(person.getBirthDate(), person.getGender()));
			fc.setBirthCodPlace(person.getBirthPlace().getCode());

			String codeTemp = fc.getSurname() + fc.getName() + fc.getBirthYearDate() + fc.getBirthMonthDate()
					+ fc.getBirthDayDate() + fc.getBirthCodPlace();

			fc.setControlCharacter(CodeGenerator.controlCharacter(codeTemp));
			// set the code attributes that is all the part of the fiscal code togheter
			fc.setCode(codeTemp + fc.getControlCharacter());
			
			person.setFc(fc);
		}

		Integer aspetto = null;
	}
	private static void PersoFcGenerator(Person person) {
		// chack if the people list is null
		if (people.equals(null)) {
			System.out.println("ERRORE");
		}
		// see the person in the list of people
		
		FiscalCode fc = new FiscalCode(null, null, null, null, null, null, null, null);
		// check if the fiscal code is empty or not
		fc.setSurname(CodeGenerator.surnameGenerator(person.getSurname()));
		fc.setName(CodeGenerator.nameGenerator(person.getName()));
		fc.setBirthYearDate(CodeGenerator.yearGenerator(person.getBirthDate()));
		fc.setBirthMonthDate(CodeGenerator.monthGenerator(person.getBirthDate()));
		fc.setBirthDayDate(CodeGenerator.dayGenerator(person.getBirthDate(), person.getGender()));
		fc.setBirthCodPlace(person.getBirthPlace().getCode());

		String codeTemp = fc.getSurname() + fc.getName() + fc.getBirthYearDate() + fc.getBirthMonthDate()
				+ fc.getBirthDayDate().toString() + fc.getBirthCodPlace();

		fc.setControlCharacter(CodeGenerator.controlCharacter(codeTemp));
		// set the code attributes that is all the part of the fiscal code togheter
		fc.setCode(codeTemp + fc.getControlCharacter());
		
		person.setFc(fc);
	}
}
