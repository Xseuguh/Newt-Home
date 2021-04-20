<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Liste conversations</title>
</head>
<body>
<div>
<form action="/liaisons_services/search" method="post">
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
<th>id service</th>

</tr>
<c:forEach items="${liaisonService}" var="ls">
<tr>
<td>${ls.id_liaison}</td>
<td>${ls.id_offre}</td>
<td>${ls.id_service}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>