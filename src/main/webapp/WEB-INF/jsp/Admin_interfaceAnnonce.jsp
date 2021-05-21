<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Newt'Home</title>
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
	<header>
		<h1>Newt'Home</h1>
	</header>
	<content>
	<div class="container">
		<form>
			<input type="search" placeholder="Rechercher une annonce...">
			<input type="submit" value="GO">
		</form>

		<h2>Resultats</h2>
		<br> <br>
		<table>
			<tr>
				<td>
					Id Offre
				</td>
				<td>
					Id Proprietaire
				</td>
				<td>
					Titre
				</td>
				<td>
					Description
				</td>
				<td>
					Adresse
				</td>
				<td>
					Code Postal
				</td>
				<td>
					Ville
				</td>
				<td>
					Pays
				</td>
				<td>
					Voir plus
				</td>
				<td>
					Corriger
				</td>
				<td>
					Supprimer
				</td>
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
	</content>
	<br>
	<br>
	<footer> </footer>
</body>
</html>