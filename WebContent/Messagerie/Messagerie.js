//Gestion de l'affichage de la conversation sélectionnée
$(".onglet").click((e) => {
  Object.values($(".onglet")).forEach((conv) => (conv.id = ""));
  e.target.id = "selectionne";

  const id = parseInt(e.target.value);
  afficheConversation(id);

  const expediteurName = e.target.textContent;
  $("#titre").html(
    `requête ajax pour l\'id ${id} afin d'avoir la conversation avec ${expediteurName}`
  );

  scrollSurPlusRecent();
});

function scrollSurPlusRecent() {
  const listeDesMessages = $("#historiqueMessages");
  listeDesMessages.scrollTop(listeDesMessages.prop("scrollHeight"));
}

//Gestion de la récupération de l'historique des messages pour la conversation sélectionnée
function afficheConversation(conversationID) {
  const historiqueDesMessages = $("#historiqueMessages");

  //on affiche les div
  historiqueDesMessages.css("display", "block");
  $("#nouveauMessageForm").css("display", "flex");

  //on efface l'historique précédent
  historiqueDesMessages.children().remove();

  const messages = getMessages(conversationID);
  messages.forEach((message) => {
    console.log(message.source);
    historiqueDesMessages.append(
      `<li class="${message.source}">
            <p class="corps">${message.valeur}</p>
            <p class="horodatage">${message.horodatage}</p>
        </li>`
    );
  });
}

function getMessages(conversationID) {
  const tempConversations = [
    [
      { source: "autre", valeur: "Salut", horodatage: "03/04/21 - 13h02" },
      {
        source: "autre",
        valeur: "Comment ça va ?",
        horodatage: "03/04/21 - 13h03",
      },
      {
        source: "moi",
        valeur: "Salut Titi, bien et toi ?",
        horodatage: "03/04/21 - 13h04",
      },
      {
        source: "autre",
        valeur: "Bien, merci. <br/>Tu fais quoi de beau ?",
        horodatage: "03/04/21 - 13h05",
      },
    ],
    [
      { source: "autre", valeur: "Salut", horodatage: "03/04/21 - 13h02" },
      {
        source: "autre",
        valeur: "Comment ça va ?",
        horodatage: "03/04/21 - 13h03",
      },
      {
        source: "moi",
        valeur: "Salut Toto, bien et toi ?",
        horodatage: "03/04/21 - 13h04",
      },
      {
        source: "autre",
        valeur: "Bien, merci. <br/>Tu fais quoi de beau ?",
        horodatage: "03/04/21 - 13h05",
      },
      {
        source: "moi",
        valeur:
          "J'écris un texte long, d'une longueur inégalé et inégalable, sans aucun retour à la ligne (que j'aurais pu utiliser grâce à la balise br) et j'en profite également pour revoir les fonctions de css. C'est sympa non ?",
        horodatage: "03/04/21 - 13h04",
      },
      { source: "autre", valeur: "Salut", horodatage: "03/04/21 - 13h02" },
      {
        source: "autre",
        valeur: "Comment ça va ?",
        horodatage: "03/04/21 - 13h03",
      },
      {
        source: "moi",
        valeur: "Salut Toto, bien et toi ?",
        horodatage: "03/04/21 - 13h04",
      },
      {
        source: "autre",
        valeur: "Bien, merci. <br/>Tu fais quoi de beau ?",
        horodatage: "03/04/21 - 13h05",
      },
      {
        source: "moi",
        valeur:
          "J'écris un texte long, d'une longueur inégalé et inégalable, sans aucun retour à la ligne (que j'aurais pu utiliser grâce à la balise br) et j'en profite également pour revoir les fonctions de css. C'est sympa non ?",
        horodatage: "03/04/21 - 13h04",
      },
      { source: "autre", valeur: "Salut", horodatage: "03/04/21 - 13h02" },
      {
        source: "autre",
        valeur: "Comment ça va ?",
        horodatage: "03/04/21 - 13h03",
      },
      {
        source: "moi",
        valeur: "Salut Toto, bien et toi ?",
        horodatage: "03/04/21 - 13h04",
      },
      {
        source: "autre",
        valeur: "Bien, merci. <br/>Tu fais quoi de beau ?",
        horodatage: "03/04/21 - 13h05",
      },
      {
        source: "moi",
        valeur:
          "J'écris un texte long, d'une longueur inégalé et inégalable, sans aucun retour à la ligne (que j'aurais pu utiliser grâce à la balise br) et j'en profite également pour revoir les fonctions de css. C'est sympa non ?",
        horodatage: "03/04/21 - 13h04",
      },
    ],
  ];
  return tempConversations[conversationID] || [];
}

//Gestion de l'envoie d'un nouveau message dans la conversation affichée
$("#boutonEnvoi").click(() => {
  console.log("HEHEHE");
});
