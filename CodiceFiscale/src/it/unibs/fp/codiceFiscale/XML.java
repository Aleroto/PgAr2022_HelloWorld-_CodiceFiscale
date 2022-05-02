package it.unibs.fp.codiceFiscale;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
 

public class XML {
	private static final String PATH_USER_DIRECTORY = System.getProperty("user.dir");
	private static final String PATH_XML_INPUT_PERSONE = "/XML/inputPersone.xml";
	String filePath = PATH_USER_DIRECTORY + PATH_XML_INPUT_PERSONE; //File Path
	private static final String SEPARATORE = "--------------------------------------------------------------------------------------------------------------------";
	
	private FiscalCode fiscalCode;

	/*
	 * Class to support the read and write of GENERAL XML file, try to create a class to read different XML like inputPersone.xml and comuni.xml,
	 * that return a generic arraylist with XML tag name associated
	 */


	
	
	public void printXML() {		
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
	
	public int IdBirthplace() {
		
	}
	
	public String Finder(String idPerson,String objectToFind, String objectType){
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;
		String object = null;
		int index = 0;
		
		try {
			Reader fileReader = new FileReader(filePath);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
			
			//Iterate through events.
			while(xmlStreamReader.hasNext()){
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  switch(xmlEvent) {
				 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
					 for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
						 separatore();
						 if(xmlStreamReader.getAttributeLocalName(i) == idPerson) {
							 index = i;
						 }
						 //System.out.printf("=> attributo %s ->%s%n", xmlStreamReader.getAttributeLocalName(i), xmlStreamReader.getAttributeValue(i));
					 }
					 break;
				 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
					 //System.out.println("END-Tag " + xmlStreamReader.getLocalName());
					 break;
				 case XMLStreamConstants.COMMENT:
					 System.out.println("// commento " + xmlStreamReader.getText()); break; // commento: ne stampa il contenuto
				 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo					 
					 if(xmlStreamReader.getLocalName() == objectToFind){
						 object = 
					 }
					 if (xmlStreamReader.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
					 System.out.println("-> " + xmlStreamReader.getText());
					 break;
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return object;
	}
	
	
	
	private static void separatore() {
		System.out.println(SEPARATORE);
	}
	
	

}
