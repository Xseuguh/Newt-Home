<%@ page language="java" contentType="text/html ; charset=utf-8"
pageEncoding="utf-8"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1"
    />
    <title>Messagerie</title>

    <link href="<%=request.getContextPath()%>/css/generalCSS.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/Messagerie.css" rel="stylesheet" />
    <link
      rel="stylesheet"
      media="screen and (max-width: 700px)"
      href="<%=request.getContextPath()%>/css/MessageriePetitEcran.css"
    />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  </head>
  <body>
    <div id="messagerie">
      <div id="conversations">
        <div class="menu">
          <h1>Messagerie</h1>
          <div id="nouvelleConversation">
            <img
              src="<%=request.getContextPath()%>/images/messagerie/nouveauMessage.png"
              width="50em"
              height="50em"
            />
          </div>
        </div>
        <ul id="correspondants">
          <!-- 
              Chaque li sera généré au rechargement de la page, en se basant sur les conversations en bdd
              value = ID dans la bdd, dont on aura besoin pour faire des requêtes ajax si on ouvre la conversation
              On affiche le nom du correspondant
            -->
          <c:forEach items="${conversations}" var="conv">
            <li class="onglet" value=${cv.id_conversation}>
              <img class="rond" src="<%=request.getContextPath()%>/images/user/default.png" width="50em" /> ${conv.prenom}${conv.nom}
          </c:forEach>
        </ul>
      </div>
      <div id="focusConversation">
        <div id="menuConversation">
          <div id="retourMenu">
            <img
              src="<%=request.getContextPath()%>/images/messagerie/flecheGauche.png"
              width="40em"
              height="40em"
            />
          </div>
          <img
            class="rond"
            id="imageTitreConversation"
            src="<%=request.getContextPath()%>/images/messagerie/salutMain.png"
            width="50em"
          />
          <h1 id="titreConversation">Bienvenue sur votre messagerie</h1>
        </div>

        <ul id="historiqueMessages">
          <!-- 
            Cette partie sera chargée via une requête ajax, aprés qu'une conv ait été choisi
           -->
        </ul>
        <form id="nouveauMessageForm">
          <div id="nouveauMessage">
            <textarea
              id="nouveauMessageInput"
              placeholder="Écrivez un message..."
            ></textarea>
            <input
              id="boutonEnvoi"
              type="image"
              src="<%=request.getContextPath()%>/images/messagerie/envoiMessage.png"
            />
          </div>
        </form>
      </div>
    </div>

    <script src="<%=request.getContextPath()%>/js/Messagerie.js" type="text/javascript"></script>
  </body>
</html>
