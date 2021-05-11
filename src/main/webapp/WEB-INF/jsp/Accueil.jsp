<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
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
</head>
<body>
	<header></header>
	<div class="container">
		<div class="row rechercheSimple" id="rechercheDiv">
			<form action="/Accueil" method="post" class="form-inline">
				<input type="search" class="recherche" name="motCle" value="${motC}"
					placeholder="Rechercher sur le site…" /> <input
					class="boutonRecherche" type="submit" name="action"
					value="Rechercher">
			</form>
			<br>
			<button class="boutonRecherche" onclick="showRechercheAvancee()">
				Plus de choix de recherche</button>
		</div>
		<div class="row rechercheAvancee" id="rechercheAvanceeDiv">
			<form action="/Accueil/Recherche_Avancee" method="post"
				class="form-inline" id="formAvancee">
				<input type="search" class="recherche" name="motCle" value="${motC}"
					placeholder="Rechercher sur le site…" /> <br> <input
					type="search" class="recherche" name="lieu" value="${lieu}"
					placeholder="Entrer le nom d'une ville, d'un pays..." /> <br>
				<!--  <label for="start">Sélectionner une date de départ :</label> <input
					type="date" class="recherche" name="date" value="${date}" />-->
				<br>
				<fieldset class="form-inline">
					<legend>Affinez votre recherche en sélectionnant des
						contraintes et/ou des services:</legend>
					<c:forEach items="${listeContrainteRecherche}" var="lcr">
						<div class="col-sm-6">
							<input type="checkbox" id="contrainte_${lcr.id_contrainte}"
								name="listeContraintes" value="${lcr.id_contrainte}"> <label
								for="contrainte_${lcr.id_contrainte}">${lcr.nom_contrainte}</label>
						</div>
					</c:forEach>
					<c:forEach items="${listeServiceRecherche}" var="lsr">
						<div class="col-sm-6">
							<input type="checkbox" id="service_${lsr.id_service}"
								name="listeServices" value="${lsr.id_service}"> <label
								for="service_${lsr.id_service}">${lsr.nom_service}</label>
						</div>
					</c:forEach>

				</fieldset>
				<input class="boutonRecherche" type="submit" name="action"
					value="Rechercher">
			</form>
			<button class="boutonRecherche" onclick="hideRechercheAvancee()">Moins
				de choix de recherche</button>
		</div>
		<div id="offres">
			<c:if test="${empty offres}">
				<div id="aucuneOffre">
					Désolé, il n'y a pas d'offres correspondant à votre recherche. <br>
					Veuillez réessayer avec d'autres paramètres.
				</div>
			</c:if>
			<c:forEach items="${offres}" var="o">
				<div class="nonModale" data-toggle="modal"
					data-target="#offre_${o.id_offre}">
					<div class="row annonce">
						<div class="col-sm-6">
							<img id="thumbnail" class="center-block"
								src="<%=request.getContextPath()%>/images/photosAnnonces/annonce_${o.id_offre}/principale.png " />
							<!-- TODO : Faire une fonction ou mettre une condition pour verifier si le fichier existe (et verifier l'extension de l'image)  -->
						</div>
						<div class="col-sm-6">
							<div id="titre" class="text-center">
								<div>${o.titre}</div>
							</div>
							<div id="description" class="text-justify">${o.description}</div>
							<br>
							<div class="titreCS">Liste des services à rendre :</div>

							<c:forEach items="${services}" var="s">
								<c:if test="${s.id_offre.equals(o.id_offre)}">
									<div id="listeServices" class="text-justify">
										<ul class="listeCS">
											<li>${s.nom_service}</li>
										</ul>
									</div>
								</c:if>
							</c:forEach>
							<br>
							<div class="titreCS">Liste des contraintes à respecter :</div>
							<c:forEach items="${contraintes}" var="c">
								<c:if test="${c.id_offre.equals(o.id_offre)}">
									<div id="listeContraintes" class="text-justify">
										<ul class="listeCS">
											<li>${c.nom_contrainte}</li>
										</ul>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<button type="button" class="btn btn-sm pull-right"
							id="openButton">En savoir plus</button>
					</div>
				</div>
				<div class="modal fade" id="offre_${o.id_offre}" role="dialog"
					aria-labelledby="label" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="label">Annonce complète</h4>
							</div>
							<div class="modal-body">
								<div id="titre" class="text-center">
									<div class="lienTitre">${o.titre}</div>
									<img id="thumbnail" class="center-block"
										src="https://www.maisonsudouest.com/wp-content/uploads/2020/08/vue.jpg?resolution=1920,1" />
								</div>
								<br>
								<div class="infosSupp text-justify">
									<img class="icones"
										src="<%=request.getContextPath()%>/images/locationIcon.png" />
									${o.ville}, ${o.pays}
								</div>
								<br>
								<div id="description" class="text-justify">${o.description}</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<div class="titreCS">Services à rendre:</div>
										<c:forEach items="${services}" var="s">
											<c:if test="${s.id_offre.equals(o.id_offre)}">
												<div id="listeServices" class="text-justify">
													<ul class="listeCS">
														<li>${s.nom_service}</li>
													</ul>
												</div>
											</c:if>
										</c:forEach>
									</div>
									<div class="col-md-6">
										<div class="titreCS">Contraintes à respecter:</div>
										<c:forEach items="${contraintes}" var="c">
											<c:if test="${c.id_offre.equals(o.id_offre)}">
												<div id="listeContraintes" class="text-justify">
													<ul class="listeCS">
														<li>${c.nom_contrainte}</li>
													</ul>
												</div>
											</c:if>
										</c:forEach>
									</div>
								</div>

								<div id="infosPropriétaire">
									<c:forEach items="${infosProprio}" var="i">
										<c:if test="${i.id_offre.equals(o.id_offre)}">
											<div class="infosSupp text-justify">
												<img class="icones"
													src="<%=request.getContextPath()%>/images/proprioIcon.png" />
												${i.prenom} ${i.nom}
											</div>
											<br>
											<c:if test="${connected}">
												<div class="row">
													<div class=" col-md-6">
														<form action="/" method="post" class="text-left">
															<!--  TODO : mettre le bon chemin et faire la partie controller -->
															<input type="hidden" id="idOffre" value="${o.id_offre}">
															<input type="hidden" id="idUserConnecte" value="">
															<!--  TODO : mettre la valeur correspondant à l'id du user connecté, afin de rajouter son id dans la table "offres_postulees" -->
															<input type="button" id="formPostuler" value="Postuler">
														</form>
													</div>
													<div class=" col-md-6">
														<form action="/messagerie/" method="post"
															class="text-right">
															<input type="hidden" id="idProprio" value="${i.id_user}">
															<input type="image" id="formRedirectionMessage"
																src="<%=request.getContextPath()%>/images/Messagerie/envoiMessage.png"
																alt="Envoyer un message au propriétaire">
														</form>
													</div>
												</div>
											</c:if>
										</c:if>
									</c:forEach>
								</div>


							</div>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<footer></footer>
	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/Accueil.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</body>
</html>
