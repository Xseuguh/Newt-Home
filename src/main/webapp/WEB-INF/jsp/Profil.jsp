<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@page import = "java.io.File"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1"
    />
    <title>Profil</title>
    <link
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <link
      href="<%=request.getContextPath()%>/css/generalCSS.css"
      rel="stylesheet"
    />
    <link
      href="<%=request.getContextPath()%>/css/Profil.css"
      rel="stylesheet"
    />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
  </head>
  <body>
    <jsp:include page="Header.jsp">
      <jsp:param name="isAuthenticated" value="true" />
    </jsp:include>

    <div id="profil">
      <div id="navbar">
        <button class="options" value="annonces">Mes annonces</button>
        <button class="options" value="reservations">Mes réservations</button>
        <button class="options" value="parametres">Paramètres</button>
      </div>

      <div id="affichage">
        <h1>Bienvenue sur votre page de profil</h1>
      </div>
    </div>

    <%@include file="Footer.jsp" %>

    <script
      src="<%=request.getContextPath()%>/js/Profil.js"
      type="text/javascript"
    ></script>
  </body>
</html>
