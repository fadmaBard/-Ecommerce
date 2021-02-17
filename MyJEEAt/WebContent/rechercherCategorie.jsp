<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dwwm.jee.*" %>
<%@ page import="java.text.DateFormat" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gérer les commandes</title>
	<link rel="stylesheet" href="CSS/bootstrap.min.css"/>
	<link rel="stylesheet" href="CSS/style.css"/>
	<link rel="stylesheet" href="CSS/modal.css"/>
</head>
<body>
	<button type="button" class="btn btn-danger"><a href="menuAdmin.jsp" class="badge"><img src="images/retourFlecheVert.jpg" alt="retour" height=50 width=50></a></button>
	<br>
	<br>
	<br>
	<br>
	<%
		Date aujourdhui = new Date();
    	DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
        DateFormat.SHORT,
        DateFormat.SHORT);
	%>	
	<%
		List<LigneCommandeBD> llc = new ArrayList<LigneCommandeBD>();
		UserDAO udao = new UserDAO();
		udao.etablirConnection();
		List<Article> liste = (List<Article>) udao.listerArticle();
		String item;
	%>
	<form action="MyServlet?operation=ajouterCommande" method="post">
		<fieldset>
            <legend>Créer commande</legend>
            <label for="numCommande">Numero commande :</label>
			<input type="text" id="numCommande" name="numCommande" disabled value=${numNouvelleCommande }>
			<br>
			<label for="date">Date :</label>
			<input type="text" id="date" name="date" disabled value="<%=shortDateFormat.format(aujourdhui) %>">
			<br>
			<label for="client">identifiant client :</label>
			<input type="text" id="client" name="client" disabled value=${idUser }>
			<br>
			<br>
			<a href="#open-modal"><input type="button" class="btn btn-primary" value="Ajouter ligne commande" href="#open-modal"></a>
			<a href="menuCommande.jsp"><input type="button" class="btn btn-primary" value="Autre commande"></a>
		
			
        </fieldset> 
	</form>
	
	<form action="MyServlet?operation=ajouterLigneCommande" method="post">
		<div id="open-modal" class="modal-window">
	    	<div>
	        	<a href="#modal-close" title="Close" class="modal-close">close &times;</a>
	        	<h1>Remplir ligne de commande</h1>
	            <div>
	            	<label for="numCommandeForLigne">Numero commande :</label>
					<input type="text" id="numCommandeForLigne" name="numCommandeForLigne" disabled value=${numNouvelleCommande }>
					<br>
					<label for="articleSelected">Article :</label>
					<select name="articleSelected">
					<%
						for (int i=0; i<liste.size(); i++)
						{
					    	item = liste.get(i).getIdArticle() + " -- " + liste.get(i).getDesignation() + " -- " + liste.get(i).getPu() + " -- " + liste.get(i).getQty() ;
					%>
						  <option value="<%=item%>"><%=item%></option>
					<%
						}
					%>
					</select>
					<br>
					<label for="qtyCommandee">Quantité commandée :</label>
					<input type="text" id="qtyCommandee" name="qtyCommandee" value="">
					<br>
					<%
					   String numc = request.getParameter( "numCommande" );
					   session.setAttribute( "numc", numc );
					%>
	            	<input type="submit" class="btn btn-primary" value="Valider ligne commande" >
	    			<a href="#modal-close" title="Close" class="close"><input type="button" class="btn btn-primary" value="Annuler"></a>
	            </div>
	        </div>
	    </div> 
	    
	</form>
	<%
		int numcde = (int) session.getAttribute("numNouvelleCommande");
		llc = (List<LigneCommandeBD>) udao.ligneCommandeParCommande(numcde);
		udao.cloturerConnection();
	%>
	<table class="table zebra-striped ">
    <tr>
            <th>Identifiant article</th>
            <th>Désignation article</th>
            <th>Quantité commandée</th>
    </tr>
     
	<%
		
		for (LigneCommandeBD l : llc)
		{
			if(l != null){
			%>
		    <tr>
		        <td><%=l.getIdArticleBD() %></td>
		        <td><%=l.getDesignationArticleBD() %></td>
		        <td><%=l.getQtyCommandeeBD()%></td>
		    </tr>
    <% }} %>
</table>
</body>
</html>