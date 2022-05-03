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
	private static final String filePath = PATH_USER_DIRECTORY + PATH_XML_INPUT_PERSONE; //File Path
	private static final String SEPARATORE = "--------------------------------------------------------------------------------------------------------------------";
	
	private static ArrayList<String> names = new ArrayList<String>();


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
			Reader fileReader = new FileReader(filePath);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			
			//Iterate through events.
			while(xmlStreamReader.hasNext()){
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  switch(xmlEvent) {
			  	case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
					 System.out.println("Start Read Doc " + filePath); break;
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
			Reader fileReader = new FileReader(filePath);	//Read XML file.
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
	
	public static Person personFinder(String id){
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;
		Person person = new Person(null,null,null,null,null,null);
			
		try {	
			Reader fileReader = new FileReader(filePath);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			boolean stato = false;
			while(xmlStreamReader.hasNext()){			
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  if(xmlEvent == XMLStreamConstants.START_ELEMENT ) {
				  System.out.println("Tag " + xmlStreamReader.getLocalName());
					 for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
						 separatore();
						 if(xmlStreamReader.getAttributeValue(i).equals(id)) {
							 xmlEvent = xmlStreamReader.next();
							 while(xmlEvent != XMLStreamConstants.END_ELEMENT) {
								 
								  if(xmlEvent == XMLStreamConstants.START_ELEMENT && xmlStreamReader.getLocalName() == "nome") {									  
									  stato = true;
								  }
								  if(stato) {
									  person.setName(xmlStreamReader.getText());
									  stato = false;
								  }
								 xmlEvent = xmlStreamReader.next();
							 }
						 }
					 }
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return value;
	}
	
	public static ArrayList<String> nameReader(){
		ArrayList<String> names = (ArrayList<String>) finder("name");
		return names;
	}
	private static ArrayList<String> sournameReader(){
		ArrayList<String> sournames = new ArrayList<String>();
		return sournames = finder("cognome");
	}
	private static ArrayList<String> genderReader(){
		ArrayList<String> genders = new ArrayList<String>();
		return genders = finder("sesso");
	}
	private static ArrayList<String> birthPlaceReader(){
		ArrayList<String> birthPlaces = new ArrayList<String>();
		return birthPlaces = finder("comune_nascita");
	}
	private static ArrayList<String> birthDateReader(){
		ArrayList<String> birthDates = new ArrayList<String>();
		return birthDates = finder("data_nascita");
	}
	
	public static ArrayList<Person> peopleReader() {
		ArrayList<Person> people = new ArrayList<Person>();
		for(int i = 0; i < nameReader().size();i++) {
			people.add(new Person(finder("name").get(i),finder("cognome").get(i),finder("sesso").get(i),null,null,null));
		}
		return people;
	}
	
	
	
	
	
	private static void separatore() {
		System.out.println(SEPARATORE);
	}
	private static void print(ArrayList<String> nomi) {
		for(int i = 0; i<nomi.size();i++) {
			System.out.println(nomi.get(i));
		}
	}
	
	

}
