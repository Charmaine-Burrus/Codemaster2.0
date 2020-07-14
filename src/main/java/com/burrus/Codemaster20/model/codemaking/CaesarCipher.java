package com.burrus.Codemaster20.model.codemaking;

/**
 * This class creates a CaesarCipher object with fields for the alphabet as well as a shiftedAlphabet based on theKey (number of places to shift the alphabet).
 * It has public methods to encrypt or decrypt both letters(char) or full messages(String).
 * 
 * Provisions have been made to preserve original case (upper or lower).
 * 
 * See the readme for more details.
 * 
 * @ceb
 * @4-24-20
 */

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;
    
    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }
    
    private char transformLetter(char originalChar, String alphaFrom, String alphaTo) {
        //transforms only alphabetic characters (those in alphabet field) from alphabet to shiftedAlphabet or visa versa
        int alphaIndex = alphaFrom.indexOf(originalChar);
        if (alphaIndex != -1) {
            return alphaTo.charAt(alphaIndex);
        }
        return originalChar;
    }
    
    public char encryptLetter(char originalChar) {
        //uses transformLetter to change char from alphabet to shiftedAlphabet
        return transformLetter(originalChar, alphabet, shiftedAlphabet);
    }
    
    public char decryptLetter(char originalChar) {
        //uses transformLetter to change char from shiftedAlphabet to alphabet
        return transformLetter(originalChar, shiftedAlphabet, alphabet);
    }
    
    private String transformMessage(String originalMessage, String alphaFrom, String alphaTo){
        //transforms entire message from alphabet to shiftedAlphabet or visa versa
        StringBuilder transformedMessage = new StringBuilder(originalMessage);
        for (int i=0; i<transformedMessage.length(); i++) {
            char originalChar = transformedMessage.charAt(i);
            char transformedChar = transformLetter(originalChar, alphaFrom, alphaTo);
            transformedMessage.setCharAt(i, transformedChar);
        }
        return transformedMessage.toString();
    }
    
    public String encryptMessage(String message) {
        //uses transformMessage to make the message unintelligible to the eye by shifting every letter using shiftedAlphabet
        return transformMessage(message, alphabet, shiftedAlphabet);
    }
    
    public String decryptMessage(String encryptedMessage) {
        //uses transformMessage to make the message intelligible again by shifting every letter back to alphabet
        return transformMessage(encryptedMessage, shiftedAlphabet, alphabet);
    }
    
    public String toString() {
        //a Caesar Cipher is determined by its' key/shift so this is the best information to return
        return "" + theKey;
    }
    
}
