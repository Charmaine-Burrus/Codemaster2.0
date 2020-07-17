package com.burrus.Codemaster20.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.burrus.Codemaster20.model.codemaking.CaesarCipher;
import com.burrus.Codemaster20.model.codemaking.VigenereCipher;

@Controller
public class AppController {
	
	@GetMapping({"/","home"}) //could add Model as parameter
	public String homepage() {			
		return "index";		
	}
	
	@GetMapping({"codemaking"}) //could add Model as parameter
	public String codemaking() {			
		return "codemaking";		
	}
	
	@GetMapping({"codebreaking"}) //could add Model as parameter
	public String codebreaking() {			
		return "codebreaking";		
	}
	
	@GetMapping({"learnMore"}) //could add Model as parameter
	public String learnMore() {			
		return "learnMore";		
	}
	
	@PostMapping("codemaking")
	String codemaking(@RequestParam String typeOfCipher,
			@RequestParam String message, Model model) {

		StringBuilder encryptedMessage = new StringBuilder();
		int[] keyArray = new int[1];
		
		switch(typeOfCipher) {
		
		case("CaesarCipher"):
		    keyArray[0] = ThreadLocalRandom.current().nextInt(1, 26);
			CaesarCipher cc = new CaesarCipher(keyArray[0]);
			encryptedMessage.append(cc.encryptMessage(message));
			break;
		
		case("VigenereCipher"):
			//get random keyLength (2-7)
			int keyLength = ThreadLocalRandom.current().nextInt(2, 8);
			keyArray = new int[keyLength];
			for (int i=0; i<keyArray.length; i++) {
				int currValue = ThreadLocalRandom.current().nextInt(1, 27);
				keyArray[i] = currValue;
			}
			VigenereCipher vc = new VigenereCipher(keyArray);
			encryptedMessage.append(vc.encryptMessage(message));
			
			//for displaying key to users
			StringBuilder keyString = new StringBuilder();
			String alphaString = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for (int i=0; i<keyArray.length; i++) {
				keyString.append(alphaString.charAt(keyArray[i]));
			}
			model.addAttribute("keyString", keyString.toString());
			break;	
		}
		
		model.addAttribute("encryptedMessage", encryptedMessage);
		model.addAttribute("keyArray", keyArray);
		
		return "codemaking";
	}
	
	@PostMapping("codebreaking")
	String codebreaking(@RequestParam String typeOfCipher, @RequestParam String key,
			@RequestParam String message, Model model) {
		System.out.println("In Post");
		StringBuilder decryptedMessage = new StringBuilder();
		
		switch(typeOfCipher) {
		
		case("CaesarCipher"):
			System.out.println("In CC");
			int intKey = Integer.parseInt(key);
			CaesarCipher cc = new CaesarCipher(intKey);
			decryptedMessage.append(cc.decryptMessage(message));
			break;
		
		case("VigenereCipher"):
			System.out.println("In VC");
			int[] keyArray = new int[key.length()];
			String alphaString = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			key = key.toUpperCase();
			for (int i=0; i<key.length(); i++) {
				keyArray[i] = alphaString.indexOf(key.charAt(i));
			}
			
			VigenereCipher vc = new VigenereCipher(keyArray);
			decryptedMessage.append(vc.decryptMessage(message));
			break;
		}
		
		model.addAttribute("decryptedMessage", decryptedMessage.toString());
		
		return "codebreaking";
	}

}
