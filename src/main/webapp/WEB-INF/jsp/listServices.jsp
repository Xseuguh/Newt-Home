<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Liste Services</title>
</head>
<body>
<div>
<form action="/services/search" method="post">
<table>
<tr>
<td>Mot Clé:</td>
<td><input type="text" name="motCle" value="${motC}"/></td>
<td><input type="submit" name="action" value="search" /></td>
</tr>
</table> 
</form>
<table class="tabcontraintes">
<tr>
<th>ref</th>
<th>Nom du service</th>
</tr>
<c:forEach items="${services}" var="s">
<tr>
<td>${s.id_service}</td>
<td>${s.nom_service}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>