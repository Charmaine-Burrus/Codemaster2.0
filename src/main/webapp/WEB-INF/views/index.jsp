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
  
  <div class="jumbotron">
    <h1 class="display-4">Welcome, Codemaster.</h1>
    <p class="lead">You've made it to the lair. Here you can encrypt or decrypt your most secret messages.</p>
    <hr class="my-4">
    <p>Codes and ciphers have been used for thousands of years to allow private communication between people.</p>
    <a class="btn btn-primary btn-lg" href="learnMore" role="button" style="background-color:  #ab6d20;">Learn more</a>
    <p></p>
    <p>Codemaster allows you to use both Caesar and Vigenère ciphers to encrypt and decrypt your messages.</p>
    <a class="btn btn-primary btn-lg" href="codemaking" role="button" style="background-color:  #1f6933;">Encrypt a Message</a>
    <a class="btn btn-primary btn-lg" href="codebreaking" role="button" style="background-color:  #1f6933;">Decrypt a Message</a>
  </div>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>

</html>