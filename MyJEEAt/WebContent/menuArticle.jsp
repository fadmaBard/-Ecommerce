<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu article</title>
<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
<link rel="stylesheet" href="CSS/style.css"/>
</head>
<body>
	<button type="button" class="btn btn-danger"><a href="menuAdmin.jsp" class="badge"><img src="images/retourFlecheVert.jpg" alt="retour" height=50 width=50></a></button>
	<br>
	<br>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="ajouterArticle.jsp" class="badge badge-primary">Nouveau article</a></button>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block"><a href="gererArticle.jsp" class="badge badge-primary">Gérer les articles</a></button>
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