<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://hasni.fr/tld/tagLogin" prefix="log" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>S'authentifier</title>
		<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
	</head>
	<body>
        <h1>Login screen</h1>
	
	    <log:LoginForm action="MyServlet?operation=connect" login="${login}" pwd="${pwd}" />
	
        <br/>
        <div class="errorMessage">${errorMessage}</div>
	
	</body>
</html>