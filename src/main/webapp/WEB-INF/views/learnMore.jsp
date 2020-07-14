<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <title>Codemaster</title>
</head>

<body>
  <jsp:include page="components/header.jsp"/>
  
  <div class="container" style="background-color: #e6e0da;">
    <h1>All About Ciphers</h1>
  </div>

  <div class="container" style="background-color: #97c9a2;">
    <h2>*Caesar Cipher</h2>
	<h3>Introduction </h3>
	<p>The Caesar cipher is one of the earliest known and simplest ciphers. 
	It is a type of substitution cipher in which each letter in the plaintext is 'shifted' a certain number of places down the alphabet. 
	For example, with a shift of 1, A would be replaced by B, B would become C, and so on. 
	The method is named after Julius Caesar, who apparently used it to communicate with his generals. 
	More complex encryption schemes such as the Vigenère cipher employ the Caesar cipher as one element of the encryption process. 
	The widely known ROT13 'encryption' is simply a Caesar cipher with an offset of 13. 
	The Caesar cipher offers essentially no communication security, and it will be shown that it can be easily broken even by hand.</p>
	<h3>Example:</h3>
	<p>To pass an encrypted message from one person to another, it is first necessary that both parties have the 'key' for the cipher, 
	so that the sender may encrypt it and the receiver may decrypt it. For the caesar cipher, the key is the number of characters to shift the cipher alphabet.
	Here is a quick example of the encryption and decryption steps involved with the caesar cipher. The text we will encrypt is 'defend the east wall of the castle', with a shift (key) of 1.</p>
	<div style="border-style: solid; border-width: 1px; border-color: #998874;">
	  <p> plaintext:  defend the east wall of the castle</p>
	  <p> ciphertext: efgfoe uif fbtu xbmm pg uif dbtumf</p>
	</div>
	<p> It is easy to see how each character in the plaintext is shifted up the alphabet. Decryption is just as easy, by using an offset of -1.</p>
	<div style="border-style: solid; border-width: 1px; border-color: #998874;">
	  <p> plain:  abcdefghijklmnopqrstuvwxyz</p>
	  <p> cipher: bcdefghijklmnopqrstuvwxyza</p>
	</div>
	<p> Obviously, if a different key is used, the cipher alphabet will be shifted a different amount.</p>
	<p> *This info is directly from this source: http://practicalcryptography.com/ciphers/caesar-cipher/ </p>
  </div>
  
  <div class="container" style="background-color: #998874;">
  <h2>*Vigenère Cipher</h1>
  <h3>Introduction </h3>
  <p>The Vigenère Cipher is a polyalphabetic substitution cipher. 
  The method was originally described by Giovan Battista Bellaso in his 1553 book La cifra del. 
  Sig. Giovan Battista Bellaso; however, the scheme was later misattributed to Blaise de Vigenère in the 19th century, 
  and is now widely known as the Vigenère cipher.</p>
  <p>The 'key' for a vigenere cipher is a key word. e.g. 'FORTIFICATION'</p>
  <p>To encipher a message, repeat the keyword above the plaintext:</p>
  <div style="border-style: solid; border-width: 1px; border-color: #97c9a2;">
	  <p> FORTIFICATIONFORTIFICATIONFO</p>
	  <p> DEFENDTHEEASTWALLOFTHECASTLE</p>
  </div>
  <p>Now we take the letter we will be encoding, 'D', and find it on the first column on the tableau. 
  Then, we move along the 'D' row of the tableau until we come to the column with the 'F' at the top (The 'F' is the keyword letter for the first 'D'), 
  the intersection is our ciphertext character, 'I'.</p>
  <p>So, the ciphertext for the above plaintext is:</p>
  <div style="border-style: solid; border-width: 1px; border-color: #97c9a2;">
	  <p> FORTIFICATIONFORTIFICATIONFO</p>
	  <p> DEFENDTHEEASTWALLOFTHECASTLE</p>
	  <p> ISWXVIBJEXIGGBOCEWKBJEVIGGQS</p>
  </div>
  <p> *This info is directly from this source: http://practicalcryptography.com/ciphers/vigenere-gronsfeld-and-autokey-cipher/ </p>
  </div>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>

</html>