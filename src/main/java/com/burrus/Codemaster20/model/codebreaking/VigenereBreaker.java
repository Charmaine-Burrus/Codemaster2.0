package com.burrus.Codemaster20.model.codebreaking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import com.burrus.Codemaster20.model.codemaking.VigenereCipher;

/**
 * This class creates a VigenereBreaker object which can be used to "break"/crack an unknown code (if that code uses a Vigenere Cipher).
 * It has the public method breakVigenereCipher which breaks/cracks the cipher and prints out the decrypted message.
 * 
 * Provisions have been made to preserve original case (upper or lower).
 * Provisions have been made so that code can be used to crack the code for a known message language or unknown language.
 * 
 * See the readme for more details, including explanation of how a Vingere Cipher works.
 * 
 * @ceb
 * @5-6-20
 */

public class VigenereBreaker {
	
	private String sliceString(String encryptedMessage, int startingIndex, int keyLength) {
		//returns a slice of the message which consists of all the characters that were shifted with the same CasearCipher
		//startingIndexOf is the index within the encryptedMessage where sliceString should start to create this slice
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i=startingIndex; i<encryptedMessage.length(); i=i+keyLength) {
            decryptedMessage.append(encryptedMessage.charAt(i));
        }
        return decryptedMessage.toString();
    }
	
	private int[] tryKeyLength(String encryptedMessage, int keyLength, char mostCommonLetter) {
		System.out.println("inside tryKeyLength");
		//returns what the key(array of ints representing how much to shift each letter) would be for a given keyLenth
        //keyLength will also correspond to the total number of slices that need to be made and for which we need keys
        int[] key = new int[keyLength];
        System.out.println("key=" + key);
        CaesarBreaker cb = new CaesarBreaker(mostCommonLetter);
        System.out.println("cb=" + cb);
        for (int i=0; i<keyLength; i++) {
        	System.out.println("inside for loop i=" + i);
            String slice = sliceString(encryptedMessage, i, keyLength);
            System.out.println("We are setting key[i] to slice. slice=" + slice);
            key[i] = cb.getKey(slice);
            System.out.println("finished this iteration of for loop");
        }
        return key;
    }
	
	// get file from classpath, resources folder
    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
	
	public HashSet<String> readDictionary(String language) {
		File file = getFileFromResources("dictionaries/" + language+".txt");

        HashSet<String> dictionary = new HashSet<String>();
        //try with resources
        try(BufferedReader dictionaryBR = new BufferedReader(new FileReader(file))){
			String word = dictionaryBR.readLine();
            while (word != null) {
            	word = word.toLowerCase();
                dictionary.add(word);
            	dictionaryBR.readLine();
            }
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dictionary; 
    }
	
	private HashMap<String, HashSet<String>> readInDictionaries(String[] languages) {
        //for each language, reads each word from a file of that name into a HashSet. String language is mapped to its HashSet dictionary
        HashMap<String, HashSet<String>> languageToDictionary = new HashMap<String, HashSet<String>>();
        
        for (String language : languages) {
            HashSet<String> dictionaryHS = readDictionary(language);
            languageToDictionary.put(language, dictionaryHS);
        }
        return languageToDictionary;
    }
	
	private int getNumberRealWords(String message, HashSet<String> dictionary) {
        //returns an int of how many words in the message matched words in the dictionary
        int realWords = 0;
        for (String word : message.split("\\W+")) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                realWords++;
            }
        }
        return realWords;
    }
	
	private String breakForLanguage(String encryptedMessage, String language, char mostCommonLetter) {
		System.out.println("inside breakForLanguage");
		//returns the message which has been decrypted without providing the key
        //cipher is broken by testing all possible keyLenths and determining which creates a message with the most real words
        int mostRealWords = 0;
        String messageWithMostRealWords = "";
        for (int i=1; i<8; i++) {  //testing for each possible keyLenth - my program has limited this to 7 
        	System.out.println("bFL: i=" + i);
        	int[] possibleKey = tryKeyLength(encryptedMessage, i, mostCommonLetter);  
        	System.out.println("tKL complete. In bFL: keyLength=" + possibleKey);
            VigenereCipher vc = new VigenereCipher(possibleKey);
            System.out.println("just made a vc: " + vc);
            String possibleDecrypedMessage = vc.decryptMessage(encryptedMessage);
            System.out.println("just made a possibleDecrypedMessage=" + possibleDecrypedMessage);
            //ClassPathResource resource = new ClassPathResource("com/thesis/work/raw_sentences.txt");
            int realWords = getNumberRealWords(possibleDecrypedMessage, readDictionary(language));
            System.out.println("realWords=" + realWords);
            if (realWords > mostRealWords) {
                mostRealWords = realWords;
                messageWithMostRealWords = possibleDecrypedMessage;
            }
            System.out.println("end of for loop iteration");
        }
        return messageWithMostRealWords;
    }
	
	private String breakForAllLanguages(String encryptedMessage, HashMap<String, HashSet<String>> languageToDictionary) {
        //returns the message which has been decrypted without providing the key or original language
        //cipher is broken by testing all possible keyLenths and languages and determining which creates a message with the most real words
        int mostRealWords = 0;
        String messageWithMostRealWords = "";
        for (String language : languageToDictionary.keySet()) {
            HashSet<String> dictionary = languageToDictionary.get(language);
            char mostCommonLetter = getMostCommonLetter(dictionary);
            String possibleDecrypedMessage = breakForLanguage(encryptedMessage, language, mostCommonLetter);
            int realWords = getNumberRealWords(possibleDecrypedMessage, dictionary);
            if (realWords > mostRealWords) {
                mostRealWords = realWords;
                messageWithMostRealWords = possibleDecrypedMessage;
            }
        }
        return messageWithMostRealWords;
    }
	
	private char getMostCommonLetter(HashSet<String> dictionary) {
        //returns the mostCommonLetter for a language by reading its' dictionary
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (String word : dictionary) {
            for(int k=0; k < word.length(); k++){
                char letter = word.charAt(k);
                int index = alph.indexOf(letter);
                if (index != -1){
                    counts[index] += 1;
                }
            }
        }
        CaesarBreaker cb = new CaesarBreaker();
        int maxIndex = cb.getHighestIndex(counts);
        char letter = alph.charAt(maxIndex);
        return letter;
    }
	
	public String breakVigenereCipher(String encryptedMessage) {
        //breaks/cracks the cipher and prints out the decrypted message - can modify the code below to make it run for either a known or unknown language
        
		//use this if the language decrypted message will be NOT known (upload dictionaries of any language you think it might be in, and add the language name to this string)
//        String[] languages = {"English", "Danish", "Dutch", "French", "German", "Italian", "Portuguese", "Spanish"};
//        HashMap<String, HashSet<String>> languageToDictionary = readInDictionaries(languages);
//        String decryptedMessage = breakForAllLanguages(encryptedMessage, languageToDictionary);
        
		System.out.println("breakVigenereCipher has been called... inside it now");
        //use this if the language decrypted message will be in is known
        String language = "English";
        char mostCommonLetter = 'e';
        System.out.println("about to call break for language with: " + encryptedMessage + "\n" + language + "\n" + mostCommonLetter);
        String decryptedMessage = breakForLanguage(encryptedMessage, language, mostCommonLetter);
        
        return decryptedMessage;
    }
}