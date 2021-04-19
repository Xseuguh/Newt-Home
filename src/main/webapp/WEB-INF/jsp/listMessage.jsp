<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Liste Message</title>
</head>
<body>
<div>
<form action="/messages/search" method="post">
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
<th>Id du sender</th>
<th>Id du recipient</th>
<th>Message </th>
</tr>
<c:forEach items="${message}" var="m">
<tr>
<td>${s.id_msg}</td>
<td>${s.id_sender}</td>
<td>${s.id_recipient}</td>
<td>${s.msg}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>