<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<%@page import="java.io.File"%>
<title>A propos de Newt'Home</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstr
ap.min.css"
	rel="stylesheet" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/generalCSS.css"
	rel="stylesheet" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/Accueil.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="<%=request.getContextPath()%>/js/Accueil.js"></script>
<script id="scriptBTS"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>


</head>
<body>
	
    <jsp:include page="Header.jsp">
      <jsp:param name="isAuthenticated" value="true" />
    </jsp:include>
	<div class="container" >
      <div class="textePagesStatiques">
        <h1>A PROPOS</h1>
      
      <h2>Newt'Home: Pourquoi? Pour qui?</h2>
      <p>
      	Vous rêvez de passer des vacances à la mer? A la montage? 
      	Mais vous craignez pour votre budget. 
      	Rassurez-vous avec Newt'Home, la solution est toute trouvée.
      	Newt'Home est une application de couch surfing sur laquelle vous pouvez
      	louer à l'emplacement de vos rêves la maison de vos rêves.
      	A quel prix? Aucun!  En effet en participant à Newt'Home vous vous engagez
      	à échanger avec quelqu'un d'autre votre maison pendant la durée de votre séjour, il suffit uniquement de repecter des contraintes et de rendre des services.
      </p>
      <h2>Comment devenir membre de la communauté Newt'Home?</h2>
      <p>
      		Pour devenir membre de la communauté Newt'Home rien de plus simple.
      		Rendez-vous sur notre page d'inscription. Complétez votre profil en trois clic.
      		Ca y est c'est fini!
      </p>
      <h2>Comment postuler à une offre?</h2>
      <p>
      		Vous vous êtes décidé à partir dans un endroit précis mais vous ne savez pas comment faire?
      		Ne vous inquiétez surtout pas.
      		Cliquer sur postuler!
      </p>
    </div>
</div>
	<%@include file="Footer.jsp" %>
</body>
</html>
