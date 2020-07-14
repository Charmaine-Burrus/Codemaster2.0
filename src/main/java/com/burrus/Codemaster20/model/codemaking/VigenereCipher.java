package com.burrus.Codemaster20.model.codemaking;

import java.util.*;

/**
 * This class creates a VigenereCipher object with a single instance variable, an array of CaesarCipher objects created from the key (array of ints)
 * It has public methods to encrypt or decrypt a message(String) using the key that it was created with.
 * 
 * Provisions have been made to preserve original case (upper or lower).
 * 
 * See the readme for more details, including explanation of how a Vingere Cipher works.
 * 
 * @ceb
 * @5-1-20
 */

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i=0; i<key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    public String encryptMessage(String message) {
        //make the message unintelligible to the eye by encrypting each letter based on the key 
        StringBuilder encryptedMessage = new StringBuilder();
        int i = 0;
        for (char character : message.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            encryptedMessage.append(thisCipher.encryptLetter(character));
            i++;
        }
        return encryptedMessage.toString();
    }
    
    public String decryptMessage(String encryptedMessage) {
        //make the message intelligible again by decrypting each letter based on the key 
        StringBuilder decryptedMessage = new StringBuilder();
        int i = 0;
        for (char character : encryptedMessage.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            decryptedMessage.append(thisCipher.decryptLetter(character));
            i++;
        }
        return decryptedMessage.toString();
    }
  
    public String toString() {
        //a Vigenere Cipher is determined by its' key of CaesarCiphers.. this will print the array of keys(one for each CaesarCipher)
        return Arrays.toString(ciphers);
    }
    
}