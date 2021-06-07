<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Conditins générales d'utilisation</title>
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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp">
<jsp:param name="isAuthenticated" value="true" />
</jsp:include>
<div class="container">
<div class="textePagesStatiques">
<h1>Mentions légales</h1>
<div>
<p>
En tant que consommateur, vous avez des droits que vous pouvez faire valoir.
</p>


<h2>1-Louer un hébergement.</h2><br>

<p>
Un hôte se réserve le droit d annuler une réservation jusqu à 24heures en avance.
Si vous restez après l'heure de départ, l'Hôte peut se réserve le droit de vous faire partir de manière conforme au droit applicable.
Vous vous engagez à tenir des contraintes imposées par la location: respecter le nombre maximum de voyageurs, arroser les plantes...
</p>

<h2>2-Chèque de caution</h2>

<p>
L'hôte se réserve le droit en cas de degradation des lieux d encaisser le cheque de caution.
</p>

<h2>3-Responsabilité</h2>

<p>
Vous êtes responsables de vos actes. Vous êtes responsables des personnes que vous invitez sur les lieux.
En tant que locataire nous vous demandons de respecter les lieux: vous vous devez de les rendre dans le même état que vous les avez trouvé.
</p>

<h2>4-Règles</h2>

<p>
Nous vous incitons à adopter les comportements suivants:
<ul>
<li>Agissez avec integrité</li>
<li>Soyez bienveillant</li>
<li>Respectez le matériel</li>
</ul>
</p>

<h2>5-Problèmes</h2>

<p>
En cas de problèmes, de descriptif erroné, Newt'Home ne pourra être tenu en tant que responsable.
</p>

<h2>6-Gestion de votre Annonce.</h2>

<p>
Newt'Home fournit des outils qui permettent de configurer et de gérer facilement une Annonce.
L'Annonce doit inclure des informations complètes : sur le nombre de chambre, l'emplacement...
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
<%@include file="Footer.jsp" %>
</body>
</html>
