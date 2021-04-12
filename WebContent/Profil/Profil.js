$(".options").click((e) => {
  $("#selectionne").attr("id", "");
  e.target.id = "selectionne";
  $("#affichage").html(affichageOption(e.target.value));
});

function affichageOption(choix) {
  //Ajax pour récupérer les info du choix
  const fakeParameter = {
    name: "Toto",
    surname: "Le",
    mail: "toto.le@domain.com",
  };
  const fakeData = [
    {
      nom: "Elements 1 (annonce ou réservation)",
      contenu: "Mon super contenu",
    },
    {
      nom: "Elements 2 (annonce ou réservation)",
      contenu: "Mon super contenu 2",
    },
    {
      nom: "Elements 3 (annonce ou réservation)",
      contenu: "Mon super contenu 3",
    },
    {
      nom:
        'Elements 3bis (annonce ou réservation) qui est vachement plus longue que la 3 plus "classique"',
      contenu: "Mon super contenu 3 bis",
    },
  ];

  if (choix === "parametres") {
    return `
    <div id="informations">
		<img src="../Messagerie/temp.png">
		<p>${fakeParameter.surname} ${fakeParameter.name}</p>
	</div>
    <form id="modificationInfo">
        <legend>Paramètres du compte</legend>
        <br />
        <label>Adresse mail :</label>
        <input type="email" name="nouvelleAdresseMail" id="nouvelleAdresseMail" value="${fakeParameter.mail}">

        <label>Modifier votre mot de passe :</label>
        <input
            type="password"
            name="ancienMotDePasse"
            placeholder="Saisissez votre ancien mot de passe"
        />
        <input
            type="password"
            name="nouveauMotDePasse"
            placeholder="Saisissez votre nouveau mot de passe"
        />

        <input
            type="submit"
            name="enregistrer"
            value="Enregistrer les modifications"
        />
    </form>`;
  } else if (choix === "annonces" || choix === "reservations") {
    const elements = ['<ul id="liste">', ...generationHtml(fakeData), "</ul>"];
    if (choix === "annonces") {
      elements.splice(
        0,
        0,
        `<a href=${"../AjouterAnnonce/Utilisateur_ajouterAnnonce.jsp"} id="ajouteAnnonce" ><img src="../Ressources/Profile/ajoutAnnonce.png" width="50em"/></a>`
      );
    }
    return elements.join("");
  } else {
    return "<h1>Bienvenue</h1>";
  }
}

//Fonction qui va générer l'html pour les annonces/reservations (sous forme de liste)
function generationHtml(tableauElements) {
  return tableauElements.map(
    (element) =>
      `<li class="element"><p>${element.nom}</p><p>${element.contenu}</p></li>`
  );
}
