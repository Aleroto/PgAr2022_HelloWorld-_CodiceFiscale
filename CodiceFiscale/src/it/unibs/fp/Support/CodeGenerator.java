package it.unibs.fp.Support;

import java.util.ArrayList;
import java.util.regex.*;
import it.unibs.fp.codiceFiscale.HomeTown;
import it.unibs.fp.codiceFiscale.Person;

public class CodeGenerator {

	//methods that return a cod of three letter from a surname string with the use of specific rules
	public static String surnameGenerator(String surname) {
		// Attributes
		int surnameLength = surname.length();
		ArrayList<String> vowels = new ArrayList<String>();
		ArrayList<String> consonants = new ArrayList<String>();
		String codSurname = null;

		// split the surname in two array, one of vowels and the other one in consonants
		vowels = splitVowelsConsonants(true, surname);
		consonants = splitVowelsConsonants(false, surname);

		// generate cod with surname rules
		if (surnameLength >= 3) {
			switch (consonants.size()) {
			case 0: {
				codSurname = vowels.get(0) + vowels.get(1) + vowels.get(2);
				return codSurname.toUpperCase();
			}
			case 1: {
				// for one consonants
				codSurname = consonants.get(0) + vowels.get(0) + vowels.get(1);
				return codSurname.toUpperCase();
			}
			case 2: {
				// for two consonants
				codSurname = consonants.get(0) + consonants.get(1) + vowels.get(0);
				return codSurname.toUpperCase();
			}
			default: {
				// for three or more consonants
				codSurname = consonants.get(0) + consonants.get(1) + consonants.get(2);
				return codSurname.toUpperCase();
			}
			}
		} else if (surnameLength >= 1) {
			if (surnameLength == 1) {
				// length equals to 1
				codSurname = surname + "XX";
				return codSurname.toUpperCase();
			} else {
				// length equals to 2
				codSurname = surname + "X";
				return codSurname.toUpperCase();
			}
		} else {
			System.out.println("La lunghezza inserita e' minore o uguale a zero, ATTENZIONE!!");
		}

		// return a value null
		return codSurname;
	}

	//methods that return a cod of three letter from a name string with the use of specific rules
	public static String nameGenerator(String name) {
		// Attributes
		int nameLength = name.length();
		ArrayList<String> vowels = new ArrayList<String>();
		ArrayList<String> consonants = new ArrayList<String>();
		String codName = null;

		// split the name in two array, one of vowels and the other one in consonants
		vowels = splitVowelsConsonants(true, name);
		consonants = splitVowelsConsonants(false, name);

		// generate cod with name rules
		if (nameLength >= 3) {
				switch (consonants.size()) {
				case 0: {
					codName = vowels.get(0) + vowels.get(1) + vowels.get(2);
					return codName.toUpperCase();
				}
				case 1: {
					codName = consonants.get(0) + vowels.get(0) + vowels.get(1);
					return codName.toUpperCase();
				}
				case 2: {
					// for one consonants
					codName = consonants.get(0) + consonants.get(1) + vowels.get(0);
					return codName.toUpperCase();
				}
				case 3: {
					// for two consonants
					codName = consonants.get(0) + consonants.get(1) + consonants.get(2);
					return codName.toUpperCase();
				}
				default: {
					// for four or more consonants
					codName = consonants.get(0) + consonants.get(2) + consonants.get(3);
					return codName.toUpperCase();
				}
			}
		} else if (nameLength >= 1) {
			if (nameLength == 1) {
				// length equals to 1
				codName = name + "XX";
				return codName.toUpperCase();
			} else {
				// length equals to 2
				codName = name + "X";
				return codName.toUpperCase();
			}
		} else {
			System.out.println("La lunghezza inserita e' minore o uguale a zero, ATTENZIONE!!");
		}

		// return a value null
		return codName;
	}

	//1999-12-17
	public static Integer yearGenerator(String value) {
		value = value.substring(2, 4);
		int year = 0;
		
		try {
			year = Integer.parseInt(value);
			return year;
        }
        catch (NumberFormatException e) { 
            // This is thrown when the String
            // contains characters other than digits
            System.out.println("Invalid String");
        }
		
		return null;	
	}
	
	//methods that return a specific char for the moth of bith date
	public static String monthGenerator(String value) {
		value = value.substring(5, 7);
		
		try {
			int temp = Integer.parseInt(value); //convert the string like 01 or 11 in a int for the switch
			switch (temp) {
				case 1: {
					//January
					return "A";
				}
				case 2: {
					//February
					return "B";
				}
				case 3: {
					//March
					return "C";
				}
				case 4: {
					//April
					return "D";
				}
				case 5: {
					//May
					return "E";
				}
				case 6: {
					//June
					return "H";
				}
				case 7: {
					//July
					return "L";
				}
				case 8: {
					//August
					return "M";
				}
				case 9: {
					//September
					return "P";
				}
				case 10: {
					//October
					return "R";
				}
				case 11: {
					//November
					return "S";
				}
				case 12: {
					//September
					return "T";
				}
				default: 
					return null;
			}
		}
        catch (NumberFormatException e) { 
            // This is thrown when the String
            // contains characters other than digits
            System.out.println("Invalid String");
        }
		
		return null;	
	}
	
	//method that return a Integer beetween 1 and 31 or 41 and 71, rappresents the day of birth date and the gender of the person
	public static Integer dayGenerator(String value, String gender) {
		value = value.substring(8);
		Integer valueInt = Integer.parseInt(value);
		String regex_vowels = "[FM]";
		if (valueInt <= 0 || valueInt > 31) {
			return null;
		}
		//check if gender is one char and is F or M
		if (gender.length()==1 && Pattern.matches(regex_vowels, gender)) {
			if (gender == "F") {
				valueInt = valueInt + 40;
			}
			return valueInt;
		}
		return null;	
	}
		
	//TODO cities generator
	
	public static String controlCharacter(String code) {
		String finalCode="";
		String charPosPari="";
		String charPosDispari="";
		int counter=0;
		for(int i=0;i<finalCode.length();i++){
			if(i%2==0)
				charPosDispari+=code.charAt(i); 
			else
				charPosPari+=code.charAt(i);
		}
		for(int i=0;i<charPosDispari.length();i++){
			switch(charPosDispari.charAt(i)){
			case '0': counter+=1;break;
			case '1': counter+=0;break;
			case '2': counter+=5;break;
			case '3': counter+=7;break;
			case '4': counter+=9;break;
			case '5': counter+=13;break;
			case '6': counter+=15;break;
			case '7': counter+=17;break;
			case '8': counter+=19;break;
			case '9': counter+=21;break;
			case 'A': counter+=1;break;
			case 'B': counter+=0;break;
			case 'C': counter+=5;break;
			case 'D': counter+=7;break;
			case 'E': counter+=9;break;
			case 'F': counter+=13;break;
			case 'G': counter+=15;break;
			case 'H': counter+=17;break;
			case 'I': counter+=19;break;
			case 'J': counter+=21;break;
			case 'K': counter+=2;break;
			case 'L': counter+=4;break;
			case 'M': counter+=18;break;
			case 'N': counter+=20;break;
			case 'O': counter+=11;break;
			case 'P': counter+=3;break;
			case 'Q': counter+=6;break;
			case 'R': counter+=8;break;
			case 'S': counter+=12;break;
			case 'T': counter+=14;break;
			case 'U': counter+=16;break;
			case 'V': counter+=10;break;
			case 'W': counter+=22;break;
			case 'X': counter+=25;break;
			case 'Y': counter+=24;break;
			case 'Z': counter+=23;break;
			}
		}
		for(int i=0;i<charPosPari.length();i++){
			switch(charPosPari.charAt(i)){
			case '0': counter+=0;break;
			case '1': counter+=1;break;
			case '2': counter+=2;break;
			case '3': counter+=3;break;
			case '4': counter+=4;break;
			case '5': counter+=5;break;
			case '6': counter+=6;break;
			case '7': counter+=7;break;
			case '8': counter+=8;break;
			case '9': counter+=9;break;
			case 'A': counter+=0;break;
			case 'B': counter+=1;break;
			case 'C': counter+=2;break;
			case 'D': counter+=3;break;
			case 'E': counter+=4;break;
			case 'F': counter+=5;break;
			case 'G': counter+=6;break;
			case 'H': counter+=7;break;
			case 'I': counter+=8;break;
			case 'J': counter+=9;break;
			case 'K': counter+=10;break;
			case 'L': counter+=11;break;
			case 'M': counter+=12;break;
			case 'N': counter+=13;break;
			case 'O': counter+=14;break;
			case 'P': counter+=15;break;
			case 'Q': counter+=16;break;
			case 'R': counter+=17;break;
			case 'S': counter+=18;break;
			case 'T': counter+=19;break;
			case 'U': counter+=20;break;
			case 'V': counter+=21;break;
			case 'W': counter+=22;break;
			case 'X': counter+=23;break;
			case 'Y': counter+=24;break;
			case 'Z': counter+=25;break;
			}
		}
		switch(counter%26){
			case 0: finalCode="A";break;
			case 1: finalCode="B";break;
			case 2: finalCode="C";break;
			case 3: finalCode="D";break;
			case 4: finalCode="E";break;
			case 5: finalCode="F";break;
			case 6: finalCode="G";break;
			case 7: finalCode="H";break;
			case 8: finalCode="I";break;
			case 9: finalCode="J";break;
			case 10: finalCode="K";break;
			case 11: finalCode="L";break;
			case 12: finalCode="M";break;
			case 13: finalCode="N";break;
			case 14: finalCode="O";break;
			case 15: finalCode="P";break;
			case 16: finalCode="Q";break;
			case 17: finalCode="R";break;
			case 18: finalCode="S";break;
			case 19: finalCode="T";break;
			case 20: finalCode="U";break;
			case 21: finalCode="V";break;
			case 22: finalCode="W";break;
			case 23: finalCode="X";break;
			case 24: finalCode="Y";break;
			case 25: finalCode="Z";break;
			}
		return finalCode;
		}
	
	//method to split a word in an array of vowels or consonants
	private static ArrayList<String> splitVowelsConsonants(boolean vowelsOrConsonant, String word) {

		ArrayList<String> vowels = new ArrayList<String>(null);
		ArrayList<String> consonants = new ArrayList<String>(null);
		String regex_vowels = "[AEIOU]|[aeiou]";

		if (vowelsOrConsonant) {
			// true for vowels
			for (int i = 0; i < word.length(); i++) {
				String character = word.substring(i, i + 1);
				// collect the char in a arraylist and check if there are consecutive duplicates
				if (vowels.get(vowels.size() - 1) != null && vowels.get(vowels.size() - 1) == character) {
					continue;
				} else if (Pattern.matches(regex_vowels, character)) {
					vowels.add(word);
				}
			}
			return vowels;
		} else {
			// false for consonants
			for (int i = 0; i < word.length(); i++) {
				String character = word.substring(i, i + 1);
				// collect the char in a arraylist and check if there are consecutive duplicates
				if (consonants.get(consonants.size() - 1) != null
						&& consonants.get(consonants.size() - 1) == character) {
					continue;
				} else if (!Pattern.matches(regex_vowels, character)) {
					consonants.add(word);
				}
			}
			return consonants;
		}
	}
}
