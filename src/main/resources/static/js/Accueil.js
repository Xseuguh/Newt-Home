function showRechercheAvancee() {
	var rA = document.getElementById("rechercheAvanceeDiv");
	var rS = document.getElementById("rechercheDiv");
	rS.style.display = "none";
	rA.style.display = "block";
}

function hideRechercheAvancee() {
	var rA = document.getElementById("rechercheAvanceeDiv");
	var rS = document.getElementById("rechercheDiv");
	rA.style.display = "none";
	rS.style.display = "block";
}

$(function() {
	var frm = $('#formAvancee');
	frm.submit(function(ev) {
		ev.preventDefault();
		$.ajax({
			type : frm.attr('method'),
			url : frm.attr('action'),
			data : frm.serializeArray(),

			success : function(html) {
				$("#offres").load("/Accueil/Recherche_Avancee #offres", {
				    motCle : $("#motCle").val(),
				    lieu : $("#lieu").val(),
				    listeContraintes : $("input[name='listeContraintes']:checked").val(),
				    listeServices : $("input[name='listeServices']:checked").val() });
			}
		});
		
	});
});
