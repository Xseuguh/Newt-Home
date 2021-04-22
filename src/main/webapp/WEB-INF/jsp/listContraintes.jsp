<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Liste Contraintes</title>
</head>
<body>
<div>
<form action="/contraintes/search" method="post">
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
<th>REF</th>
<th>Name</th>
</tr>
<c:forEach items="${contraintes}" var="c">
<tr>
<td>${c.id_contrainte}</td>
<td>${c.nom_contrainte}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>