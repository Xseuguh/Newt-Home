<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Newt'Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="Utilisateur_ajouterAnnonce.css">
<link rel="stylesheet" href="href="../Ressources/generalCSS.css">
</head>
<body>
	<header>
		<h1>Newt'Home</h1>
	</header>
	<!-- a mettre dans le header des liens vers
	*pour revenir sur la page d'accueil
	*pour revenir vers la page de profil
	 -->
	<script src="Utilisateur_ajouterAnnonce.js"></script>
	<div class="tout">
		<content class="container">
		<div class="row">
			<div class="col">
				<img id="galerieImage" src="Cheverny_Chateau_1.jpg"> <br><br>
				<img src="flecheG.jpg" class="fleches" onclick="precedent()" id="flecheG"> 
				<img src="flecheD.jpg" class="fleches" onclick="suivant()">
				<br><br>
				<form enctype="multipart/form-data">
					Ajouter une image
					<input type="submit">
				</form>
			</div>
			<div class="col">
				<h2>Mon annonce</h2>
				<form id="form2">
					<label for="titreAnnonce">Titre:</label>
					<input type="text"	id="titreAnnonce" name="titreAnnonce"><br>
					<label	for="prixLocation">Prix (€):</label><input type="number" id="prixLocation" name="prixLocation" min="0"><br>
					<label	for="descriptionAnnonce">Description:</label><br>
					<textarea id="descriptionAnnonce" name="descriptionAnnonce" rows="10" cols="90"></textarea>
					<br>
					<p id="servicesInclus">
						Inclus:<br>
						<input type="checkbox" name="wifi" id="wifi"/>
						<label	for="wifi">Wi-Fi</label>
						<input type="checkbox" name="piscine" id="piscine" />
						<label for="piscine">Piscine</label>
						<input type="checkbox" name="parking" id="parking" />
						<label for="parking">Parking</label>
						<br>
						<input type="checkbox" name="animauxAdmis" id="animauxAdmis" />
						<label for="animauxAdmis">Animaux</label>
						<input	type="checkbox" name="golf" id="golf" />
						<label for="golf">Golf</label>
					</p>
					<label for="campagneOuVille">Environnement:</label> <br> 
					<input type="checkbox" name="campagne" id="campagne" />
					<label for="campagne">Campagne</label><br> 
					<input type="checkbox" name="ville" id="ville" /><label for="ville">Ville</label>
					<br>
					<input type="submit" value="Valider" id="boutonSubmit">
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
		</content>
		<footer>
			<a href="">CGU</a> 
			<a href="">A propos de nous</a>
		</footer>
	</div>
</body>
</html>