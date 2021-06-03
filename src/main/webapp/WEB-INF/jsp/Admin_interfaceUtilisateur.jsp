<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Gestion d'utilisateurs</title>
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
    <div class="tout table-responsive barreRechercheAdmin">
		<form method="GET" action="/Admin/Gestion/UtilisateurPrecis">
			<input type="search" placeholder="Rechercher un utilisateur..." name="recherche">
			<input type="submit" value="GO">
		</form>
		<br><br>
		<div class="text-center ">
		<table class="table text-center tableAffichage">
		<thead>
			<tr>
				<th >
					Id Utilisateur
				</th >
				<th>
					Nom
				</th >
				<th>
					Prenom
				</th >
				<th>
					mail
				</th >
				<th>
					Date de naissance
				</th >
				<th>
					Supprimer
				</th >
			</tr>
			</thead>
			<tbody>
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
			</tbody>
		</table>
		</div>
	</div>

	<%@include file="Footer.jsp" %>
	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script id="scriptBTS"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>