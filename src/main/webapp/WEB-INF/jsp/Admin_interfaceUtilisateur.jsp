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
	
    <jsp:include page="Header.jsp">
      <jsp:param name="isAuthenticated" value="true" />
    </jsp:include>

	<content>
	<div class="container">
		<form>
			<input type="search" placeholder="Rechercher un utilisateur...">
			<input type="submit" value="GO">
		</form>

		<h2>Resultats</h2>
		<br><br>
		<table>
			<tr>
				<td>
					Id Utilisateur
				</td>
				<td>
					Nom
				</td>
				<td>
					Prenom
				</td>
				<td>
					mail
				</td>
				<td>
					Date de naissance
				</td>
				<td>
					Supprimer
				</td>
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
	</content>

	<%@include file="Footer.jsp" %>
</body>
</html>