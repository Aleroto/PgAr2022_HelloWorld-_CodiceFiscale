package it.unibs.fp.codiceFiscale;

import java.io.FileReader;
import java.io.Reader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
 

public class XML {
/*
 * Class to support the read and write of GENERAL XML file, try to create a class to read different XML like inputPersone.xml and comuni.xml,
 * that return a generic arraylist with XML tag name associated
 */
	public static void ciao() {
		System.out.println("ciao");
	}
	
	public static void lettura() {
		try {
			//File Path
			String filePath = "D:\\Desktop\\scuola\\universita\\UNIBS\\Arnaldo\\gruppo\\PgAr2022_HelloWorld-_CodiceFiscale\\XML\\inputPersone.xml"; //File Path		
			Reader fileReader = new FileReader(filePath);	//Read XML file.
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();	//Get XMLInputFactory instance.			
			XMLStreamReader xmlStreamReader  = xmlInputFactory.createXMLStreamReader(fileReader);	//Create XMLStreamReader object.
	 
			//Iterate through events.
			while(xmlStreamReader.hasNext()){
			  int xmlEvent = xmlStreamReader.next();	//Get integer value of current event.			  
			  if (xmlEvent == XMLStreamConstants.START_ELEMENT) {	//Process start element.
				System.out.println("Start Element: "+xmlStreamReader.getLocalName());
				int attributes =  xmlStreamReader.getAttributeCount();
				for(int i=0; i<attributes; i++){
				 QName name = xmlStreamReader.getAttributeName(i);
				String value=xmlStreamReader.getAttributeValue(i);
				 System.out.println("Attribute name: " + name);
				 System.out.println("Attribute value: " + value);
				}
			  }
			  
			  if (xmlEvent == XMLStreamConstants.END_ELEMENT) {		//Process end element.
				System.out.println("End Element: " +xmlStreamReader.getLocalName());
			  }
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
	

}
