$(".onglet").click((e) => {
  Object.values($(".onglet")).forEach((ong) => (ong.id = ""));
  e.target.id = "selected";
  console.log(e.target.value);
  $("#titre").html(`requête ajax pour l\'id ${e.target.value}`);
});

$("#boutonEnvoi").click(() => {
  console.log("HEHEHE");
});
