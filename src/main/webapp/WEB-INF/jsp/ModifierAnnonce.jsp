<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ page import="java.io.File"%> 
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.wtg.entities.Offres"%>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_interface.css">
		<link href="<%=request.getContextPath()%>/css/generalCSS.css" rel="stylesheet" />
	</head>
	<body>
		<jsp:include page="Header.jsp" />
			<p>
				Vos images sont actuellements:
				<c:set value="${offresZoom}" var="oz"/>
				<%
				Offres o = (Offres) pageContext.getAttribute("oz");
				String whereFolder = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\images\\photosAnnonces\\"+o.getId_offre();;
	            File folder = new File(whereFolder); 
	            String listOfTheNameOfTheFiles[] = folder.list();
				for(String fileName : listOfTheNameOfTheFiles){
					out.println(fileName);
				}

				%>
			</p>
			<form action="/annonce/modifierMesImages/?ref=${offresZoom.id_offre}" method="post">
				<input type="submit" value="Modifier mes offre" onclick="alert('Attention vous devrez poster necessairement 3 images!')">
			</form>
			<form action="/annonce/uploaderDeNouvellesImages/?ref=${offresZoom.id_offre}" method="post" enctype="multipart/form-data">
				<input type="file" name="files" class="boutonSubmit" multiple />
				<input type="submit" value="Uploader des images" onclick="alert('Attention vous pouvez poster au maximum 3 images!')">
			</form>
		
			<form action="/ads/ValidationDesModifications?ref=${offresZoom.id_offre}" method="post">
			
				<label>Id Offre</label>					
				<span>${offreAffiche.id_offre}</span>
				<br>
				
				<label>Id user</label>
				<span>${offresZoom.id_user}</span>
				</br>
				
				<label for="titreAnnonce">Titre</label>
				<input type="text" id="titreAnnonce" name="titreAnnonce" value="${offresZoom.titre}"/>
				<br>
			
				<label for="descriptionAnnonce">Description</label>
				<input type="text" id="descriptionAnnonce" name="descriptionAnnonce" value="${offresZoom.description}"/>
				<br>
			
				<label for="adresseAnnonce">Adresse</label>
				<input type="text" id="adresseAnnonce" name="adresseAnnonce" value="${offresZoom.adresse}"/>
				<br>
				
				<label for="codePostalAnnonce">Code postal:</label>
				<input type="text" id="codePostalAnnonce" name="codePostalAnnonce" value="${offresZoom.code_postal}"/>
				<br>
					
				<label for="villeAnnonce">Ville</label>
				<input type="text" id="villeAnnonce" name="villeAnnonce" value="${offresZoom.ville}"/>
				<br>
					
				<label for="paysAnnonce">Pays</label>
				<input type="text" id="paysAnonce" name="paysAnnonce" value="${offresZoom.pays}"/>
				<br>
					
				<label for="dateDebutAnnonce">Date de debut</label>
				<input type="date" id="dateDebutAnnonce" name="dateDebutAnnonce" value="${offresZoom.date_debut}"/>
				<br>
					
				<label for="dateFinAnnonce">Date de fin</label>
				<input type="date" id="dateFinAnnonce" name="dateFinAnnonce" value="${offresZoom.date_limite}"/>
				<br>
				
				<c:if test="${offresZoom.pourvu eq false}">
				  <input type="radio" name="pourvuAnnonce" value="oui" id="pourvuAnnonceOui" ><label for="pourvuAnnonceOui">Oui</label>
				  <input type="radio" name="pourvuAnnonce" value="non" id="pourvuAnnonceNon" checked><label for="pourvuAnnonceNon">Non</label>
				</c:if>
				<c:if test="${offresZoom.pourvu eq true}">
				  <input type="radio" name="pourvuAnnonce" value="oui" id="pourvuAnnonceOui" checked><label for="pourvuAnnonceOui">Oui</label>
				  <input type="radio" name="pourvuAnnonce" value="non" id="pourvuAnnonceNon"><label for="pourvuAnnonceNon">Non</label>
				</c:if>
				<br>
				<p id="contraintes">
				
					<!--<c:forEach items="contraintesEnvoyees" var="contraintes">-->
					<c:if test="${nombreDeContrainte eq 0}">
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
					</c:if>
					<!-- -----------------------*-*-*-*-*-*-*----------- -->
					
					<c:if test="${nombreDeContrainte eq 1}">
						Contraintes:<br>
							<c:if test="${contraintesEnvoyees1 eq 1}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" />
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
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 2}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" />
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 3}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" />
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 4}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 5}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" />
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
					</c:if>
					<!-- -----------------------*-*-*-*-*-*-*----------- -->
					<!-- reduit a 10 cas -->
					<!-- 
					Se servir des tables de multiplication
					1*2=2
					1*3=3
					1*4=4
					1*5=5
					 -->
					<c:if test="${nombreDeContrainte eq 2}">
						<!--Table des 1-->
						<c:if test="${contraintesEnvoyees1 eq 2}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" />
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" />
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" />
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 3}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" />
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" />
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 4}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" />
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 5}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants"/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!--Table des 2-->
						<c:if test="${contraintesEnvoyees1 eq 6}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants"/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 8}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 10}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants"/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!-- Table des 3-->
						<c:if test="${contraintesEnvoyees1 eq 12}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" />
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 15}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants"/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" />
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!-- Table des 4-->
						<c:if test="${contraintesEnvoyees1 eq 20}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" />
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!-- Table des 5 non utile-->
					</c:if>
					<!-- -----------------------*-*-*-*-*-*-*----------- -->
					
					
					<c:if test="${nombreDeContrainte eq 3}">
					<!-- similaire au cas precedent nous avons 10 cas grace au tables de multiplications 
					mais cette fois ci ce sont les cas a eviter-->
						<!--Table des 1-->
						<c:if test="${contraintesEnvoyees1 eq 2}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 3}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 4}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" />
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" />
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 5}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!--Table des 2-->
						<c:if test="${contraintesEnvoyees1 eq 6}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 8}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" />
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" />
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 10}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!-- Table des 3-->
						<c:if test="${contraintesEnvoyees1 eq 12}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax"/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants"/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<c:if test="${contraintesEnvoyees1 eq 15}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" />
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!-- Table des 4-->
						<c:if test="${contraintesEnvoyees1 eq 20}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants"/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
						</c:if>
						<!-- Table des 5 non utile-->
					</c:if>
					
					<!-- -----------------------*-*-*-*-*-*-*----------- -->
					<!-- reduit a 5 cas -->
					<!-- -faire la somme totale(15) le cas manquant est le cas obtenu par les differences des 4 autres 4 -->
					<c:if test="${nombreDeContrainte eq 4}">
						Contraintes:<br>
							<c:if test="${contraintesEnvoyees1 eq 1}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette"/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 2}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois"/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 3}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" />
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"checked />
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 4}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux" checked/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
							<c:if test="${contraintesEnvoyees1 eq 5}">
								<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
								<label for="deuxEnfantsMax">2 enfants maximum par logements</label>
								<br>
								<input type="checkbox" name="animaux" id="animaux"/>
								<label for="animaux">Pas d animaux </label>
								<br>
								<input type="checkbox" name="enfants" id="enfants" checked/>
								<label for="enfants">Pas d enfants autorises</label>
								<br>
								<input type="checkbox" name="bruitVingtTrois" id="bruitVingtTrois" checked/>
								<label for="bruitVingtTrois">Pas de bruits apres 23heures</label>
								<br>
								<input type="checkbox" name="cigarette" id="cigarette" checked/>
								<label for="cigarette">Pas de cigarettes</label>
								<br>
							</c:if>
					</c:if>
					<c:if test="${nombreDeContrainte eq 5}">
						Contraintes:<br>
						<input type="checkbox" name="deuxEnfantsMax" id="deuxEnfantsMax" checked/>
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
					</c:if>
					
					<!--</c:forEach>-->
				</p>
				<p id="services">
					<!-- --------------------------- -->
					<c:if test="${nombreDeService eq 0}">
						Services:<br>
						<input type="checkbox" name="arroserPlante" id="arroserPlante"/>
						<label>Arroser les plantes</label>
						<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux"/>
						<label>Garder les animaux</label>
						<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison"/>
						<label>Nettoyer la maison</label>
					</c:if>
					<!-- --------------------------- -->
					<c:if test="${nombreDeService eq 1}">
						<c:if test="${serviceEnvoye1 eq 1}">
							<input type="checkbox" name="arroserPlante" id="arroserPlante" checked/>
							<label>Arroser les plantes</label>
							<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux" />
							<label>Garder les animaux</label>
							<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison"/>
							<label>Nettoyer la maison</label>
						</c:if>
						<c:if test="${serviceEnvoye1 eq 2}">
							<input type="checkbox" name="arroserPlante" id="arroserPlante"/>
							<label>Arroser les plantes</label>
							<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux" checked/>
							<label>Garder les animaux</label>
							<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison"/>
							<label>Nettoyer la maison</label>
						</c:if>
						<c:if test="${serviceEnvoye1 eq 3}">
							<input type="checkbox" name="arroserPlante" id="arroserPlante"/>
							<label>Arroser les plantes</label>
							<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux" />
							<label>Garder les animaux</label>
							<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison" checked/>
							<label>Nettoyer la maison</label>
						</c:if>
					</c:if>
					<!-- --------------------------- -->
					<!-- -somme-les 2 on trouve le cas non utilise -->
					<c:if test="${nombreDeService eq 2}">
						<c:if test="${serviceEnvoye1 eq 1}">
							<input type="checkbox" name="arroserPlante" id="arroserPlante"/>
							<label>Arroser les plantes</label>
							<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux" checked/>
							<label>Garder les animaux</label>
							<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison" checked/>
							<label>Nettoyer la maison</label>
						</c:if>
						<c:if test="${serviceEnvoye1 eq 2}">
							<input type="checkbox" name="arroserPlante" id="arroserPlante" checked/>
							<label>Arroser les plantes</label>
							<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux"/>
							<label>Garder les animaux</label>
							<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison" checked/>
							<label>Nettoyer la maison</label>
						</c:if>
						<c:if test="${serviceEnvoye1 eq 3}">
							<input type="checkbox" name="arroserPlante" id="arroserPlante" checked/>
							<label>Arroser les plantes</label>
							<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux" checked/>
							<label>Garder les animaux</label>
							<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison"/>
							<label>Nettoyer la maison</label>
						</c:if>
					</c:if>
					<!-- --------------------------- -->
					<c:if test="${nombreDeService eq 3}">
						Services:<br>
						<input type="checkbox" name="arroserPlante" id="arroserPlante" checked/>
						<label>Arroser les plantes</label>
						<input type="checkbox" name="garderLesAnimaux" id="garderLesAnimaux" checked/>
						<label>Garder les animaux</label>
						<input type="checkbox" name="nettoyerLaMaison" id="nettoyerLaMaison" checked/>
						<label>Nettoyer la maison</label>
					</c:if>
				</p>
				
				<input type="submit">
				</form>

				<jsp:include page="Footer.jsp" />
	</body>
</html>