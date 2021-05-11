<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!-- a mettre dans le header des liens vers
	*pour revenir sur la page d'accueil
	*pour revenir vers la page de profil
	 -->
	<script src="<%=request.getContextPath()%>/js/Utilisateur_ajouterAnnonce.js"></script>
	<div class="tout">
		<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<img class="center-block" id="galerieImage" src="<%=request.getContextPath()%>/images/Cheverny_Chateau_1.jpg"> <br><br>
				<img src="flecheG.jpg" class="fleches" onclick="precedent()" id="flecheG"> 
				<img src="flecheD.jpg" class="fleches" onclick="suivant()">
				<br><br>
				<!-- <form enctype="multipart/form-data">
					Ajouter une image
					<input type="submit">
				</form>-->
			</div>
			<div class="col-sm-6">
				<h2>Mon annonce</h2>
				<form action="/annonce/ajout" method="post">
					<label for="titreAnnonce">Titre:</label>
					<input type="text"	id="titreAnnonce" name="titreAnnonce"><br>
					<label	for="descriptionAnnonce">Description:</label><br>
					<textarea id="descriptionAnnonce" name="descriptionAnnonce" ></textarea>
					<br>
					<label for="paysAnnonce">Pays:</label>
					<input type="text" id="paysAnnonce" name="paysAnnonce">
					<br>
					<label for="adresseAnnonce">Adresse:</label>
					<input type="text" id="adresseAnnonce" name="adresseAnnonce">
					<br>
					<label for="codePostalAnnonce">Code Postal:</label>
					<input type="text" id="codePostalAnnonce" name="codePostalAnnonce">
					<br>
					<label for="villeAnnonce">Ville:<label>
					<input type="text" id="villeAnnonce" name="villeAnnonce">
					<br>
					<br>
					<label for=date_debut_string>Date debut:<label>
					<input type="date" name="date_debut_string">
					<br>
					<br>
					<label for="date_limite_string">Date de fin:<label>
					<input type="date" id="date_limite_string" name="date_limite_string">
					<br>
					<br>
					<p id="contraintes">
						Contraintes:<br>
						<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
						<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
						<br>
						<input type="checkbox" name="animaux" id="animaux" />
						<label for="animaux">Pas d animaux </label>
						<br>
						<input type="checkbox" name="enfants" id="enfants" />
						<label for="enfants">Pas d enfants autorises</label>
						<br>
						<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" />
						<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
						<br>
						<input type="checkbox" name="cigarette" id="cigarette"/>
						<label for="cigarette">Pas de cigarettes</label>
						<br>
						<br>
					</p>
					<p id="services">
						Services:<br>
						<input type="checkbox" name="arroserPlante" id="arroserPlante"/>
						<label>Arroser les plantes</label>
						<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux"/>
						<label>Garder les animaux</label>
						<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison"/>
						<label>Nettoyer la maison</label>
					</p>
					<br>
					<div class="text-center" >
					<input type="submit" value="Valider" id="boutonSubmit">
					</div>
				</form>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
			</div>
		</div>
		</div>
		<!--  <footer>
			<a href="">CGU</a> 
			<a href="">A propos de nous</a>
		</footer>-->
	</div>
</body>
</html>