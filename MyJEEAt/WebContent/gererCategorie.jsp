<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://hasni.fr/tld/Login" prefix="log" %>
<%@ page import="java.util.*" %>
<%@ page import="dwwm.jee.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle catégorie</title>
<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
<link rel="stylesheet" href="CSS/style.css"/>
</head>
<body>
	<h1>
		<div class="fixed-top">
		<button type="button" class="btn btn-danger"><a href="menuAdmin.jsp" class="badge"><img src="images/retourFlecheVert.jpg" alt="retour" height=25 width=30></a></button>
		</div>
		Gestion des catégories
	</h1>
	
		<form action="MyServlet?operation=ajoutCat" method="post">
			<fieldset>
                <legend>Ajout catégorie</legend>
				<label for="designation">Désignation :</label>
				<input type="text" id="designation" name="designation" value="">
				<button type="submit" class="btn btn-primary"><a href="#" class="badge"><img src="images/add.jpg" alt="retour" height=30 width=30></a></button>
				<br>
				<div class="container">
	        	<p class="${msgAdd == '' ? 'erreur' : 'succes'}">${msgAddCat}</p>
	        	</div>
			</fieldset>
				
		</form>
		<!--
		<log:Login action="MyServlet?operation=connect" login="${login}" pwd="${pwd}" />
			
		 
		<form action="MyServlet?operation=listerCat" method="post">
			<fieldset>
                <legend>Lister catégorie</legend>
				<label for="designation">Désignation :</label>
				<%
					UserDAO udao = new UserDAO();
					udao.etablirConnection();
					List<Categorie> liste = (List<Categorie>) udao.listerCategorie();
					udao.cloturerConnection();
					String item;
				%>
				 
				<select name="itemSelected">
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
				<button type="submit" class="btn btn-primary"><a href="#" class="badge"><img src="images/listing3.png" alt="retour" height=30 width=30></a></button>
				<br>
			</fieldset>
		</form>
		 -->
		<form action="MyServlet?operation=modifCat" method="post">
			<fieldset>
                <legend>Modifier catégorie</legend>
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
				<button type="submit" class="btn btn-primary"><a href="#" class="badge"><img src="images/modifier1.jpg" alt="retour" height=30 width=30></a></button>
				<br>
				<div class="container">
	        	<p class="${msgModif == '' ? 'erreur' : 'succes'}">${msgModifCat}</p>
	        	</div>
			</fieldset>
		</form>
		<form action="MyServlet?operation=supprimerCat" method="post">
			<fieldset>
                <legend>Supprimer catégorie</legend>
				<label for="designation">Désignation :</label>
				<select name="itemSelectedSupprimer">
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
				<button type="submit" class="btn btn-primary"><a href="#" class="badge"><img src="images/delete1.png" alt="retour" height=30 width=30></a></button>
				<br>
				<div class="container">
	        	<p class="${msgSup == '' ? 'erreur' : 'succes'}">${msgSupCat}</p>
	        	</div>
			</fieldset>
		</form>
</body>
</html>