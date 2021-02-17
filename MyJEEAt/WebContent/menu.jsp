<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Menu</title>
	<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
	<link rel="stylesheet" href="CSS/style.css"/>
</head>
<body>
	<div id="carouselExampleInterval" class="carousel slide carousel-fade" data-ride="carousel">
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="images/arp2.jpg" height="150" class="d-block w-100" alt="oups">
	    </div>
	    <div class="carousel-item active">
	      <img src="images/arp2" height="150" class="d-block w-100" alt="oups">
	    </div>
	    <div class="carousel-item active">
	      <img src="images/arp3" height="150" class="d-block w-100" alt="oups">
	    </div>
	  </div>
</div>





	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="connect.jsp" class="badge badge-primary">Se connecter</a></button>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="inscription.jsp" class="badge badge-primary">S'inscrire</a></button>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="accueil.jsp" class="badge badge-primary">Accueil</a></button>
	<br>
	<br>
	<div class="alert alert-success" role="alert">
	  <h4 class="alert-heading">Bien choisir!</h4>
	  <p>Veuillez choisir s'inscrire si vous n'avez pas de compte.</p>
	  <hr>
	  <p class="mb-0">Si vous avez un compte alors choisir se connecter.</p>
	</div>
</body>
</html>