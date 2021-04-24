<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Liste offres</title>
</head>
<body>
<div>
<form action="/offres/search" method="post">
<table>
<tr>
<td>Mot Clé:</td>
<td><input type="text" name="motCle" value="${motC}"/></td>
<td><input type="submit" name="action" value="search" /></td>
</tr>
</table> 
</form>
<table class="tabmessage">
<tr>
<th>ref</th>
<th>id utilisateur</th>
<th>titre de l'offre</th>
<th>description de l'offre </th>
<th>date du debut</th>
<th>date limite</th>
<th> pourvu </th>

</tr>
<c:forEach items="${offres}" var="o">
<tr>
<td>${o.id_offre}</td>
<td>${o.id_user}</td>
<td>${o.titre}</td>
<td>${o.description}</td>
<td>${o.date_debut}</td>
<td>${o.date_limite}</td>
<td>${o.pourvu}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>