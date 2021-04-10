const taillePetitEcran = 700;
let isOnConversationMenu = true;

//Gestion de l'affichage de la conversation sélectionnée
$(".onglet").click((e) => {
  isOnConversationMenu = false;
  switchSiEcranPlusPetitQue(taillePetitEcran);
  supprimeChampDestinataire();

  retireIDSelectionne();
  e.target.id = "selectionne";

  const id = parseInt(e.target.value);
  afficheConversation(id);

  const expediteurName = e.target.textContent;
  $("#titreConversation").html(expediteurName);

  //Mettre le lien de l'image du profil de l'expéditeur
  $("#imageTitreConversation").attr("src", "temp.png");

  scrollSurPlusRecent();
});

function scrollSurPlusRecent() {
  const listeDesMessages = $("#historiqueMessages");
  listeDesMessages.scrollTop(listeDesMessages.prop("scrollHeight"));
}

//Gestion de la récupération de l'historique des messages pour la conversation sélectionnée
function afficheConversation(conversationID) {
  const historiqueDesMessages = $("#historiqueMessages");

  //on affiche les div pour l'historique des messages et le formulaire d'envoi de nouveau message
  historiqueDesMessages.css("display", "block");
  $("#nouveauMessageForm").css("display", "flex");

  //on efface l'historique précédent
  historiqueDesMessages.children().remove();

  const messages = getMessages(conversationID);
  messages.forEach((message) => {
    historiqueDesMessages.append(
      `<li class="${message.source}">
            <p class="corps">${message.valeur}</p>
            <p class="horodatage">${message.horodatage}</p>
        </li>`
    );
  });
}

//Fonctions qui récupére en ajax les messages d'une conversation
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
$("#nouveauMessageForm").on("submit", (e) => {
  e.preventDefault();

  const input = $("#nouveauMessageInput");
  const message = input.val();

  if (message.length === 0) {
    alert("Le message ne peut être vide !");
    return;
  }

  const destinataire = $("#destinataireInput").val();
  if (destinataire !== undefined && destinataire.length === 0) {
    alert("Le destinataire ne peut être vide !");
    return;
  }
  if (destinataire !== undefined) {
    console.log("Destinataire : " + destinataire);
  }
  console.log("Message : " + message);
  input.val("");
});

//Gestion de la création d'un nouveau message
$("#nouvelleConversation").click(() => {
  isOnConversationMenu = false;
  retireIDSelectionne();
  switchSiEcranPlusPetitQue(640);
  //On retire le possible input déjà existant
  supprimeChampDestinataire();

  //On indique qu'on veut une nouvelle conversation
  $("#titreConversation").html("Nouvelle conversation");
  //et on met l'icône correspondante
  $("#imageTitreConversation").attr(
    "src",
    "../Ressources/Messagerie/nouveauMessage.png"
  );

  $(
    '<input id="destinataireInput" type="text" placeholder="À qui souhaitez vous envoyer un message ?">'
  ).insertBefore($("#nouveauMessage"));
  $("#historiqueMessages").css("display", "none");
  $("#nouveauMessageForm").css("display", "flex");
});

//Retour affichage menu
$("#retourMenu").click(() => {
  isOnConversationMenu = true;
  switchSiEcranPlusPetitQue(taillePetitEcran);
});

window.onresize = () => {
  if ($(window).width() > taillePetitEcran) {
    $("#conversations").css("display", "flex");
    $("#focusConversation").css("display", "grid");
  } else if (isOnConversationMenu) {
    $("#conversations").css("display", "flex");
    $("#focusConversation").css("display", "none");
  } else {
    $("#focusConversation").css("display", "grid");
    $("#conversations").css("display", "none");
  }
};

function switchSiEcranPlusPetitQue(sizeScreen) {
  if ($(window).width() <= sizeScreen) {
    $("#conversations").toggle();
    const focusConversation = $("#focusConversation");
    focusConversation.css("display") === "none" ||
    $("#conversations").css("display") === "none"
      ? focusConversation.css("display", "grid")
      : focusConversation.css("display", "none");
  }
}

function retireIDSelectionne() {
  $("#selectionne").attr("id", "");
}

function supprimeChampDestinataire() {
  $("#destinataireInput").remove();
}
