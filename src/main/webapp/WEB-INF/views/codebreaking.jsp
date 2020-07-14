<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <title>Codebreaking</title>
</head>

<body>
  <jsp:include page="components/header.jsp"/>
  
  <div class="container">
  	<h1>Let's break this code!</h1>
  	<p></p>
  	<h4>Choose a type of Cipher and type the unknown message. Be aware that the codebreaker works best on messages of at least 2 sentences long. Shorter messages may be decrypted inaccurately.
  	  <a class="btn btn-primary btn-sm" href="learnMore.jsp" role="button" style="background-color: #ab6d20;">Learn more</a>
  	</h3>
  	
	<form action="codebreaking" method="post">
	  
	  <div class="form-group"> Type of Cipher 
        <select class="form-control" name="typeOfCipher" id="typeOfCipher">
        <option value="CaesarCipher">Caesar Cipher</option>
        <option value="VigenereCipher">Vigenere Cipher</option>
        </select>
      </div>
      
      <div class="form-group">
        <label for="message">Message</label>
        <input type="text" class="form-control" name="message" id="message" placeholder="Type your message here...">
      </div>
      <button type="submit" class="btn btn-primary btn-sm" style="background-color: #1f6933;">Decrypt</button>
    </form>
    
  </div>
  
  <div class="container">
	Decrypted Message: ${decryptedMessage}
  </div>
  

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>

</html>