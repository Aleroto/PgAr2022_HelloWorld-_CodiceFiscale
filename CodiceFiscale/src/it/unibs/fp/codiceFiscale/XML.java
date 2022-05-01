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
	private static final String SEPARATORE = "--------------------------------------------------------------------------------------------------------------------";
	
	private FiscalCode fiscalCode;

	/*
	 * Class to support the read and write of GENERAL XML file, try to create a class to read different XML like inputPersone.xml and comuni.xml,
	 * that return a generic arraylist with XML tag name associated
	 */


	
	
	public static void readerXML() {		
		XMLInputFactory xmlInputFactory = null;
		XMLStreamReader xmlStreamReader  = null;
		
		try {
			//File Path
			String filePath = PATH_USER_DIRECTORY + PATH_XML_INPUT_PERSONE; //File Path		
			Reader fileReader = new FileReader(filePath);	//Read XML file.
			xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
	 
			//Iterate through events.
			while(xmlStreamReader.hasNext()){
				separatore();
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.
			  switch(xmlEvent) {
			  	case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
					 System.out.println("Start Read Doc " + filePath); break;
				 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
					 System.out.println("Tag " + xmlStreamReader.getLocalName());
					 for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++)
					 System.out.printf(" => attributo %s->%s%n", xmlStreamReader.getAttributeLocalName(i), xmlStreamReader.getAttributeValue(i));
					 break;
				 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
					 System.out.println("END-Tag " + xmlStreamReader.getLocalName()); break;
				 case XMLStreamConstants.COMMENT:
					 System.out.println("// commento " + xmlStreamReader.getText()); break; // commento: ne stampa il contenuto
				 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					 if (xmlStreamReader.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
					 System.out.println("-> " + xmlStreamReader.getText());
					 break;
			  }
			  /*
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {	//Process start element.
				System.out.println("Start Element: "+xmlStreamReader.getLocalName());
				int attributes =  xmlStreamReader.getAttributeCount();
				for(int i=0; i<attributes; i++){
				 QName name = xmlStreamReader.getAttributeName(i);
				String value=xmlStreamReader.getAttributeValue(i);
				 System.out.println(name + ": "+ value);
				}
			  }
			  
			  if (xmlEvent == XMLStreamConstants.END_ELEMENT) {		//Process end element.
				System.out.println("End Element: " +xmlStreamReader.getLocalName());
			  }*/
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	public static void prova2() throws XMLStreamException {
		
		String filename = PATH_USER_DIRECTORY + PATH_XML_INPUT_PERSONE;
		
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
		 xmlif = XMLInputFactory.newInstance();
		 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
		} catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del reader:");
		 System.out.println(e.getMessage());
		}
		
		while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
			 switch (xmlr.getEventType()) { // switch sul tipo di evento
				 case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
					 System.out.println("Start Read Doc " + filename); break;
				 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
					 System.out.println("Tag " + xmlr.getLocalName());
					 for (int i = 0; i < xmlr.getAttributeCount(); i++)
					 System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
					 break;
				 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
					 System.out.println("END-Tag " + xmlr.getLocalName()); break;
				 case XMLStreamConstants.COMMENT:
					 System.out.println("// commento " + xmlr.getText()); break; // commento: ne stampa il contenuto
				 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					 if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
					 System.out.println("-> " + xmlr.getText());
					 break;
			 }
			 xmlr.next();
			}

	}
	
	private static void separatore() {
		System.out.println(SEPARATORE);
	}
	

}
