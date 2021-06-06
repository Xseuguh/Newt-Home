<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Newt'Home</title>
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
	 
    <jsp:include page="Header.jsp" />

	<div class="container">
		<div class="textePagesStatiques">
			<h1>Mentions l�gales</h1>
			<div>
				<p>
				En tant que consommateur, vous avez des droits que vous pouvez faire valoir.
				</p>
				
				
				<h2>1-Louer un h�bergement.</h2><br>
				
				<p>
				Un h�te se r�serve le droit d annuler une r�servation jusqu � 24heures en avance.
				Si vous restez apr�s l'heure de d�part, l'H�te peut se r�serve le droit de vous faire partir de mani�re conforme au droit applicable.
				Vous vous engagez � tenir des contraintes impos�es par la location: respecter le nombre maximum de voyageurs, arroser les plantes...
				</p>
				
				<h2>2-Ch�que de caution</h2>
				
				<p>				
				L'h�te se r�serve le droit en cas de degradation des lieux d encaisser le cheque de caution.
				</p>
				
				<h2>3-Responsabilit�</h2>
				
				<p>
				Vous  �tes responsables de vos actes. Vous �tes responsables des personnes que vous invitez sur les lieux.
				En tant que locataire nous vous demandons de respecter les lieux: vous vous devez de les rendre dans le m�me �tat que vous les avez trouv�.
				</p>
				
				<h2>4-R�gles</h2>
				
				<p>
				Nous vous incitons � adopter les comportements suivants:
					<ul>
						<li>Agissez avec integrit�</li>
						<li>Soyez bienveillant</li>
						<li>Respectez le mat�riel</li>
					</ul>
				</p>
				
				<h2>5-Probl�mes</h2>
				
				<p>
				En cas de probl�mes, de descriptif erron�, Newt'Home ne pourra �tre tenu en tant que responsable.
				</p>
				
				<h2>6-Gestion de votre Annonce.</h2>
				
				<p>
				Newt'Home fournit des outils qui permettent de configurer et de g�rer facilement une Annonce.
				L'Annonce doit inclure des informations compl�tes : sur le nombre de chambre, l'emplacement...
				</p>
				
				<h2>Cookies</h2>
				
				<p>
				Afin de vous proposez des offres qui vous correspondent mieux nous sommes susceptibles d'utiliser des cookies
				</p>
				<br>
				<p>Nous vous remercions de votre utilisation de Newt'Home!</p>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>
