<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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

    <link href="../generalCSS.css" rel="stylesheet" />
    <link href="../Accueil.css" rel="stylesheet" />
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
      <div class="row annonce">
        <div class="col-sm-6">
        <a href="" > <!--ne pas oublier de mettre le lien vers la page "Annonce" plus tard-->
          <img
            id="thumbnail"
            class="center-block"
            src="https://www.maisonsudouest.com/wp-content/uploads/2020/08/vue.jpg?resolution=1920,1"
          />
          </a>
        </div>
        <div class="col-sm-6">
          <div id="titre" class="text-center">
           <a  href="" class="lienTitre"> <!--ne pas oublier de mettre le lien vers la page "Annonce" plus tard-->
            Maison située dans le sud de la France près de Biarritz
           </a>
          </div>
          <div id="description" class="text-justify">
            En plein centre de Biarritz, non loin de la plage du Port Vieux,
            somptueux appartement de 220m2 avec une belle vue océan. Idéalement
            située, pour tout faire à pied, l'appartement dispose aussi d' un
            parking. Séjour très lumineux avec une belle hauteur de plafond.
            Belle vue sur le Port des Pêcheurs, l'Eglise Sainte Eugénie et
            l'océan. Cuisine américaine très bien équipée,
          </div>
        </div>
      </div>
      <div class="row annonce">
        <div class="col-sm-6">
         <a  href="" > <!--ne pas oublier de mettre le lien vers la page "Annonce" plus tard-->
          <img
            id="thumbnail"
            class="center-block"
            src="https://www.tendanceouest.com/photos/maxi/47243.jpg"
          />
          </a>
        </div>
        <div class="col-sm-6">
          <div id="titre" class="text-center"> 
          <a href="" class="lienTitre"> <!--ne pas oublier de mettre le lien vers la page "Annonce" plus tard-->
          Maison située à Rouen
          </a>
          </div>
          <div id="description" class="text-justify">
            Il s’agit de la demeure de Ferdinand Marrou, célèbre ferronnier
            d’art installé en Normandie en 1870, qui exerça son savoir-faire
            dans de nombreux chantiers d’envergure à Fécamp ou à Rouen. En 1890,
            les Rouennais découvre sa nouvelle maison : une effusion de bois
            sculpté et de fer forgé reprenant le style des maisons bourgeoises
            du XVIe siècle.
          </div>
        </div>
      </div>
    </div>
    <footer></footer>
  </body>
</html>
