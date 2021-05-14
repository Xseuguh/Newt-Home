<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1"
 />
<title>Ajout d'annonce</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Utilisateur_ajouterAnnonce.css">
<link href="<%=request.getContextPath()%>/css/generalCSS.css" rel="stylesheet" />
</head>
<body>
	<header>
		<h1>Newt'Home</h1>
	</header>
	<script src="<%=request.getContextPath()%>/js/Utilisateur_ajouterAnnonce.js"></script>
	<div class="tout">
		<h2>Mon annonce 1/2</h2>
		
		<div class="container">
			

			<!-- UPLOAD D IMAGE -->
				<%
				String where=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier\\";
		        File repertoire = new File(where);
		        String liste[] = repertoire.list();
		        ArrayList<String> myList=new ArrayList<String>();
		        for(String elt:liste){
			        myList.add(elt);
		        }
				if(!(myList.isEmpty())){
			       for(String elt:myList){
			    	   request.setAttribute( "tmp", elt );
			    	%>
			    	   <div>
			    	   <img src="<%=request.getContextPath()%>/images/photosAnnonces/ReceptionFichier/${tmp}"/>
			    	   ${tmp}
			    	   </div>
			    	   <%
			        }
		    	   %>
		    	   <br>
		    	   <div>
		    	   <form action="/updateAll" method="post">
		    	   		<input type="submit" value="Modifier les fichiers">
		    	   </form>
		    	   </div>
		    	   <br>
		    	   <button onclick="window.location.href='/annonce/ajout'">Continuer</button>
		    	   <%
			    }
				else{
				%>
				<form action="/annonce/ajoutImage" method="post" enctype="multipart/form-data">		
			   		<input type="file" name="files" multiple>
			   		<input type="submit" value="Upload Files">
		   	   </form>
			   <%
			   }
			   %>
	</div>
</body>
</html>