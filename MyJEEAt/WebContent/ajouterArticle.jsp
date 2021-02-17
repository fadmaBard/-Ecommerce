<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dwwm.jee.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau article</title>
<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
<link rel="stylesheet" href="CSS/style.css"/>
</head>
<body>

	<h1>Ajout de nouveaux articles</h1>
		<%
			UserDAO udao = new UserDAO();
			udao.etablirConnection();
			List<Categorie> liste = (List<Categorie>) udao.listerCategorie();
			udao.cloturerConnection();
			String item;
		%>
		<form action="MyServlet?operation=ajoutArticle" method="post">
			<fieldset>
                <legend>Ajout article</legend>
				<label for="designation">Désignation :</label>
				<input type="text" id="designation" name="designation" value="">
				<br>
				<label for="qty">Quantité :</label>
				<input type="text" id="qty" name="qty" value="">
				<br>
				<label for="pu">Prix unitaire :</label>
				<input type="text" id="pu" name="pu" value="">
				<br>
				<label for="categorie">Catégorie :</label>
				<select name="catSelected">
				<%
					for (int i=0; i<liste.size(); i++)
					{
				    	item= (String) liste.get(i).getDesignation() ;
				%>
					   <option value="<%=item%>"><%=item%></option>
				<%
					}
				%>
				</select>
				<br>
				
			</fieldset>
			<input type="submit" class="btn btn-primary" value="Ajouter">
			<button type="button" class="btn btn-primary"><a href="menuArticle.jsp" class="badge badge-primary">Annuler</a></button>
	<br>
		</form>
		
</body>
</html>