<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Gestion administrateur</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstr
ap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Admin_interface.css">
<link href="<%=request.getContextPath()%>/css/generalCSS.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="Header.jsp">
      <jsp:param name="isAuthenticated" value="true" />
    </jsp:include>
    <div class="tout">
	<h1>Gestion des annonces et des utilisateurs</h1>
	<h2>Cliquez sur ce que vous voulez gérer.</h2>
	<div class="container">
		<div class="col-sm-12 text-center">
			<div class="btn-group ">
				<button onclick="window.location.href='/Admin/Gestion/Annonce'"
					id="boutonAdminG">Annonce</button>
				<button onclick="window.location.href='/Admin/Gestion/Utilisateur'"
					id="boutonAdminD">Utilisateur</button>
			</div>
		</div>
	</div>
	</div>
	<%@include file="Footer.jsp" %>
	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script id="scriptBTS"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
