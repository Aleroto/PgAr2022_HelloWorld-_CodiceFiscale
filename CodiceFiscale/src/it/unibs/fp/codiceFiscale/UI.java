package it.unibs.fp.codiceFiscale;

import java.util.ArrayList;
import java.util.regex.*;
import it.unibs.fp.Support.*;


/**
 *  Class for Run program
 */

public class UI {
	
	private static final String FC_REGEX_EXPRESSION = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	
	
	private static final int MATCHED = 0 ;		//codice generato esiste in xml
	private static final int ABSENT = 1 ;		//assente
	//private static final int INVALID = 2 ;
	
	
	private static ArrayList<Person> people = new ArrayList<Person>();
	private static ArrayList<String> fcXml = new ArrayList<String>(); // arraylist with fiscal code take from XML
	private ArrayList<HomeTown> cities = new ArrayList<HomeTown>(); // arraylist with fiscal code take from XML 
	
	public static ArrayList<String> fcXmlMatched = new ArrayList<String>();		//codici corretti
	public static ArrayList<String> fcXmlAbsent = new ArrayList<String>();
	public static ArrayList<String> fcXmlInvalid = new ArrayList<String>();
	public static ArrayList<String> fcXmlUnpaired = new ArrayList<String>();		//codici spaiati
	public static ArrayList<String> fcXmlCorrect = new ArrayList<String>();		//codici corretti nell'XML
	
	public static ArrayList<Integer> fcXmlIndex = new ArrayList<Integer>();		//array che mi savlva gli indici a cui devo rimuovere gli elementi
	//int[] index; 
	
	//TODO lettura file xml dei comuni e importazione nell'arraylist
	//TODO controllo codici fiscali presenti in people con quelli presenti in fcXml, assegnazione di un tag per capire se ? corretto e presente, assente, sbagliato
	//TODO parte finale di salvataggio in un xml diviso in due tag

	
	/**
	 * method for run the main program in a user interface
	 */
	public static void runProgram() {
				
		 
		 //riempio arrayList con Codici Fiscali
		 fcXml = XML.fiscalCodeReader();
		 
				 
		 //genero array invalidi e quelli corretti
		  for(int k = 0; k < fcXml.size(); k++) {
			  if(verifyFiscalCode(fcXml.get(k)))
				  fcXmlCorrect.add(fcXml.get(k)); 	//possibili spaiati, assenti e matched
			  else
				  fcXmlInvalid.add(fcXml.get(k)); 	//invalido 
		  }
		 
		
		//Genero e stampo tutte le informazioni delle persone
		 for (int i = 0; i < 999; i++) { 
			  Person person = new Person(0,null,null,null,null,null,null);
			  person = XML.findPerson(i);
			  
			  //genero i codici fiscali
			  PersoFcGenerator(person);
			  
			  String s = person.getFc().getCode();
		  	  //System.out.println(s);

			  //genero arrayList invalidi
			  switch(fcChecker(person.getFc().getCode())) {
			  
			  	case MATCHED:		
			  		fcXmlMatched.add(person.getFc().getCode());
			  		break;
			  		
			  	case ABSENT:
			  		 fcXmlAbsent.add(person.getFc().getCode());
			 		 //System.out.println(person.getFc().getCode());

			  		break;
			  		
			  	default:
			
			  }
			 
		
			  //stampo a video tutto
			  //person.printPerson();	 
			  			  
		 }
		 
		 //Copio tutti gli elementi corretti nell'array degli spaiati
		 //fcXmlUnpaired.addAll(fcXmlCorrect);
		 
		 
	
		 fcXmlCorrect.removeAll(fcXmlAbsent);
		 fcXmlCorrect.removeAll(fcXmlMatched);

		 
		 
		 
		 
		 
		 /*
		 //tolgo dagli spaiati quelli assenti
		 for(int t=0; t < fcXmlCorrect.size(); t++) {	
			 for(int b=0; b < fcXmlAbsent.size(); b++) {
				 if(fcXmlCorrect.get(t).equals(fcXmlAbsent.get(b))) {
					  //fcXmlUnpaired.remove(fcXmlCorrect.get(t));
					  fcXmlIndex.add(t);
					  break;
				  }
			 }  	
		 }
		 
		//tolgo dagli spaiati quelli presenti
		 for(int m=0; m < fcXmlCorrect.size(); m++) {
			  for(int n=0; n < fcXmlMatched.size(); n++) {
				  if(fcXmlCorrect.get(m).equals(fcXmlMatched.get(n))) {
					  //fcXmlUnpaired.remove(fcXmlCorrect.get(m));
					  fcXmlIndex.add(m);
					  break;
				  }
			 }  	
		  }
		
		 */

		 
		 
		 //mi rimangono quelli spaiati
		 for(int l = 0; l < fcXmlCorrect.size(); l++) {			 
			 System.out.println("-----------SPAIATI------------");
			 System.out.println(fcXmlCorrect.get(l));
		 }
	 
		 
}
	 
		 
		 
		 
		/*
		people = XML.peopleReader();

		fcGenerator(people);

		//people = XML.peopleReader();
		
		 for (int i = 0; i < 1000; i++) { 
		  Person person = new Person(0,null,null,null,null,null,null);
		  person = XML.findPerson(i);
		  PersoFcGenerator(person);
		  person.printPerson();
		 
		 } //TODO Ale prova la differenza di tempo, con peopleReader ? pi? veloce del finder, con il finder poi non si sapeva la quantit? di persone nel file e mettere 999 nel for non se po vede'. Altra cosa secondo te ? corretto a linea 8 l'arraylist come statico. Ho dovuto metterlo altrimenti non riuscivo  usare i metodi
		
		 
		/*fcGenerator(people);
		for (Person person : people) {
			person.printPerson();
		}
	*/

	

	/**
	 * method for generate all the fiscal code by data from the XML collect in the arrayList
	 * 
	 * @param people ArrayList of person to create
	 */
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
	
	
	/**
	 * method for generate Fiscal Code
	 * 
	 * @param person 
	 */
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
	
	
	
	/**
	 * method for check Fiscal Code
	 * 
	 * @param fcXml
	 * @param fcGnted
	 */
	public static Integer fcChecker(String fcGnted){
		for(int fc_xml = 0; fc_xml < fcXmlCorrect.size() ; fc_xml++){
			
			if(fcGnted.equals(fcXmlCorrect.get(fc_xml)))
				return 0; //codice corretto
		}
		
		
		return 1; // codice assente 
				
	}
					
		
		
	/**
	 * method for verify fiscal code
	 * @param fiscal_code
	 * @return
	 */
	public static boolean verifyFiscalCode(String fiscal_code){
		Pattern pattern = Pattern.compile(FC_REGEX_EXPRESSION);
		Matcher matcher = pattern.matcher(fiscal_code);
		if(matcher.matches())
			return true;
		else
			return false;
		
	}
}