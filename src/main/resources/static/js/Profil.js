const CHOICE_TO_ROUTE = {
  annonces: "getAnnonces",
  reservations: "getReservations",
  parametres: "getInfo",
};

$(".options").click((e) => {
  $("#selectionne").attr("id", "");
  e.target.id = "selectionne";
  const choix = e.target.value;
  affichageOption(choix).then((html) => {
    $("#affichage").html(html);
    attachActionTo(choix);
  });
});

async function affichageOption(choix) {
  const data = await $.getJSON(`/profil/${CHOICE_TO_ROUTE[choix]}`);

  if (!data) {
    return "<h1>Ooops something went wrong!</h1>";
  }

  if (choix === "parametres") {
    return `
    <div id="informations">
      <img 
      class="clickable"
      id="avatarImage" 
      src="../images/user/${data.id_user}.png"
      onerror='this.onerror = null; this.src="../images/user/default.png"'
      data-toggle="modal" 
      data-target="#modalUpdateAvatar"/>
      <p>${data.nom} ${data.prenom}</p>
	  </div>

    <div class="modal" id="modalUpdateAvatar" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Choisissez votre avatar :</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              </button>
            </div>
            <div class="modal-body">
              <form id="updateAvatar">
                <label for="avatar">
                	Veuillez sélectionner l'image d'avatar que vous souhaitez utiliser <br/> 
                	(image au format png, et de taille inférieur à 1 Mo))
                </label>
                <span class="error"></span>
                <input type="file" required="true" name="avatar" id="avatar" accept="image/png"/>
                <input type="submit" value="Mettre à jour mon avatar" />
              </form>
            </div>
          </div>
        </div>
    </div>

    <form id="modificationInfo">
        <legend>Paramètres du compte</legend>
        <br />

        <label>Adresse mail :</label>
        <span class="error"></span>
        <input type="email"
        name="nouvelleAdresseMail"
        id="nouvelleAdresseMail"
        value="${data.mail}"
        />

        <label>Modifier votre mot de passe :</label>
        <span class="error"></span>
        <input
            type="password"
            name="ancienMotDePasse"
            id="ancienMotDePasse"
            placeholder="Saisissez votre ancien mot de passe"
        />

        <span class="error"></span>
        <input
            type="password"
            name="nouveauMotDePasse"
            id="nouveauMotDePasse"
            placeholder="Saisissez votre nouveau mot de passe"
        />

        <input
            type="submit"
            name="enregistrer"
            value="Enregistrer les modifications"
        />
    </form>`;
  }

  const htmlList = await generationHtml(data, choix);

  const elements = ['<ul id="liste">', ...htmlList, "</ul>"];
  if (choix === "annonces") {
    //TODO CHANGER L'URL PAR LA BONNE
    elements.unshift(
      `<a href="/annonce/posterUneAnnonce" id="ajouteAnnonce" ><img src="../images/profil/ajoutAnnonce.png" width="50em"/></a>`
    );
  }
  return elements.join("");
}

//Fonction qui va générer l'html pour les annonces/reservations (sous forme de liste)
async function generationHtml(tableauElements, choix) {
  return await Promise.all(
    tableauElements.map(async (element) => {
      let users = [];
      if (choix === "annonces" && !element.pourvu) {
        users = await $.getJSON(
          `getUserWhoApply?offreID=${element.id_offre}`,
          (users) => users
        );
      }

      // console.log(users);

      return `<li 
        class="${choix} ${users.length > 0 ? "clickable" : ""}" 
        id="${element.id_offre}" 
        data-toggle="modal" 
        data-target="#modalOffre${element.id_offre}">
        <p class="adTitleAndState">
          ${element.titre}
          ${addOptionsForTheAdd(choix, element)}
          ${imageForStateOf(element, choix, users.length)}
        </p>
        <p>${element.description}</p>
        <p>${element.adresse}, ${element.ville} (${element.code_postal} ${
        element.pays
      })</p>
      </li>
      ${createModal(users, element.id_offre)}
      `;
    })
  );
}

function addOptionsForTheAdd(choix, element) {
  if (choix === "annonces") {
    return `<a href="/ads/editing?id=${element.id_offre}" title="Éditer l'offre">
        <img class="clickable smallIcon" src="../images/icones/edit.png" />
      </a>
      <a onclick="removeElement('${choix}',${element.id_offre})" title="Supprimer l'offre">
        <img class="clickable smallIcon" src="../images/icones/trash.png" />
      </a>`;
  }
  return `<a onclick="removeElement('${choix}',${element.id_offre})" title="Supprimer la demande">
      <img class="clickable smallIcon" src="../images/icones/trash.png" />
    </a>`;
}

function imageForStateOf(element, choix, numberOfApplicant) {
  if (choix === "annonces") {
    if (element.pourvu) {
      return '<div class="green state">Cette offre est pourvu</div>';
    }
    if (numberOfApplicant > 0) {
      return '<div class="orange state">Des gens ont postulé à cette offre</div>';
    }
    return '<div class="red state">Pour le moment personne ne postule à cette offre</div>';
  }
  if (parseInt(element.accepte) === 1) {
    return '<div class="green state">Votre demande a été accepté</div>';
  }
  if (parseInt(element.accepte) === 2) {
    return '<div class="red state">Votre demande a été refusé</div>';
  }
  return '<div class="orange state">En attente</div>';
}

function attachActionTo(choix) {
  switch (choix) {
    case "parametres":
      $("#updateAvatar").submit((e) => {
        e.preventDefault();
        const selectedImage = $("#avatar").get(0).files[0];

        const errors = [];

        if (!selectedImage) {
          errors.push("Pas de fichier sélectionné !");
        }
        if (selectedImage.size > 1000000) {
          errors.push("Le fichier est trop lourd !");
        }

        if (errors.length > 0) {
          displayError(errors, "avatar");
          return;
        }

        const toBase64 = (file) =>
          new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = (error) => reject(error);
          });

        toBase64(selectedImage).then((imageBase64) => {
          $.post("/profil/updateAvatar", { avatar: imageBase64 }, "json")
            .done(() => {
              alert(
                "Le changement d'avatar a été pris en compte, il sera effectif sous peu !"
              );
              $(`.options[value="parametres"]`).click();
            })
            .fail(function (error) {
              alert(error.responseText);
            });
        });
      });

      $("#modificationInfo").submit((e) => {
        e.preventDefault();

        if (!$("#ancienMotDePasse").val() && $("#nouveauMotDePasse").val()) {
          displayError(["Le champ est vide !"], "ancienMotDePasse");
          return;
        }

        const numberOfError =
          passwordError($("#nouveauMotDePasse").val()).length +
          emailError($("#nouvelleAdresseMail").val()).length;
        if (numberOfError > 0) {
          return;
        }

        const data = {
          email: $("#nouvelleAdresseMail").val(),
        };

        if ($("#ancienMotDePasse").val() && $("#nouveauMotDePasse").val()) {
          data["oldPassword"] = $("#ancienMotDePasse").val();
          data["newPassword"] = $("#nouveauMotDePasse").val();
        }

        $.post("/profil/updateInfo", data, "json")
          .done(() => {
            $(`.options[value="parametres"]`).click();
          })
          .fail(function (error) {
            alert(error.responseText);
          });
      });
      $("#nouveauMotDePasse").keyup((e) => {
        displayError(passwordError(e.target.value), "nouveauMotDePasse");
      });

      $("#ancienMotDePasse").keyup((e) => {
        displayError(
          e.target.value ? [] : ["Le champ est vide !"],
          "ancienMotDePasse"
        );
      });

      $("#nouvelleAdresseMail").keyup((e) => {
        displayError(emailError(e.target.value), "nouvelleAdresseMail");
      });
      break;
    default:
      break;
  }
}

function passwordError(password) {
  const errors = [];
  if (password) {
    if (password.length < 8) {
      errors.push("Mot de passe trop court ! (au moins 8 caractères)");
    }
    if (password.search("[A-Z]") == -1) {
      errors.push("Il manque une majuscule.");
    }
    if (password.search("[a-z]") == -1) {
      errors.push("Il manque une minuscule.");
    }
    if (password.search("[0-9]") == -1) {
      errors.push("Il manque un chiffre.");
    }
  }
  return errors;
}

function emailError(email) {
  const errors = [];
  if (email.length < 1) {
    errors.push("Le champ d'email est vide !");
  }
  if (email.search("@") == -1) {
    errors.push("L'email n'est pas valide !");
  }
  return errors;
}

function displayError(errors, inputID) {
  $(`#${inputID}`).prev(".error").html(errors.join("<br/>"));
}

function createModal(users, annonceID) {
  if (users.length > 0) {
    return `<div class="modal modalApplicant" id="modalOffre${annonceID}" role="dialog">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Liste des utilisateurs postulant à cette annonce :
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </h5>
          
        </div>
        <div class="modal-body">
          <table class="listUserApplying">
              ${users
                .map(
                  (user) =>
                    `<tr>
                      <td>
                        ${user.prenom} ${user.nom}
                      </td>
                      <td>
                        <form action="/messagerie/" method="post">
                          <input type="hidden" id="userID" name="receiverID" value="${user.id_user}">
                          <input
                            class="mediumIcon clickable"
                            type="image"
                            title="Envoyer un message"
                            src="../images/Messagerie/envoiMessage.png"
                            alt="Envoyer un message">
                        </form>
                      </td>
                      <td>
                        
                          <input
                            onclick="adUserManager('approve',${user.id_user},${annonceID})"
                            value="${user.id_user}"
                            class="mediumIcon clickable" 
                            type="image"
                            title="Accepter cet utilisateur pour l'offre"
                            src="../images/icones/valider.png"
                            alt="Accepter cet utilisateur"/>
                      </td>
                      <td>
                          <input
                            onclick="adUserManager('refuse',${user.id_user},${annonceID})"
                            value="${user.id_user}"
                            class="mediumIcon clickable" 
                            type="image"
                            title="Refuser cet utilisateur pour l'offre"
                            src="../images/icones/croix.png"
                            alt="Refuser cet utilisateur"/>
                      </td>
                    </tr>`
                )
                .join("")}
              </table>
            </div>
          </div>
        </div>
    </div>`;
  }

  return "";
}

function adUserManager(type, userID, offreID) {
  $.post(`/profil/${type}User`, { offreID: offreID, userID: userID }, () => {
    $(`.options[value="annonces"]`).click();
  });
}

function removeElement(type, ID) {
  $.post(`/profil/remove/${type}`, { offreID: ID }, () => {
    $(`.options[value="${type}"]`).click();
  });
}
