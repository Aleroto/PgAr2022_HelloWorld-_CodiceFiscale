package it.unibs.fp.codiceFiscale;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;


public class XML {
	private static final String PATH_USER_DIRECTORY = System.getProperty("user.dir");
	private static final String PATH_XML_INPUT_PERSONE = "/XML/inputPersone.xml";
	private static final String PATH_XML_COMUNI = "/XML/comuni.xml";
	private static final String PATH_XML_CODICI_FISCALI = "/XML/codiciFiscali.xml";
	private static final String FILIPATCH_INPUT_PERSONE = PATH_USER_DIRECTORY + PATH_XML_INPUT_PERSONE; //File Path
	private static final String FILIPATCH_COMUNI = PATH_USER_DIRECTORY + PATH_XML_COMUNI;
	private static final String FILIPATCH_CODICI_FISCALI = PATH_USER_DIRECTORY + PATH_XML_CODICI_FISCALI;
	private static final String SEPARATORE = "--------------------------------------------------------------------------------------------------------------------";

	/*
	 * Class to support the read and write of GENERAL XML file, try to create a class to read different XML like inputPersone.xml and comuni.xml,
	 * that return a generic arraylist with XML tag name associated
	 */


	
	// stampa su terinale l'intero file XML (for development uses)
	public static void printXML() {		
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;
		
		try {
			//File Path		
			Reader fileReader = new FileReader(FILIPATCH_COMUNI);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			
			//Iterate through events.
			while(xmlStreamReader.hasNext()){
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  switch(xmlEvent) {
			  	case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
					 System.out.println("Start Read Doc " + FILIPATCH_INPUT_PERSONE); break;
				 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
					 System.out.println("Tag " + xmlStreamReader.getLocalName());
					 for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
						 separatore();
						 System.out.printf("=> attributo %s ->%s%n", xmlStreamReader.getAttributeLocalName(i), xmlStreamReader.getAttributeValue(i));
					 }
					 break;
				 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
					 //System.out.println("END-Tag " + xmlStreamReader.getLocalName());
					 break;
				 case XMLStreamConstants.COMMENT:
					 System.out.println("// commento " + xmlStreamReader.getText()); break; // commento: ne stampa il contenuto
				 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					 if (xmlStreamReader.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
					 System.out.println("-> " + xmlStreamReader.getText());
					 break;
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	public static ArrayList<String> finder(String type){
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;
		ArrayList<String> value = new ArrayList<String>();
			
		try {	
			Reader fileReader = new FileReader(FILIPATCH_INPUT_PERSONE);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			boolean stato = false;
			while(xmlStreamReader.hasNext()){			
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  if(stato) {
				  value.add(xmlStreamReader.getText());
				  stato = false;
			  }
			  if(xmlEvent == XMLStreamConstants.START_ELEMENT && xmlStreamReader.getLocalName() == type) {
				  stato = true;
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return value;
	}
	
	private static String personTagFinder(String id,String tag){
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;
		
		try {
			//File Path		
			Reader fileReader = new FileReader(FILIPATCH_INPUT_PERSONE);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			
			//Iterate through events.
			while(xmlStreamReader.hasNext()){
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  if(xmlEvent == XMLStreamConstants.START_ELEMENT) {
				 for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
					 if(xmlStreamReader.getAttributeValue(0).equals(id)) {
						 boolean stato = false;
						 for(int j = 0; j< 19;j++) {
							 xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
							 if(stato) {
								 String text = xmlStreamReader.getText();
								  return text;
							  }
							  if(xmlEvent == XMLStreamConstants.START_ELEMENT && xmlStreamReader.getLocalName() == tag) {
								  stato = true;
							  }
						 }
					 }					 
				}
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return null;
	}
	
	public static ArrayList<String> nameReader(){
		ArrayList<String> names = new ArrayList<String>();
		names.addAll(finder("nome"));
		return names;
	}
	private static ArrayList<String> surnameReader(){
		ArrayList<String> surnames = new ArrayList<String>();
		surnames.addAll(finder("cognome"));
		return surnames;
	}
	private static ArrayList<String> genderReader(){
		ArrayList<String> genders = new ArrayList<String>();
		genders.addAll(finder("sesso"));
		return genders;
	}
	private static ArrayList<String> birthPlaceReader(){
		ArrayList<String> birthPlaces = new ArrayList<String>();
		birthPlaces.addAll(finder("comune_nascita"));
		return birthPlaces;
	}
	private static ArrayList<String> birthDateReader(){
		ArrayList<String> birthDates = new ArrayList<String>();
		birthDates.addAll(finder("data_nascita"));
		return birthDates;
	}
	
	public static ArrayList<Person> peopleReader() {
		ArrayList<Person> people = new ArrayList<Person>();
		for(int i = 0; i < nameReader().size();i++) {
			people.add(new Person(i,nameReader().get(i),surnameReader().get(i),genderReader().get(i),homeTownIdReader(birthPlaceReader().get(i)),birthDateReader().get(i),null));
		}
		return people;
	}
	
	public static Person findPerson(Integer id) {
		String idString =id.toString();
		Person person = new Person(id,personTagFinder(idString,"nome"),personTagFinder(idString,"cognome"),personTagFinder(idString,"sesso"),
				homeTownIdReader(personTagFinder(idString,"comune_nascita")),personTagFinder(idString,"data_nascita"),null);
		return person;
	}
	
	
	public static HomeTown homeTownIdReader(String comune) {
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;		
		try {	
			Reader fileReader = new FileReader(FILIPATCH_COMUNI);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			boolean stato = false;
			while(xmlStreamReader.hasNext()){			
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.	  
			   
			  if(stato) {
				  if( xmlStreamReader.getText().equals(comune)) {
					  for(int i = 0; i < 4;i++) {
						  xmlStreamReader.next();
					  }
					  return new HomeTown(comune,xmlStreamReader.getText());
				  }
				  stato = false;
			  }
			 if(xmlEvent == XMLStreamConstants.START_ELEMENT && xmlStreamReader.getLocalName() == "nome") {
				  stato = true;
			  }
			 
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return null;
	}
	
	public static ArrayList<String> fiscalCodeReader(){
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;
		ArrayList<String> value = new ArrayList<String>();
			
		try {	
			Reader fileReader = new FileReader(FILIPATCH_CODICI_FISCALI);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			boolean stato = false;
			while(xmlStreamReader.hasNext()){			
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  if(stato) {
				  value.add(xmlStreamReader.getText());
				  stato = false;
			  }
			  if(xmlEvent == XMLStreamConstants.START_ELEMENT && xmlStreamReader.getLocalName() == "codice") {
				  stato = true;
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return value;
	}
		
	
	private static void separatore() {
		System.out.println(SEPARATORE);
	}
	
}
