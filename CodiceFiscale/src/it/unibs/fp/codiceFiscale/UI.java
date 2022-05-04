package it.unibs.fp.codiceFiscale;

import java.util.ArrayList;
import java.util.regex.*;
import it.unibs.fp.Support.*;


public class UI {

	private static final String FC_REGEX_EXPRESSION = "/^(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i";
	private static ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<FiscalCode> fcXml = new ArrayList<FiscalCode>(); // arraylist with fiscal code take from XML
	private ArrayList<HomeTown> cities = new ArrayList<HomeTown>(); // arraylist with fiscal code take from XML //TODO lettura file xml dei comuni e importazione nell'arraylist
	//TODO controllo codici fiscali presenti in people con quelli presenti in fcXml, assegnazione di un tag per capire se � corretto e presente, assente, sbagliato
	//TODO parte finale di salvataggio in un xml diviso in due tag
	//todo 
	// method for run the main program in a user interface
	public static void runProgram() {
		 for (int i = 0; i < 999; i++) { 
			  Person person = new Person(0,null,null,null,null,null,null);
			  person = XML.findPerson(i);
			  PersoFcGenerator(person);
			  person.printPerson();	 
		 }
		 
		/*
		people = XML.peopleReader();

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
			// set the code attributes that is all the part of the fiscal code together
			fc.setCode(codeTemp + fc.getControlCharacter());
			
			person.setFc(fc);
		}

		Integer aspetto = null;
	}
	private static void PersoFcGenerator(Person person) {
		// check if the people list is null
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
	
	//fcChecker 
	public void fcChecker(ArrayList<FiscalCode> fcXml, ArrayList<FiscalCode> fcGnted){
		int present = 0;
		int invalid = 0;		//numero invalidi
		int unmatched = 0;		//numero spaiati
		boolean correct = false;
			

		for(FiscalCode fc_generated: fcGnted){
			for(FiscalCode fc_xml: fcXml ){
				if(!(fcGnted.get(fc_generated).equals(fcXml.get(fc_xml)){
						present = 1; //non tutti i codici fiscali sono presenti nel file xml
						//scrivere su codiciPersone.xml che il Codice Fiscale è ASSENTE
				}
				else{
					if(verifyFiscalCode() == false){
						invalid++;
						//lo aggiungo in sezione codici invalidi  su codiciPersone.xml 
					}
				}
				
			}
			
		}
		
	}

		
	//Validità dei codici fiscali
	public static boolean verifyFiscalCode(String fiscal_code){
		Pattern pattern = Pattern.compile(FC_REGEX_EXPRESSION);
		Matcher matcher = pattern.matcher(fiscal_code);
		if(matcher.matches())
			return true;
		else
			return false;
	}
 }



