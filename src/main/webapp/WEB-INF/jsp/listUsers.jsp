<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Liste users</title>
</head>
<body>
<div>
<form action="/users/search" method="post">
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
<th>Nom de l'utilisateur</th>
<th>Prenom de l'utilisateur</th>
<th>Adresse mail</th>
<th>Date de naissance</th>
<th>Admin (0 pour non)</th>
</tr>
<c:forEach items="${users}" var="u">
<tr>
<td>${u.id_user}</td>
<td>${u.nom}</td>
<td>${u.prenom}</td>
<td>${u.mail}</td>
<td>${u.birthday}</td>
<td>${u.admin}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>