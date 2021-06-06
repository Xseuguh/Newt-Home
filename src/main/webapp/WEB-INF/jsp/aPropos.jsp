<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<%@page import="java.io.File"%>
<title>Bienvenue chez Newt'Home</title>

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
	<div >
      <div>
        <h1>A PROPOS DE NOUS...</h1>
      </div>
      <h2>Newt'Home: Pourquoi? Pour qui?</h2>
      <p>
      	Vous rêvez de passer des vacances à la mer? A la montage? 
      	Mais vous craignez pour votre budget. 
      	Rassurez-vous avec Newt'Home, la solution est toute trouvée.
      	Newt'Home est une application de couch surfing sur laquelle vous pouvez
      	louer à l'emplacement de vos rêves la maison de vos rêves.
      	A quel prix? Aucun!  En effet en participant à Newt'Home vous vous engagez
      	à louer votre maison pendant la durée de votre séjour.
      </p>
      <h2>Comment devenir membre de la communauté Newt'Home?</h2>
      <p>
      		Pour devenir membre de la communauté Newt'Home rien de plus simple.
      		Rendez vous sur notre page d'inscription. Complétez votre profile en trois clic.
      		Ca y est c'est fini!
      </p>
      <h2>Comment postulez à une offre?</h2>
      <p>
      		Vous vous êtes décidé à partir dans un endroit précis mais vous ne savez pas comment faire?
      		Ne vous inquiétez surtout pas.
      		Cliquer sur postuler et entre un message.
      </p>
    </div>

	<%@include file="Footer.jsp" %>
</body>
</html>
