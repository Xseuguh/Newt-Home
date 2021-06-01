<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Gestion d'annonce</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Admin_interface.css">
<link href="<%=request.getContextPath()%>/css/generalCSS.css"
	rel="stylesheet" />
</head>
<body>
	<script src="https://code.jquery.com/jquery-1.11.2.min.js">
		
	</script>
	
    <jsp:include page="Header.jsp">
      <jsp:param name="isAuthenticated" value="true" />
    </jsp:include>
	<div class="container">
		<form>
			<input type="search" placeholder="Rechercher une annonce...">
			<input type="submit" value="GO">
		</form>
		<br> <br>
		<div class="text-center">
		<table class="table table-striped table-responsive text-center">
			<tr>
				<th scope="col">
					Id Offre
				</th>
				<th scope="col">
					Id Propriétaire
				</th>
				<th scope="col">
					Titre
				</th>
				<th scope="col">
					Description
				</th>
				<th scope="col">
					Adresse
				</th>
				<th scope="col">
					Code Postal
				</th>
				<th scope="col">
					Ville
				</th>
				<th scope="col">
					Pays
				</th>
				<th scope="col">
					Voir plus
				</th>
				<th scope="col">
					Modifier
				</th>
				<th scope="col">
					Supprimer
				</th>
			</tr>
			<c:forEach items="${offres}" var="offreAffiche">
				<tr>
					<td>${offreAffiche.id_offre}</td>
					<td>${offreAffiche.id_user}</td>
					<td>${offreAffiche.titre}</td>
					<td>${offreAffiche.description}</td>
					<td>${offreAffiche.adresse}</td>
					<td>${offreAffiche.code_postal}</td>
					<td>${offreAffiche.ville}</td>
					<td>${offreAffiche.pays}</td>
					<td><a href="/Admin/Zoom/Annonce?ref=${offreAffiche.id_offre}"><img src="<%=request.getContextPath()%>/images/zoom.png" alt="zoom"></a></td>
					<td><a href="/Admin/Modifier/Annonce?ref=${offreAffiche.id_offre}"><img src="<%=request.getContextPath()%>/images/editer.png" alt="editer"></a></td>
					<td><a href="/Admin/Supprimer/Annonce?ref=${offreAffiche.id_offre}" onclick="return confirm('Please Confirm')"><img src="<%=request.getContextPath()%>/images/suppression.png" alt="supprimer"></a></td>
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