<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Gestion d'utilisateurs</title>
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Admin_interface.css">
<link href="<%=request.getContextPath()%>/css/generalCSS.css"
	rel="stylesheet" />-->
</head>
<body>
	<script src="https://code.jquery.com/jquery-1.11.2.min.js">
		
	</script>
	
    <jsp:include page="Header.jsp">
      <jsp:param name="isAuthenticated" value="true" />
    </jsp:include>

	<div class="container">
		<form method="GET" action="/Admin/Gestion/UtilisateurPrecis">
			<input type="search" placeholder="Rechercher un utilisateur..." name="recherche">
			<input type="submit" value="GO">
		</form>
		<br><br>
		<div class="text-center">
		<table class="table table-striped table-responsive text-center">
			<tr>
				<th scope="col">
					Id Utilisateur
				</th >
				<th scope="col">
					Nom
				</th >
				<th scope="col">
					Prenom
				</th >
				<th scope="col">
					mail
				</th >
				<th scope="col">
					Date de naissance
				</th >
				<th scope="col">
					Supprimer
				</th >
			</tr>
			<c:forEach items="${listeUtilisateurs}" var="utilisateurAffiche">
				<tr>
					<td>${utilisateurAffiche.id_user}</td>
					<td>${utilisateurAffiche.nom}</td>
					<td>${utilisateurAffiche.prenom}</td>
					<td>${utilisateurAffiche.mail}</td>
					<td>${utilisateurAffiche.birthday}</td>
					<td><a href="/Admin/Supprimer/Utilisateur?ref=${utilisateurAffiche.id_user}" onclick="return confirm('Attention cette action sera irreversible!')"><img src="<%=request.getContextPath()%>/images/suppression.png" alt="supprimer"></a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	<br>
	<br>

	<%@include file="Footer.jsp" %>
</body>
</html>