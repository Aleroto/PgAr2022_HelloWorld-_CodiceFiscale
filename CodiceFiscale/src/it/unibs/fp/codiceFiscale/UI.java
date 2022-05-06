package it.unibs.fp.codiceFiscale;

import java.util.ArrayList;
import java.util.regex.*;
import it.unibs.fp.Support.*;


/**
 *  Runs the program
 */

public class UI {
	
	private static final String FC_REGEX_EXPRESSION = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
	
	private static final int MATCHED = 0 ;		//codice generato esiste in XML
	private static final int ABSENT = 1 ;		//assente
	
	
	private static ArrayList<Person> people = new ArrayList<Person>();
	private static ArrayList<String> fcXml = new ArrayList<String>(); // arraylist with fiscal code take from XML
	private ArrayList<HomeTown> cities = new ArrayList<HomeTown>(); // arraylist with fiscal code take from XML 
	
	public static ArrayList<String> fcXmlMatched = new ArrayList<String>();		//codici corretti
	public static ArrayList<String> fcXmlAbsent = new ArrayList<String>();		//codici assenti
	public static ArrayList<String> fcXmlInvalid = new ArrayList<String>();		//codici invalidi
	public static ArrayList<String> fcXmlCorrect = new ArrayList<String>();		//codici corretti nell'XML che in fine conterrano gli spaiati
		
	//TODO lettura file xml dei comuni e importazione nell'arraylist
	//TODO parte finale di salvataggio in un xml diviso in due tag
	
	/**
	 * Run the main program in a user interface
	 */
	public static void runProgram() {
				
		 //Riempio arrayList con Codici Fiscali
		 fcXml = XML.fiscalCodeReader();
		 
		 
		 //Genero array invalidi e quelli corretti
		  for(int k = 0; k < fcXml.size(); k++) {
			  if(verifyFiscalCode(fcXml.get(k)))
				  fcXmlCorrect.add(fcXml.get(k)); 	//ci somo dentro gli spaiati, assenti e matched
			  else
				  fcXmlInvalid.add(fcXml.get(k)); 	//invalido 
		  }
		 
		
		//Genero e stampo tutte le informazioni delle persone
		 for (int i = 0; i < XML.attributeCount(); i++) { 
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
			 
		
			  //Stampo a video tutto
			  person.printPerson();	 
			  			  
		 }
		 
		 //Tolgo dall'array correct tutti gli assenti e tutti i matched 
		 //cosi mi rimangono gli spaiati in questo array
		 fcXmlCorrect.removeAll(fcXmlAbsent);
		 fcXmlCorrect.removeAll(fcXmlMatched);

		 /*
		 //stampo quelli spaiati
		 System.out.println("-----------SPAIATI------------");
		 for(int l = 0; l < fcXmlCorrect.size(); l++) {			 
			 System.out.println(fcXmlCorrect.get(l));
		 }
		 
		 //stampo quelli invalidi
		 System.out.println("-----------INVVALIDI------------");
		 for(int l = 0; l < fcXmlInvalid.size(); l++) {			 
			 System.out.println(fcXmlInvalid.get(l));
		 }
		 */
}
	 
		 
	/**
	 * Generates all the fiscal code by data from the XML collect in the arrayList
	 * @param people 
	 */
	private static void fcGenerator(ArrayList<Person> people) {
		// check if the people list is null
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
	 * Generates Fiscal Code
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
	 * Checks if the Fiscal Code is present in codiciFiscali.xml
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
	 * Verifies fiscal code
	 * @param fiscal_code
	 * @return true if it is written correctly
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