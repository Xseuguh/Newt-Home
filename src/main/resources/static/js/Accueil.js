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
		$.ajax({
			type : frm.attr('method'),
			url : frm.attr('action'),
			data : frm.serialize(),

			success : function(data) {
				var result = $(data);
				var jqObj = jQuery(data);
				jqObj.find("#rechercheDiv").remove();
				jqObj.find("#rechercheAvanceeDiv").remove();
				$('#offres').empty().append(jqObj);

			}
		});
		ev.preventDefault();
	});
});
