package com.burrus.Codemaster20.model.codebreaking;

import com.burrus.Codemaster20.model.codemaking.CaesarCipher;

/**
 * 
 * This class creates a CaesarBreaker object with a single instance variable, mostCommonLetter, which is the character/letter that is most common in that language. (Ex. in English mostCommonLetter is 'e').
 * It has the public method decrypt which is used to decrypt messages of an unknown key by utilizing the CaesarCipher class.
 * 
 * Provisions have been made to preserve original case (upper or lower).
 * Provisions have been made so CaesarBreaker can be used to decrypt messages from any language where the most common letter is known (any langugae with a dictionary).
 * 
 * See the readme for more details including explanation of other public methods.
 * 
 * @ceb
 * @4-26-20
 */

public class CaesarBreaker {
    char mostCommonLetter;
    
    public CaesarBreaker() {
        //'e' is the default as it is the most common letter in many languages
        mostCommonLetter = 'e';
    }
    
    public CaesarBreaker(char mostCommonCharacter) {
        //user can input most common letter of language if it isn't 'e' (there is also a method in Viginere Cipher which can determine this parameter by analyzing a dictionary for that language).
        mostCommonLetter = mostCommonCharacter;
    }

    public int[] countLetterOccurences(String message){
        //creates an array of integers which corresponds to the alphabet and contains counts of how many times each letter occurs in the message
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] letterOccurrences = new int[26];
        for(int i=0; i<message.length(); i++){
            char ch = message.charAt(i);
            int alphaIndex = alpha.indexOf(Character.toLowerCase(ch));
            if (alphaIndex != -1){
                letterOccurrences[alphaIndex] += 1;
            }
        }
        return letterOccurrences;
    }
    
    public int getHighestIndex(int[] letterOccurrences){
        //returns an int which corresponds to the index of the letter with the highest number of occurrences in the message
        //we can determine that the letter at this index should be decrypted to mostCommonLetter
        int indexOfHighest = 0;
        for(int i=0; i<letterOccurrences.length; i++){
            if (letterOccurrences[i] > letterOccurrences[indexOfHighest]){
                indexOfHighest = i;
            }
        }
        return indexOfHighest;
    }

    public int getKey(String encrypted){
        //returns an int which is the reverse of the key which was used to encrypt the message originally (this key can now be used to decrypt the message)
        int[] letterOccurrences = countLetterOccurences(encrypted);
        int indexOfmostCommonLetter = getHighestIndex(letterOccurrences);
        
        int decryptionKey = 0;
        if (indexOfmostCommonLetter < 4) {
            decryptionKey = 26 - (4-indexOfmostCommonLetter);
        }
        else {
            decryptionKey = indexOfmostCommonLetter-4;
        }
        return decryptionKey;
    }
    
    public String decrypt(String encrypted){
        //uses the key to create a new CasesarCipher object which will contain a shifted alphabet made from that key. Returns the message which has been decrypted using that shiftedAlphabet.
        int decryptionKey = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(decryptionKey);
        return cc.decryptMessage(encrypted);
    }
    
    public String toString() {  
        //a Caesar Breaker is determined by its' mostCommonLetter letter so this is the best information to return
        return "" + mostCommonLetter;
    }
   
}