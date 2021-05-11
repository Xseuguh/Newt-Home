package org.wtg.web;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.ContraintesRepository;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.entities.Contraintes;
import org.wtg.entities.LiaisonOffreContrainte;
import org.wtg.entities.LiaisonOffreService;
import org.wtg.entities.Offres;

/****Partie 1 afficher les utilisateurs: lie aux administrateurs***/
@Controller
public class OffresController {
	@Autowired
	private OffresRepository offresDao;
	@Autowired
	private ContraintesRepository contrainteDao;
	@Autowired
	private LiaisonOffreContrainteRepository liaisonOffreContrainteDao;
	@Autowired
	private LiaisonOffreServiceRepository liaisonOffreServiceDao;

	//************For User*******************/
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
			@RequestParam(name = "date_limite_string", defaultValue = "") String date_limite_string,
			@RequestParam(name= "deuxEnfantsMax",defaultValue="") String deuxEnfantsMax,
			@RequestParam(name="animaux",defaultValue="")String animaux,
			@RequestParam(name="enfants",defaultValue="")String pasDenfantsAutorises,
			@RequestParam(name="bruitVingtTrois",defaultValue="")String pasDeBruitApres23H,
			@RequestParam(name="cigarette",defaultValue="")String pasDeCigarettes,
			@RequestParam(name="arroserPlante",defaultValue="") String arroserPlante,
			@RequestParam(name="garderLesAnimaux",defaultValue="") String garderLesAnimaux,
			@RequestParam(name="nettoyerLaMaison",defaultValue="") String nettoyerLaMaison){

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
			
			////////////////Liaison Contraintes////////////////
			List<Offres> maListeOffre=offresDao.findAll();
			int nombreElement=maListeOffre.size();
			//2 enfants max par logements
			if(!deuxEnfantsMax.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)nombreElement,(long)3);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			//pas d animaux
			if(!animaux.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)nombreElement,(long)5);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			//pas d enfants autorises
			if(!pasDenfantsAutorises.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)nombreElement,(long)4);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			if(!pasDeBruitApres23H.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)nombreElement,(long)2);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			if(!pasDeCigarettes.equals("")) {
				LiaisonOffreContrainte liaisonOffreContrainte=new LiaisonOffreContrainte((long)nombreElement,(long)1);
				liaisonOffreContrainteDao.save(liaisonOffreContrainte);
			}
			
			////////////////Liaison Services////////////////
			if(!arroserPlante.equals("")) {
				LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)nombreElement,(long) 2);
				liaisonOffreServiceDao.save(liaisonOffreService);
			}
			if(!garderLesAnimaux.equals("")) {
				LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)nombreElement,(long) 1);
				liaisonOffreServiceDao.save(liaisonOffreService);
			}
			if(!nettoyerLaMaison.equals("")) {
				LiaisonOffreService liaisonOffreService=new LiaisonOffreService((long)nombreElement,(long) 3);
				liaisonOffreServiceDao.save(liaisonOffreService);
			}
		}
		return "Utilisateur_ajouterAnnonce";
	}
	/*
	@RequestMapping(value = "/test")
	public String test(Model model) {
		return "Utilisateur_AjouterAnnonce";
	}
	*/
	/**************FOR admin****************************/
	/*@RequestMapping(value = "/admin/pannel/user")
	public String search2(Model model) {
		return "Admin_interface.jsp";
	}*/
}
