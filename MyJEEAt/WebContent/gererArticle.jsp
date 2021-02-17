<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dwwm.jee.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer les articles</title>
<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
<link rel="stylesheet" href="CSS/style.css"/>
</head>
<body>
<button type="button" class="btn btn-danger"><a href="menuAdmin.jsp" class="badge"><img src="images/retourFlecheVert.jpg" alt="retour" height=50 width=50></a></button>
	<br>
				<%
					UserDAO udao = new UserDAO();
					udao.etablirConnection();
					List<Article> liste = (List<Article>) udao.listerArticle();
					udao.cloturerConnection();
					String item;
				%>
	<form action="MyServlet?operation=modifArticle" method="post">
		<fieldset>
            <legend>Modifier désignation article</legend>
            <label for="nvxDesignation">Nouvelle désignation :</label>
			<input type="text" id="nvxDesignation" name="nvxDesignation" value="">
			<br>
			<label for="designation">Désignation :</label>
				
			<select name="eltSelected">
			<%
				for (int i=0; i<liste.size(); i++)
				{
			    	item = (String) liste.get(i).getDesignation() ;
			%>
				  <option value="<%=item%>"><%=item%></option>
			<%
				}
			%>
			</select>
			<br>
			<input type="submit" class="btn btn-primary" value="Modifier désignation">
		</fieldset>
	</form>
	<form action="MyServlet?operation=modifQtyArticle" method="post">
		<fieldset>
            <legend>Modifier quantité en stock</legend>
            <label for="qty">Quantité à ajouter :</label>
			<input type="text" id="qty" name="qty" value="">
			<br>
			<label for="designation">Désignation :</label>
				
			<select name="eltSelected">
			<%
				for (int i=0; i<liste.size(); i++)
				{
			    	item = (String) liste.get(i).getDesignation() + "-- " + liste.get(i).getQty();
			%>
				  <option value="<%=item%>"><%=item%></option>
			<%
				}
			%>
			</select>
			<br>
			<input type="submit" class="btn btn-primary" value="Modifier quantité">
		</fieldset>
	</form>
	<form action="MyServlet?operation=modifPuArticle" method="post">
		<fieldset>
            <legend>Modifier prix unitaire article</legend>
            <label for="pu">Nouveau prix :</label>
			<input type="text" id="pu" name="pu" value="">
			<br>
			<label for="designation">Désignation :</label>
				
			<select name="eltSelected">
			<%
				for (int i=0; i<liste.size(); i++)
				{
			    	item = (String) liste.get(i).getDesignation() + "-- " + liste.get(i).getPu();
			%>
				  <option value="<%=item%>"><%=item%></option>
			<%
				}
			%>
			</select>
			<br>
			<input type="submit" class="btn btn-primary" value="Modifier prix">
		</fieldset>
	</form>
	<form action="MyServlet?operation=supprimerArticle" method="post">
		<fieldset>
            <legend>Supprimer article</legend>
            
			<br>
			<label for="designation">Désignation :</label>
				
			<select name="eltSelected">
			<%
				for (int i=0; i<liste.size(); i++)
				{
			    	item = (String) liste.get(i).getDesignation() + "-- " + liste.get(i).getPu() + " -- " + liste.get(i).getQty();
			%>
				  <option value="<%=item%>"><%=item%></option>
			<%
				}
			%>
			</select>
			<br>
			<input type="submit" class="btn btn-primary" value="Supprimer l'article">
		</fieldset>
	</form>
</body>
</html>