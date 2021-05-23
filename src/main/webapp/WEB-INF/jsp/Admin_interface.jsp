<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Newt'Home</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_interface.css">
<link href="<%=request.getContextPath()%>/css/generalCSS.css" rel="stylesheet" />
</head>
<body>
    <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<header>
		<h1>Newt'Home</h1>
	</header>
	<content>
		<button onclick="window.location.href='/Admin/Gestion/Annonce'">Annonce</button>
		<button onclick="window.location.href='/Admin/Gestion/Utilisateur'">Utilisateur</button>
	</content>
	<br>
	<br>
	<footer>
	</footer>
</body>
</html>