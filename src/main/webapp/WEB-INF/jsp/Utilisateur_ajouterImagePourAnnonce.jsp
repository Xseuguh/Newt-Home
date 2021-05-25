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
	<script>
		function raiseConcernWhenDiscard(){
			event.preventDefault()
			var questionResult=confirm("Etes vous sur?");
			if(questionResult==true){
				window.location.href="/annonce/modifierImage";
			}
		}
	</script>
	<div class="tout">
		<h2>Mon annonce 1/2</h2>
		<div class="container">
			<!-- UPLOAD D IMAGE -->
				<%
					String whereFolder=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier\\";
			        File folder = new File(whereFolder);
			        String listOfTheNameOfTheFiles[] = folder.list();
			        if(listOfTheNameOfTheFiles.length==3){
				        for(String element:listOfTheNameOfTheFiles){
				        	request.setAttribute( "element", element);
				    		%>
				    	  	 <div>
				    		   <img src="<%=request.getContextPath()%>/images/photosAnnonces/ReceptionFichier/${element}"/>
				    	 	  ${element}
				    	  	 </div>
				    	<%
				        }
				    	%>
				    	<br>
				    	<div>
				    	<form action="/annonce/modifierImage" method="post">
				    	   	<input type="submit" onclick="raiseConcernWhenDiscard()" value="Modifier les fichiers">
				    	</form>
				    	</div>
				    	<br>				    	
				    	<button onclick="window.location.href='/annonce/ajout'">Continuer</button>
				    <%
			    	}
					else{
					%>
						<!-- upload vide -->
						<div>
							ATTENTION VOUS DEVEZ METTRE NECESSAIREMENT 3 IMAGES<br>
							Format accepte: jpeg, png
							Tailles maximum: 15Mo
						</div>
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