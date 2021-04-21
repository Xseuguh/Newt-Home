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
<form action="/offres_postulées/search" method="post">
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
<th>id offre</th>
<th>id user</th>
<th> accepte </th>

</tr>
<c:forEach items="${offresPostulées}" var="op">
<tr>
<td>${op.id_postula}</td>
<td>${op.id_offre}</td>
<td>${op.id_user}</td>
<td>${op.accepte}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>