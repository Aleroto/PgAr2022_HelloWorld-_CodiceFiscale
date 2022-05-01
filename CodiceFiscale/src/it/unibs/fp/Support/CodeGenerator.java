package it.unibs.fp.Support;

import java.util.ArrayList;
import java.util.regex.*;

public class CodeGenerator {

	public static String surnameGenerator(String surname) {
		//Attributes
		int surnameLength = surname.length();
		String regex_vowels = "[AEIOU]|[aeiou]";
		ArrayList<String> vowels = new ArrayList<String>();
		ArrayList<String> consonants = new ArrayList<String>();
		String codSurname = null;
		
		//get vowels and consonants
		for (int i = 0; i < surnameLength; i++) {
			String letter = surname.substring(i, i+1);
			// with a regex check if the letter is a vowel or consonant to save after in an
			// array
			if (Pattern.matches(regex_vowels, letter)) {
				vowels.add(letter);
			} else {
				consonants.add(letter);
			}
		}
		
		//generate cod for length cases
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
		}else if (surnameLength >= 1) {
			if(surnameLength == 1) {
				//length equals to 1
				codSurname = surname + "XX";
				return codSurname.toUpperCase();
			}else {
				//length equals to 2
				codSurname = surname + "X";
				return codSurname.toUpperCase();
			}
		}else {
			System.out.println("La lunghezza inserita e' minore o uguale a zero, ATTENZIONE!!");
		}
		
		//return a value null
		return codSurname;
	}
}
