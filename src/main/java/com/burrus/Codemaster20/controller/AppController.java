package com.burrus.Codemaster20.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.burrus.Codemaster20.model.codebreaking.CaesarBreaker;
import com.burrus.Codemaster20.model.codebreaking.VigenereBreaker;

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
	
	@PostMapping("codebreaking")
	String codebreaking(@RequestParam String typeOfCipher,
			@RequestParam String message, Model model) {
		StringBuilder decryptedMessage = new StringBuilder();
		
		switch(typeOfCipher) {
		
		case("CaesarCipher"):
			CaesarBreaker cb = new CaesarBreaker();
			decryptedMessage.append(cb.decrypt(message));
			break;
		
		case("VigenereCipher"):
			VigenereBreaker vb = new VigenereBreaker();
			try {
				decryptedMessage.append(vb.breakVigenereCipher(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		
		model.addAttribute("decryptedMessage", decryptedMessage.toString());
		
		return "codebreaking";
	}

}
