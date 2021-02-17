<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
<link rel="stylesheet" href="CSS/style.css"/>
</head>
<body>
	<h1>Bonjour stagiaire DWWM Beauvais</h1>
	<%
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		if(login == null){login = "";}
		if(pwd == null){pwd = "";}
		
		if(request.getMethod().equals("POST") && login.equals("admin") && pwd.equals("admin")){
	%>
		<p>Bienvenue <%=login %></p>
	<%} else { %>
		<form action="MyServlet?operation=connect" method="post">
			<fieldset>
                <legend>Connexion</legend>
				<label for="login">Pseudo :</label>
				<input type="text" id="login" name="login" value="admin">
				<br>
				<label for="pwd">Mot de passe :</label>
				<input type="text" id="pwd" name="pwd" value="admin">
				
			</fieldset>
			<input type="submit" class="btn btn-primary" value="Envoyer">
			<a href="menu.jsp"><input type="button" class="btn btn-primary" value="Annuler"></a>
		</form>
	<% } %>
	
</body>
</html>