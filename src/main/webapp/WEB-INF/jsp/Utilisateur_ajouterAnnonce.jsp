<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>Ajout d'annonce</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstr
ap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Utilisateur_ajouterAnnonce.css">
<link href="<%=request.getContextPath()%>/css/generalCSS.css"
	rel="stylesheet" />
	
</head>
<body>
	
    <jsp:include page="Header.jsp">
      <jsp:param name="isAuthenticated" value="true" />
    </jsp:include>

	<div class="tout">
		<h2>Mon annonce 2/2</h2>
		<br>
		<div class="container">
			<div class="row">
				<div class="col-sm-6" id="uploadImages">
					<!-- UPLOAD D IMAGE -->
					<%
						String whereFolder = System.getProperty("user.dir")
								+ "\\src\\main\\resources\\static\\images\\photosAnnonces\\ReceptionFichier\\";
						File folder = new File(whereFolder);
						String listOfTheNameOfTheFiles[] = folder.list();
						if (listOfTheNameOfTheFiles.length == 3) {
							for (String element : listOfTheNameOfTheFiles) {
								request.setAttribute("element", element);
					%>
					<div>${element}</div>
					<%
						}
						}
					%>
				</div>
				<div class="col-sm-6">
						<form id="formAjout"  action="/annonce/ajout" method="post">
						<div class="text-center">
							<label for="titreAnnonce">Titre: </label> <br><input
								type="text" id="titreAnnonce" name="titreAnnonce"><br><br>
							<label for="descriptionAnnonce">Description: </label><br>
							<textarea id="descriptionAnnonce" name="descriptionAnnonce"></textarea>
							<br><br> <label for="paysAnnonce">Pays: </label> <br><input
								type="text" id="paysAnnonce" name="paysAnnonce"> <br><br>
							<label for="adresseAnnonce">Adresse: </label><br><input
								type="text" id="adresseAnnonce" name="adresseAnnonce"> <br><br>
							<label for="codePostalAnnonce">Code Postal: </label><br> <input
								type="text" id="codePostalAnnonce" name="codePostalAnnonce">
							<br><br> <label for="villeAnnonce">Ville: </label><br><input
								type="text" id="villeAnnonce" name="villeAnnonce"> <br>
							<br> <label for=date_debut_string>Date debut: </label> <br>
							<input type="date" name="date_debut_string"> <br><br>
							<label for="date_limite_string">Date de fin: </label><br> <input
								type="date" id="date_limite_string" name="date_limite_string">
							<br> <br>
							</div>
							<div>Pour que l'annonce soit accepteée il est demandé de
								mettre: au moins un service et une contrainte</div>
							<p id="contraintes">
								Contraintes:<br> <input type="checkbox"
									name="deuxEnfantsMax" id="deuxEnfantsMax" /> <label
									for="deuxEnfantsMax">2 enfants maximum par logement</label> <br>
								<input type="checkbox" name="animaux" id="animaux" /> <label
									for="animaux">Pas d'animaux </label> <br> <input
									type="checkbox" name="enfants" id="enfants" /> <label
									for="enfants">Pas d'enfants autorisés</label> <br> <input
									type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" />
								<label for="bruitVingtTrois">Pas de bruits après
									23h</label> <br> <input type="checkbox" name="cigarette"
									id="cigarette" /> <label for="cigarette">Pas de
									cigarettes</label> <br> <br>
							</p>
							<p id="services">
								Services:<br> <input type="checkbox" name="arroserPlante"
									id="arroserPlante" /> <label>Arroser les plantes</label> <input
									type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux" />
								<label>Garder les animaux</label> <input type="checkbox"
									name="nettoyerLaMaison" id="nettoyerLaMaison" /> <label>Nettoyer
									la maison</label>
							</p>
							<br>
							<div class="text-center">
								<input type="submit" value="Valider" id="boutonSubmit">
							</div>
						</form>
				</div>

			</div>
		</div>
	</div>
    <%@include file="Footer.jsp" %>
    
	<script src="<%=request.getContextPath()%>/js/Utilisateur_ajouterAnnonce.js"></script>
	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script id="scriptBTS"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	
</body>
</html>