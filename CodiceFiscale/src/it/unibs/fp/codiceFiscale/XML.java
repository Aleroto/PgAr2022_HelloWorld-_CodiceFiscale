package it.unibs.fp.codiceFiscale;

import java.io.File;



import java.io.FileReader;
import java.io.Reader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;


import java.util.ArrayList;
import java.io.FileOutputStream;


/**
 * Supports the read and write of GENERAL XML file, try to create a class to read different XML like inputPersone.xml and comuni.xml,
 * that return a generic arrayList with XML tag name associated *
 */
public class XML {
	//Attributes
	private static final String PATH_USER_DIRECTORY = System.getProperty("user.dir");
	private static final String PATH_XML_INPUT_PERSONE = "/XML/inputPersone.xml";
	private static final String PATH_XML_COMUNI = "/XML/comuni.xml";
	private static final String PATH_XML_CODICI_FISCALI = "/XML/codiciFiscali.xml";	
	private static final String PATH_FILE_DESTINATION = "/XML/CodiciGenerati/";
	private static final String FILIPATCH_INPUT_PERSONE = PATH_USER_DIRECTORY + PATH_XML_INPUT_PERSONE; //File Path
	private static final String FILIPATCH_COMUNI = PATH_USER_DIRECTORY + PATH_XML_COMUNI;
	private static final String FILIPATCH_CODICI_FISCALI = PATH_USER_DIRECTORY + PATH_XML_CODICI_FISCALI;
	
	private static final String SEPARATORE = "--------------------------------------------------------------------------------------------------------------------";

	
	/**
	 * Prints all the file XML (for development uses)
	 */ 
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
			  	case XMLStreamConstants.START_DOCUMENT: //Inizio del documento: stampa che inizia il documento
					 System.out.println("Start Read Doc " + FILIPATCH_INPUT_PERSONE); break;
				 case XMLStreamConstants.START_ELEMENT: //Inizio di un elemento: stampa il nome del tag e i suoi attributi
					 System.out.println("Tag " + xmlStreamReader.getLocalName());
					 for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
						 separatore();
						 System.out.printf("=> attributo %s ->%s%n", xmlStreamReader.getAttributeLocalName(i), xmlStreamReader.getAttributeValue(i));
					 }
					 break;
				 case XMLStreamConstants.END_ELEMENT: //Fine di un elemento: stampa il nome del tag chiuso
					 //System.out.println("END-Tag " + xmlStreamReader.getLocalName());
					 break;
				 case XMLStreamConstants.COMMENT:
					 System.out.println("// commento " + xmlStreamReader.getText()); break; //Commento: ne stampa il contenuto
				 case XMLStreamConstants.CHARACTERS: //Contentenuto interno di un elemento: stampa il testo
					 if (xmlStreamReader.getText().trim().length() > 0) //Controlla se il testo non contiene solo spazi
					 System.out.println("-> " + xmlStreamReader.getText());
					 break;
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	
	/**
	 * @param type 
	 * @return Capture all tag value from an arrayList
	 */
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
	
	
	 /**
	 * @param id 
	 * @param tag 
	 * @return Capture the tag value of a person from tag and id of the person
	 */
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
	
	/**
	 * @return An ArrayList<String> generated with all present names in the file XML
	 */
	public static ArrayList<String> nameReader(){
		ArrayList<String> names = new ArrayList<String>();
		names.addAll(finder("nome"));
		return names;
	}
	
	/**
	 * @return An ArrayList<String> generated with all present surnames in the file XML
	 */
	private static ArrayList<String> surnameReader(){
		ArrayList<String> surnames = new ArrayList<String>();
		surnames.addAll(finder("cognome"));
		return surnames;
	}
	
	/**
	 * @return An ArrayList<String> generated with all present gender in the file XML
	 */
	private static ArrayList<String> genderReader(){
		ArrayList<String> genders = new ArrayList<String>();
		genders.addAll(finder("sesso"));
		return genders;
	}
	
	/**
	 * @return An ArrayList<String> generated with all present birth places in the file XML
	 */
	private static ArrayList<String> birthPlaceReader(){
		ArrayList<String> birthPlaces = new ArrayList<String>();
		birthPlaces.addAll(finder("comune_nascita"));
		return birthPlaces;
	}
	
	/**
	 * @return An ArrayList<String> generated with all present birth dates in the file XML
	 */
	private static ArrayList<String> birthDateReader(){
		ArrayList<String> birthDates = new ArrayList<String>();
		birthDates.addAll(finder("data_nascita"));
		return birthDates;
	}
	
	
	/**
	 * @return An ArrayList<Person> generated with all present people in the file XML
	 */
	public static ArrayList<Person> peopleReader() {
		ArrayList<Person> people = new ArrayList<Person>();
		for(int i = 0; i < nameReader().size();i++) {
			people.add(new Person(i,nameReader().get(i),surnameReader().get(i),genderReader().get(i),homeTownIdReader(birthPlaceReader().get(i)),birthDateReader().get(i),null));
		}
		return people;
	}
	
	
	/**
	 * @param id 
	 * @return Captures a person from the XML file given the id
	 */
	public static Person findPerson(Integer id) {
		String idString =id.toString();
		Person person = new Person(id,personTagFinder(idString,"nome"),personTagFinder(idString,"cognome"),personTagFinder(idString,"sesso"),
				homeTownIdReader(personTagFinder(idString,"comune_nascita")),personTagFinder(idString,"data_nascita"),null);
		return person;
	}
	
	
	/**
	 * @param comune 
	 * @return HomeTown given the name of a municipality
	 */
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
	
	
	/**
	 * @return An ArrayList<String>  of fiscal codes present in the XML
	 */
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

	
	/**
	 * Checks for the destination folder
	 */
	private static void directoryExisting() {
		try {
            File f = new File(PATH_USER_DIRECTORY+PATH_FILE_DESTINATION);
            if (!f.exists()) {
                System.out.println("Creating directory");
            	f.mkdir();
            }
		}
        catch (Exception e) {
            System.err.println(e);
        }

	}
	
	/**
	 * Write file output
	 * @param people 
	 * @param fc_invalid 
	 * @param fc_unmatched 
	 */
	public static void writeXML(ArrayList<Person> people, ArrayList<String> fc_invalid, ArrayList<String> fc_unmatched, ArrayList<String> fc_absent) {
		directoryExisting();
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;

		int absent = 0; //Variabile per gestione stampa assente
		Integer peopleSize = people.size();
		Integer invalidSize = fc_invalid.size();
		Integer unmatchedSize = fc_unmatched.size();	
		
		try {
			xmlof = XMLOutputFactory.newInstance();
			//xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(PATH_USER_DIRECTORY + "/Prova/text.xml"), "utf-8");
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(PATH_USER_DIRECTORY + "/XML/CodiciGenerati/codiciPersone.xml"), "utf-8");

			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeStartElement("output"); //Scrittura del tag radice [1]
			//BLOCCO XML CON LE PERSONE, liverlli dei tag indicati con [x]
			xmlw.writeStartElement("persone"); //[2]
			xmlw.writeAttribute("numero", peopleSize.toString()); //Attributo del tag persone che indica il numero di p. presenti
			xmlw.writeComment("Inizio della lista di persone"); //Scrittura di un commento
			Integer count = null;
			for (int i = 0; i < peopleSize; i++) {
				count = i+1;
				xmlw.writeStartElement("persona"); //Scrittura del tag PERSONA [3]
				xmlw.writeAttribute("id", count.toString()); //Attributo 
				xmlw.writeStartElement("nome"); //Scrittura del tag NOME[4]
				xmlw.writeCharacters(people.get(i).getName()); //Valore contenuto nel tag
				xmlw.writeEndElement(); // chiusura tag
				xmlw.writeStartElement("cognome"); //Scrittura del tag COGNOME[4]
				xmlw.writeCharacters(people.get(i).getSurname()); //Valore contenuto nel tag
				xmlw.writeEndElement(); // chiusura tag
				xmlw.writeStartElement("sesso"); //Scrittura del tag SESSO[4]
				xmlw.writeCharacters(people.get(i).getGender()); //Valore contenuto nel tag
				xmlw.writeEndElement(); // chiusura tag
				xmlw.writeStartElement("comune_nascita"); //Scrittura del tag COMUNE_NASCITA[4]
				xmlw.writeCharacters(people.get(i).getBirthPlace().getName()); //Valore contenuto nel tag
				xmlw.writeEndElement(); // chiusura tag
				xmlw.writeStartElement("data_nascita"); //Scrittura del tag DATA_NASCITA[4]
				xmlw.writeCharacters(people.get(i).getBirthDate()); //Valore contenuto nel tag
				xmlw.writeEndElement(); // chiusura tag
				xmlw.writeStartElement("codice_fiscale"); //Scrittura del tag CODICE FISCALE[4]
			
				for(int k = 0; k < fc_absent.size(); k++) {
					if((people.get(i).getFc().getCode()).equals(fc_absent.get(k)))
						absent = 1;
				}
				
				if(absent == 1) {
					xmlw.writeCharacters("ASSENTE");
					absent = 0;
				}	
				else {
					xmlw.writeCharacters(people.get(i).getFc().getCode()); //Valore contenuto nel tag
				}
					
			xmlw.writeEndElement(); //Chiusura tag
			xmlw.writeEndElement(); //Chiusura tag
			}
			xmlw.writeEndElement(); //[2]
			
			//BLOCCO XML CON I CODICI, livelli dei tag indicati con [x]
			xmlw.writeStartElement("codici"); //[2]
			//BLOCCO XML CON I CODICI INVALIDI
			xmlw.writeStartElement("invalidi"); //[3]
			xmlw.writeAttribute("numero", invalidSize.toString()); //Attributo del tag persone che indica il numero di p. presenti
			xmlw.writeComment("Inizio della lista di codici fiscali invalidi"); //Scrittura di un commento
			for (String value : fc_invalid) {
				xmlw.writeStartElement("codice"); //Scrittura del tag codice nella sezione codici invalidi[4]
				xmlw.writeCharacters(value); //Valore contenuto nel tag
				xmlw.writeEndElement(); //Chiusura tag
			}
			xmlw.writeEndElement(); //[3]
			
			//BLOCCO XML CON I CODICI SPAIATI
			xmlw.writeStartElement("spaiati"); //[3]
			xmlw.writeAttribute("numero", unmatchedSize.toString()); //Attributo del tag persone che indica il numero di p. presenti
			xmlw.writeComment("Inizio della lista di codici fiscali spaiati"); //Scrittura di un commento
			for (String value : fc_unmatched) {
				xmlw.writeStartElement("codice"); //Scrittura del tag codice nella sezione codici invalidi[4]
				xmlw.writeCharacters(value); //Valore contenuto nel tag
				xmlw.writeEndElement(); //Chiusura tag
			}
			xmlw.writeEndElement(); //[3]
			
			xmlw.writeEndElement(); // [2]
			xmlw.writeEndElement(); //Chiusura della root [1]
			xmlw.writeEndDocument(); // Scrittura della fine del documento
			xmlw.flush(); //Svuota il buffer e procede alla scrittura
			xmlw.close(); //Chiusura del documento e delle risorse impiegate*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return The number of people within the XML
	 */
	public static int attributeCount(){
			XMLInputFactory xmlInputFactory = null;
			XMLStreamReader xmlStreamReader  = null;
			int count = 0;
				
			try {	
				Reader fileReader = new FileReader(FILIPATCH_CODICI_FISCALI);	//Read XML file.
				xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
				xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
				while(xmlStreamReader.hasNext()){		
					if(xmlStreamReader.next() == XMLStreamConstants.START_ELEMENT) {
						count++;
					}

				}
			  } catch (Exception e) {
				e.printStackTrace();
				return -1;
			  }
			return count-1;
		}
	/**
	 * Print line separator
	 */
	private static void separatore() {
		System.out.println(SEPARATORE);
	}
	
	
	
}
