const TAILLE_PETIT_ECRAN = 700;
let isOnConversationMenu = true;

let conversationID;

//Partie de code pour refresh les messages de la conversation tous les 5s
let isRunning = false;
const refresh = setInterval(() => {
  if (isRunning) {
    afficheConversation(conversationID, false);
  }
}, 5000);

//Gestion de l'affichage de la conversation sélectionnée
$(".onglet").click(async (e) => {
  isOnConversationMenu = false;
  switchSiEcranPlusPetitQue(TAILLE_PETIT_ECRAN);
  supprimeChampDestinataire();

  retireIDSelectionne();
  e.target.id = "selectionne";

  conversationID = parseInt(e.target.value);
  await afficheConversation(conversationID, true);

  const expediteurName = e.target.textContent;
  $("#titreConversation").html(expediteurName);

  const srcImg = e.target.children[0].src.split("/");
  //Mettre le lien de l'image du profil de l'expéditeur
  $("#imageTitreConversation").attr(
    "src",
    `../images/user/${srcImg[srcImg.length - 1]}`
  );

  isRunning = true; //On actualise les messsages
});

function scrollSurPlusRecent() {
  const listeDesMessages = $("#historiqueMessages");
  listeDesMessages.scrollTop(listeDesMessages.prop("scrollHeight"));
}

//Gestion de la récupération de l'historique des messages pour la conversation sélectionnée
async function afficheConversation(conversationID, isNewConversation) {
  const historiqueDesMessages = $("#historiqueMessages");

  let shouldScroll = false;
  const currentScroll = historiqueDesMessages.scrollTop();

  const messages = await getMessages(conversationID);

  if (isNewConversation) {
    shouldScroll = true;
    //on affiche les div pour l'historique des messages et le formulaire d'envoi de nouveau message
    historiqueDesMessages.css("display", "block");
    $("#nouveauMessageForm").css("display", "flex");

    //on efface l'historique précédent
    historiqueDesMessages.children().remove();

    //et on ajoute les nouveaux éléments
    messages.forEach((message) => {
      historiqueDesMessages.append(generateMessage(message));
    });
  } else {
    //Sinon, on voit si on a un nouveau message à ajouter, si oui, on l'ajoute
    const currentSize = historiqueDesMessages.children().length;
    if (currentSize < messages.length) {
      shouldScroll = true;
      for (let i = currentSize; i < messages.length; i++) {
        historiqueDesMessages.append(generateMessage(messages[i]));
      }
    }
  }

  //détermine si on doit scroll sur l'élément le plus récent, ou rester au scroll actuel
  if (shouldScroll) {
    scrollSurPlusRecent();
  } else {
    historiqueDesMessages.scrollTop(currentScroll);
  }
}

//Fonction qui génére le nouvel élément de la conversation
function generateMessage(message) {
  return `<li class="${message.origine}">
    <p class="corps">${message.message}</p>
    <p class="horodatage">${message.date_message}</p>
  </li>`;
}

//Fonction qui récupére en ajax les messages d'une conversation
async function getMessages(conversationID) {
  const rawMessages = await fetch(
    `/messagerie/getMessages?conversationID=${conversationID}`,
    {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "GET",
    }
  );

  return rawMessages.json() || [];
}

//Gestion de l'envoie d'un nouveau message dans la conversation affichée
$("#nouveauMessageForm").on("submit", (e) => {
  e.preventDefault();

  const input = $("#nouveauMessageInput");
  const message = input.val();

  if (message.length <= 0) {
    alert("Le message ne peut être vide !");
    return;
  }

  const data = { message: message, receiverID: 2 };

  const destinataire = $("#destinataireInput").val();
  if (destinataire !== undefined) {
    if (destinataire.length <= 0) {
      alert("Le destinataire ne peut être vide !");
      return;
    }
    console.log("Destinataire : " + destinataire);

    data["receiverID"] = destinataire;
  }

  fetch("/messagerie/add", {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(data),
  })
    .then(() => {
      afficheConversation(conversationID);
    })
    .catch(() => alert("Oops..."));

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
    "../images/messagerie/nouveauMessage.png"
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
  switchSiEcranPlusPetitQue(TAILLE_PETIT_ECRAN);
});

window.onresize = () => {
  if ($(window).width() > TAILLE_PETIT_ECRAN) {
    $("#conversations").css("display", "flex");
    $("#focusConversation").css("display", "grid");
  } else if (isOnConversationMenu) {
    $("#conversations").css("display", "flex");
    $("#focusConversation").css("display", "none");
    isRunning = false; //On arréte l'update si on affiche pas la conversation
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
