package it.unibs.fp.Support;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.regex.*;

import org.junit.Test;

public class JUnitTest {

	public void surnameGenerator() {
		//Attributes
		String surname = "O";
		int surnameLength = surname.length();
		String regex_vowels = "[AEIOU]|[aeiou]";
		ArrayList<String> vowels = new ArrayList<String>();
		ArrayList<String> consonants = new ArrayList<String>();
		String codSurname = null;
		
		/*vowels = splitVowelsConsonants(true, surname);
		consonants = splitVowelsConsonants(false, surname);
		*/
		//generate cod for length cases
		if (surnameLength >= 3) {
			switch (consonants.size()) {
				case 0: {
					codSurname = vowels.get(0) + vowels.get(1) + vowels.get(2);
					
				}
				case 1: {
					// for one consonants
					codSurname = consonants.get(0) + vowels.get(0) + vowels.get(1);
					
				}
				case 2: {
					// for two consonants
					codSurname = consonants.get(0) + consonants.get(1) + vowels.get(0);
					
				}
				default: {
					// for three or more consonants
					codSurname = consonants.get(0) + consonants.get(1) + consonants.get(2);
					
				}
			}
		}else if (surnameLength >= 1) {
			if(surnameLength == 1) {
				//length equals to 1
				codSurname = surname + "XX";
				
			}else {
				//length equals to 2
				codSurname = surname + "X";
				
			}
		}else {
			System.out.println("La lunghezza inserita e' minore o uguale a zero, ATTENZIONE!!");
		}

		assertNull(null);
		//return a value null
		//return codSurname;
	}
	
	@Test
	public void splitVowelsConsonants(boolean vowelsOrConsonant, String word){
		
		vowelsOrConsonant = true;
		word = "ALESSANDRO";
		
		ArrayList<String> vowels = new ArrayList<String>(null);
		ArrayList<String> consonants = new ArrayList<String>(null);
		String regex_vowels = "[AEIOU]|[aeiou]";
		
		if(vowelsOrConsonant) {
			//true for vowels
			for (int i = 0; i < word.length(); i++) {
				String character = word.substring(i, i+1);
				//collect the char in a arraylist and check if there are consecutive duplicates
				if (vowels.get(vowels.size() - 1) != null && vowels.get(vowels.size() - 1) == character) {
					continue;
				}
				else if (Pattern.matches(regex_vowels, character)) {
					vowels.add(word);
				}
			}
		}else {
			//false for consonants
			for (int i = 0; i < word.length(); i++) {
				String character = word.substring(i, i+1);
				//collect the char in a arraylist and check if there are consecutive duplicates
				if (consonants.get(consonants.size() - 1) != null && consonants.get(consonants.size() - 1) == character) {
					continue;
				}
				else if (!Pattern.matches(regex_vowels, character)) {
					consonants.add(word);
				}
			}
			
			assertNotNull(vowels);
			assertNotNull(consonants);
		}
	}
}
