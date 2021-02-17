<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu admin</title>
<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
<link rel="stylesheet" href="JS/bootstrap.js"/>
<link rel="stylesheet" href="CSS/style.css"/>
</head>
<body>
<h1>Bravo connexion réussie ${pseudo}</h1>


	
<br>
	<br>
	<br>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="menuCategorie.jsp" class="badge badge-primary">Catégorie</a></button>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="menuArticle.jsp" class="badge badge-primary">Article</a></button>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="menuCommande.jsp" class="badge badge-primary">Commande</a></button>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="connect.jsp" class="badge badge-primary">Se reconnecter</a></button>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="accueil.jsp" class="badge badge-primary">Annuler</a></button>
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