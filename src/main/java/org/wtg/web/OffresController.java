package org.wtg.web;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.ContraintesRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.entities.Contraintes;
import org.wtg.entities.Offres;

@Controller
public class OffresController {
	@Autowired
	private OffresRepository offresDao;
	@Autowired
	private ContraintesRepository contrainteDao;

	@RequestMapping(value = "/offres/search")
	public String search(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc) {
		List<Offres> offres = offresDao.findByName("%" + mc + "%");
		model.addAttribute("offres", offres);
		model.addAttribute("motC", mc);
		return "listOffres";
	}

	@RequestMapping(value = "/annonce/ajout")
	public String add(Model model,
			@RequestParam(name = "descriptionAnnonce", defaultValue = "") String descriptionAnnonce,
			@RequestParam(name = "titreAnnonce", defaultValue = "") String titreAnnonce,
			@RequestParam(name = "adresseAnnonce", defaultValue = "") String adresseAnnonce,
			@RequestParam(name = "codePostalAnnonce", defaultValue = "") Integer codePostalAnnonce,
			@RequestParam(name = "villeAnnonce", defaultValue = "") String villeAnnonce,
			@RequestParam(name = "paysAnnonce", defaultValue = "") String paysAnnonce,
			@RequestParam(name = "date_debut_string", defaultValue = "") String date_debut_string,
			@RequestParam(name = "date_limite_string", defaultValue = "") String date_limite_string) {
		/*
		 * System.out.println("Titre"+titreAnnonce);
		 * System.out.println("Description"+descriptionAnnonce);
		 * System.out.println("adresseAnnonce"+adresseAnnonce);
		 * System.out.println("codePostalAnnonce"+codePostalAnnonce);
		 * System.out.println("villeAnnonce"+villeAnnonce);
		 * System.out.println("paysAnnonce"+paysAnnonce);
		 * System.out.println("date_debut_string"+date_debut_string);
		 * System.out.println("date_limite_string"+date_limite_string);
		 * System.out.println("Pourvu"+0); System.out.println("id user"+118218);
		 */
		//-----Condition importante pour eviter de remplir la table en ayant des null----
		if (!titreAnnonce.equals("")) {

			Date date_debut = Date.valueOf(date_debut_string);
			Date date_limite = Date.valueOf(date_limite_string);

			model.addAttribute("titre", titreAnnonce);
			model.addAttribute("description", descriptionAnnonce);
			model.addAttribute("adresse", adresseAnnonce);
			model.addAttribute("code_postal", codePostalAnnonce);
			model.addAttribute("ville", villeAnnonce);
			model.addAttribute("pays", paysAnnonce);
			model.addAttribute("date_debut", date_debut);
			model.addAttribute("date_limite", date_limite);
			model.addAttribute("pourvu", false);
			model.addAttribute("id_user", (long) 118218);

			Offres offresAjoutee = new Offres((long) 118218, titreAnnonce, descriptionAnnonce, paysAnnonce,
					villeAnnonce, codePostalAnnonce, adresseAnnonce, date_debut, date_limite, false);
			offresDao.save(offresAjoutee);
		}
		return "Utilisateur_ajouterAnnonce";
	}

	@RequestMapping(value = "/test")
	public String test(Model model) {
		return "Utilisateur_AjouterAnnonce";
	}

}

