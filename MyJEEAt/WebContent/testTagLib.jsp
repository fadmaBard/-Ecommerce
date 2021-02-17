<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://hasni.fr/tld/Login" prefix="log" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<log:Login action="MyServlet?operation=connect" login="${login}" pwd="${pwd}" />
				
</body>
</html>