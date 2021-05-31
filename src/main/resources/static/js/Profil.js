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

  const elements = ['<ul id="liste">', ...generationHtml(data, choix), "</ul>"];
  if (choix === "annonces") {
    //TODO CHANGER L'URL PAR LA BONNE
    elements.unshift(
      `<a href="/annonce/posterUneAnnonce" id="ajouteAnnonce" ><img src="../images/profil/ajoutAnnonce.png" width="50em"/></a>`
    );
  }
  return elements.join("");
}

//Fonction qui va générer l'html pour les annonces/reservations (sous forme de liste)
function generationHtml(tableauElements, choix) {
  return tableauElements.map(
    (element) =>
      `<li class="${choix}" 
        id="${element.id_offre}" 
        data-toggle="modal" 
        data-target="#modalOffre${element.id_offre}">
        <p>
          ${element.titre}
          <span>
            ${
              element.pourvu || element.accepte
                ? "L'offre est pourvue"
                : "L'offre n'est pas pourvue"
            }
          </span>
          <a href="/ads/editing?id=${element.id_offre}">Edit</a>
          <a onclick="removeElement('${choix}',${element.id_offre})">Delete</a>
        </p>
        <p>${element.description}</p>
        <p>${element.adresse}, ${element.ville} (${element.code_postal} ${
        element.pays
      })</p>
      </li>
      ${
        choix === "annonces" && !element.pourvu
          ? createModal(element.id_offre)
          : ""
      }
      `
  );
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

        console.log(data);

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

function createModal(annonceID) {
  $.getJSON(`getUserWhoApply?offreID=${annonceID}`, (users) => {
    if (users.length > 0) {
      console.log(users);
      $(`#${annonceID}`).after(`
      <div class="modal" id="modalOffre${annonceID}" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Liste des utilisateurs postulant à cette annonce :</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
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
                                class="smallImage"
                                type="image"
																src="../images/Messagerie/envoiMessage.png"
																alt="Envoyer un message"
                                width="30%">
														</form>
                          </td>
                          <td>
                            
															<input
                                onclick="adUserManager('approve',${user.id_user},${annonceID})"
                                value="${user.id_user}"
                                class="smallImage" 
                                type="image"
																src="../images/icones/valider.png"
																alt="Accepter cet utilisateur"
                                width="30%"/>
                          </td>
                          <td>
                              <input
                                onclick="adUserManager('refuse',${user.id_user},${annonceID})"
                                value="${user.id_user}"
                                class="smallImage" 
                                type="image"
                                src="../images/icones/croix.png"
                                alt="Refuser cet utilisateur"
                                width="30%"/>
                          </td>
                        </tr>`
                    )
                    .join("")}
              </table>
            </div>
          </div>
        </div>
    </div>`);
    }
  });

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
