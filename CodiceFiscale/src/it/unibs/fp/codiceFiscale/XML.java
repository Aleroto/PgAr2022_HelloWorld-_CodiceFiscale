package it.unibs.fp.codiceFiscale;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import java.util.ArrayList;
import java.io.FileOutputStream;


/**
 * Class to support the read and write of GENERAL XML file, try to create a class to read different XML like inputPersone.xml and comuni.xml,
 * that return a generic arraylist with XML tag name associated *
 */
public class XML {
	private static final String PATH_USER_DIRECTORY = System.getProperty("user.dir");
	private static final String PATH_XML_INPUT_PERSONE = "/XML/inputPersone.xml";
	private static final String PATH_XML_COMUNI = "/XML/comuni.xml";
	private static final String PATH_XML_CODICI_FISCALI = "/XML/codiciFiscali.xml";
	private static final String PATH_FILE_DESTINATION = "/XML/Risultati/";
	private static final String FILIPATCH_INPUT_PERSONE = PATH_USER_DIRECTORY + PATH_XML_INPUT_PERSONE; //File Path
	private static final String FILIPATCH_COMUNI = PATH_USER_DIRECTORY + PATH_XML_COMUNI;
	private static final String FILIPATCH_CODICI_FISCALI = PATH_USER_DIRECTORY + PATH_XML_CODICI_FISCALI;
	private static final String SEPARATORE = "--------------------------------------------------------------------------------------------------------------------";

	
	
	/**
	 * stampa su terminale l'intero file XML (for development uses)
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
				 case XMLStreamConstants.CHARACTERS: // content all�interno di un elemento: stampa il testo
					 if (xmlStreamReader.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
					 System.out.println("-> " + xmlStreamReader.getText());
					 break;
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	/**
	 * acquisisce tutti i valori di un tag dato in un arraylist
	 * @param type
	 * @return
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
	 * acquisisce il valore di un tag di una persona dato nome tag e id persona
	 * @param id
	 * @param tag
	 * @return
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
	 * genera un arraylist con tutti i nomi presenti nel file XML
	 * @return
	 */
	public static ArrayList<String> nameReader(){
		ArrayList<String> names = new ArrayList<String>();
		names.addAll(finder("nome"));
		return names;
	}
	
	/**
	 * genera un arraylist con tutti i cognomi presenti nel file XML
	 * @return
	 */
	private static ArrayList<String> surnameReader(){
		ArrayList<String> surnames = new ArrayList<String>();
		surnames.addAll(finder("cognome"));
		return surnames;
	}
	
	/**
	 * genera un arraylist con tutti i generi presenti nel file XML
	 * @return
	 */
	private static ArrayList<String> genderReader(){
		ArrayList<String> genders = new ArrayList<String>();
		genders.addAll(finder("sesso"));
		return genders;
	}
	
	/**
	 * genera un arraylist con tutti i luoghi di nascita presenti nel file XML
	 * @return
	 */
	private static ArrayList<String> birthPlaceReader(){
		ArrayList<String> birthPlaces = new ArrayList<String>();
		birthPlaces.addAll(finder("comune_nascita"));
		return birthPlaces;
	}
	
	/**
	 * genera un arraylist con tutte le date di nascita presenti nel file XML
	 * @return
	 */
	private static ArrayList<String> birthDateReader(){
		ArrayList<String> birthDates = new ArrayList<String>();
		birthDates.addAll(finder("data_nascita"));
		return birthDates;
	}
	
	/**
	 * genera un arraylist di Person con tutte le persone presenti nel file XML
	 * @return
	 */
	public static ArrayList<Person> peopleReader() {
		ArrayList<Person> people = new ArrayList<Person>();
		for(int i = 0; i < nameReader().size();i++) {
			people.add(new Person(i,nameReader().get(i),surnameReader().get(i),genderReader().get(i),homeTownIdReader(birthPlaceReader().get(i)),birthDateReader().get(i),null));
		}
		return people;
	}
	
	/**
	 * acquisisce una persona dal file XML dato l'id
	 * @param id
	 * @return
	 */
	public static Person findPerson(Integer id) {
		String idString =id.toString();
		Person person = new Person(id,personTagFinder(idString,"nome"),personTagFinder(idString,"cognome"),personTagFinder(idString,"sesso"),
				homeTownIdReader(personTagFinder(idString,"comune_nascita")),personTagFinder(idString,"data_nascita"),null);
		return person;
	}
	
	
	/**
	 * genera HomeTown dato il nome di un comune
	 * @param comune
	 * @return
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
	 * genera un arraylist di codici fiscali presenti nel file XML
	 * @return
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


	//verifica esistenza della cartella di destinazione
	private static void directoryExisting() {
		try {
            File f = new File(PATH_USER_DIRECTORY+PATH_FILE_DESTINATION);
            if (f.exists()) {
            	f.mkdir();
            	System.out.println("File created");
            }
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }

	}
	public static void writeXML(ArrayList<Person> people, ArrayList<String> fc_invalid, ArrayList<String> fc_unmatched) {
		directoryExisting();
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;

		Integer peopleSize = people.size();
		Integer invalidSize = fc_invalid.size();
		Integer unmatchedSize = fc_unmatched.size();	
		
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(PATH_USER_DIRECTORY + "/Prova/text.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeStartElement("output"); // scrittura del tag radice [1]
			//BLOCCO XML CON LE PERSONE, liverlli dei tag indicati con [x]
			xmlw.writeStartElement("persone"); //[2]
			xmlw.writeAttribute("numero", peopleSize.toString()); // attributo del tag persone che indica il numero di p. presenti
			xmlw.writeComment("Inizio della lista di persone"); // scrittura di un commento
			Integer count = null;
			for (int i = 0; i < peopleSize; i++) {
			count = i+1;
			xmlw.writeStartElement("persona"); // scrittura del tag PERSONA [3]
			xmlw.writeAttribute("id", count.toString()); // attributo 
			xmlw.writeStartElement("nome"); // scrittura del tag NOME[4]
			xmlw.writeCharacters(people.get(i).getName()); // valore contenuto nel tag
			xmlw.writeEndElement(); // chiusura tag
			xmlw.writeStartElement("cognome"); // scrittura del tag COGNOME[4]
			xmlw.writeCharacters(people.get(i).getSurname()); // valore contenuto nel tag
			xmlw.writeEndElement(); // chiusura tag
			xmlw.writeStartElement("sesso"); // scrittura del tag SESSO[4]
			xmlw.writeCharacters(people.get(i).getGender()); // valore contenuto nel tag
			xmlw.writeEndElement(); // chiusura tag
			xmlw.writeStartElement("comune_nascita"); // scrittura del tag COMUNE_NASCITA[4]
			xmlw.writeCharacters(people.get(i).getBirthPlace().getName()); // valore contenuto nel tag
			xmlw.writeEndElement(); // chiusura tag
			xmlw.writeStartElement("data_nascita"); // scrittura del tag DATA_NASCITA[4]
			xmlw.writeCharacters(people.get(i).getBirthDate()); // valore contenuto nel tag
			xmlw.writeEndElement(); // chiusura tag
			xmlw.writeStartElement("codice_fiscale"); // scrittura del tag CODICE FISCALE[4]
			xmlw.writeCharacters(people.get(i).getFc().getCode()); // valore contenuto nel tag
			xmlw.writeEndElement(); // chiusura tag
			xmlw.writeEndElement(); // chiusura tag
			}
			xmlw.writeEndElement(); //[2]
			
			//BLOCCO XML CON I CODICI, livelli dei tag indicati con [x]
			xmlw.writeStartElement("codici"); //[2]
			//BLOCCO XML CON I CODICI INVALIDI
			xmlw.writeStartElement("invalidi"); //[3]
			xmlw.writeAttribute("numero", invalidSize.toString()); // attributo del tag persone che indica il numero di p. presenti
			xmlw.writeComment("Inizio della lista di codici fiscali invalidi"); // scrittura di un commento
			for (String value : fc_invalid) {
				xmlw.writeStartElement("codice"); // scrittura del tag codice nella sezione codici invalidi[4]
				xmlw.writeCharacters(value); // valore contenuto nel tag
				xmlw.writeEndElement(); // chiusura tag
			}
			xmlw.writeEndElement(); //[3]
			
			//BLOCCO XML CON I CODICI SPAIATI
			xmlw.writeStartElement("spaiati"); //[3]
			xmlw.writeAttribute("numero", unmatchedSize.toString()); // attributo del tag persone che indica il numero di p. presenti
			xmlw.writeComment("Inizio della lista di codici fiscali spaiati"); // scrittura di un commento
			for (String value : fc_unmatched) {
				xmlw.writeStartElement("codice"); // scrittura del tag codice nella sezione codici invalidi[4]
				xmlw.writeCharacters(value); // valore contenuto nel tag
				xmlw.writeEndElement(); // chiusura tag
			}
			xmlw.writeEndElement(); //[3]
			
			xmlw.writeEndElement(); // [2]
			xmlw.writeEndElement(); // chiusura della root [1]
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//ottiene il numero di persone all'interno dell'XML
	public static int attrieCount(){
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
	 * stampa un separatore di riga
	 */
	private static void separatore() {
		System.out.println(SEPARATORE);
	}
	
}
