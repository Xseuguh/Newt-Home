<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1"
    />
    <title>Bienvenue chez Newt'Home</title>

    <link
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstr
ap.min.css"
      rel="stylesheet"
    />
    <link type="text/css" href="<%=request.getContextPath()%>/css/generalCSS.css" rel="stylesheet" />
    <link type="text/css" href="<%=request.getContextPath()%>/css/Accueil.css" rel="stylesheet" />
  </head>
  <body>
    <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap
    .min.js"></script>
    <header></header>
    <div class="container">
      <div class="row " id="rechercheDiv">
        <form class="form-inline">
          <input
            type="search"
            id="recherche"
            name="q"
            placeholder="Rechercher sur le site…"
          />
          <button id="boutonRecherche">Rechercher</button>
        </form>
      </div>
       <c:forEach items="${offres}" var="o">
      <div class="row annonce">
        <div class="col-sm-6">
        <a href="" > <!--ne pas oublier de mettre le lien vers la page "Annonce" plus tard-->
          <img
            id="thumbnail"
            class="center-block"
            src="https://www.maisonsudouest.com/wp-content/uploads/2020/08/vue.jpg?resolution=1920,1" 
          /> <!--ne pas oublier de changer les images-->
          </a>
        </div>
        <div class="col-sm-6">
          <div id="titre" class="text-center">
           <a  href="" class="lienTitre"> <!--ne pas oublier de mettre le lien vers la page "Annonce" plus tard-->
            ${o.titre}
           </a>
          </div>
          <div id="description" class="text-justify">
            ${o.description}
          </div>
          <br>
          <div class="titreCS" >
          Liste des services à rendre : 
          </div>
 
          <c:forEach items="${services}" var="s">
          <c:if test = "${s.id_offre.equals(o.id_offre)}">
          <div id="listeServices" class="text-justify">
           <ul class="listeCS"><li>${s.nom_service}</li>
           </ul>
          </div>
          </c:if>
          </c:forEach>
          <br>
          <div class="titreCS" >
          Liste des contraintes à respecter :
          </div>
          <c:forEach items="${contraintes}" var="c">
          <c:if test="${c.id_offre.equals(o.id_offre)}" >
          <div id="listeContraintes" class="text-justify">
            <ul class="listeCS"> <li>${c.nom_contrainte}</li></ul>
         </div>
         </c:if>
         </c:forEach>
      </div>
      </div>
          </c:forEach>
    </div>
   
    <footer></footer>
  </body>
</html>
