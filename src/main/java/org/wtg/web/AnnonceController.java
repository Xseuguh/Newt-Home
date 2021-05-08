package org.wtg.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Selection;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.ContraintesRepository;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.dao.ServicesRepository;
import org.wtg.entities.Annonce;
import org.wtg.entities.Contraintes;
import org.wtg.entities.JoinOffresUsers;
import org.wtg.entities.Offres;
import org.wtg.entities.Services;

@Controller
public class AnnonceController {
	@Autowired
	OffresRepository offresDao;
	@Autowired
	LiaisonOffreContrainteRepository contrainteDao;
	@Autowired
	LiaisonOffreServiceRepository serviceDao;
	@Autowired
	ContraintesRepository listeContrainteRechercheDao;
	@Autowired
	ServicesRepository listeServiceRechercheDao;

	@RequestMapping(value = "/Accueil")
	public String search(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc) {

		List<Contraintes> listeContrainteRecherche = listeContrainteRechercheDao.findAll();
		List<Services> listeServiceRecherche = listeServiceRechercheDao.findAll();
		List<Offres> offres = offresDao.findOffreByNameOrDescription("%" + mc + "%");
		List<Annonce> contraintes = new ArrayList<Annonce>();
		List<Annonce> services = new ArrayList<Annonce>();
		List<JoinOffresUsers> infosProprio = new ArrayList<JoinOffresUsers>();

		for (Offres value : offres) {
			contraintes.addAll(contrainteDao.findContraintesByIdOffre(value.getId_offre()));
			services.addAll(serviceDao.findServicesByIdOffre(value.getId_offre()));
			infosProprio.addAll(offresDao.findUserByIdOffre(value.getId_offre()));

		}

		Boolean connected = true; // TODO : changer la valeur de "connected" une fois qu'on aura géré la connexion
									// (session et/ou cookie)
		model.addAttribute("listeContrainteRecherche", listeContrainteRecherche);
		model.addAttribute("listeServiceRecherche", listeServiceRecherche);
		model.addAttribute("offres", offres);
		model.addAttribute("contraintes", contraintes);
		model.addAttribute("services", services);
		model.addAttribute("infosProprio", infosProprio);
		model.addAttribute("motC", mc);
		model.addAttribute("connected", connected);
		return "Accueil";
	}

	@RequestMapping(value = "/Accueil/Recherche_Avancee")
	public String rechercheAvancee(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc,
			@RequestParam(value = "listeContraintes", defaultValue = "") long[] listeContraintes,
			@RequestParam(value = "listeServices", defaultValue = "") long[] listeServices,
			@RequestParam(name = "lieu", defaultValue = "") String lieu) {

		List<Contraintes> listeContrainteRecherche = listeContrainteRechercheDao.findAll();
		List<Services> listeServiceRecherche = listeServiceRechercheDao.findAll();
		List<Offres> offresAvancees = new ArrayList<Offres>();

		if ((listeContraintes.length>0) && (listeServices.length>0) && !mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findAvanceeTousChamps("%" + mc + "%", listeContraintes, listeServices, "%" + lieu + "%"));
		}

		else if (listeContraintes.length>0 && listeServices.length == 0 && mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainte(listeContraintes));
		}

		else if (listeContraintes.length == 0  && listeServices.length >0 && mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByService(listeServices));
		}

		else if (listeContraintes.length == 0 && listeServices.length == 0 && mc.equals("") && !(lieu.equals(""))) {
			offresAvancees.addAll(offresDao.findOffreByLocation("%" + lieu + "%"));
		}
		
		else if(listeContraintes.length == 0 && listeServices.length == 0 && !(mc.equals("")) && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findOffreByNameOrDescription("%" + mc + "%"));
		
		}

		else if (listeContraintes.length>0 && listeServices.length>0 && mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteService(listeContraintes, listeServices));
		}

		if (listeContraintes.length>0 && listeServices.length == 0 && !mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteMC("%" + mc + "%", listeContraintes));
		}

		else if (listeContraintes.length>0 && listeServices.length == 0 && mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteLocation(listeContraintes, "%" + lieu + "%"));
		}

		else if (listeContraintes.length == 0 && listeServices.length>0 && !mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByServiceMC("%" + mc + "%", listeServices));
		}

		else if (listeContraintes.length == 0 && listeServices.length>0 && mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByServiceLocation("%" + lieu + "%", listeServices));
		}

		else if (listeContraintes.length == 0 && listeServices.length == 0 && !(mc.equals("")) && !(lieu.equals(""))) {
			offresAvancees.addAll(offresDao.findByMCLocation("%" + mc + "%", "%" + lieu + "%"));
		}

		else if (listeContraintes.length == 0 && listeServices.length>0 && !mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByServiceMcLocation("%" + mc + "%", listeServices, "%" + lieu + "%"));
		}

		else if (listeContraintes.length>0 && listeServices.length == 0 && !mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteMcLocation("%" + mc + "%", listeContraintes, "%" + lieu + "%"));
		}

		else if (listeContraintes.length>0 && listeServices.length>0 && mc.equals("") && !lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteServiceLocation(listeContraintes, listeServices, "%" + lieu + "%"));
		}

		else if (listeContraintes.length>0 && listeServices.length>0 && !mc.equals("") && lieu.equals("")) {
			offresAvancees.addAll(offresDao.findByContrainteServiceMC(listeContraintes, listeServices, "%" + mc + "%"));
		}
		
		List<Annonce> contraintes = new ArrayList<Annonce>();
		List<Annonce> services = new ArrayList<Annonce>();

		List<JoinOffresUsers> infosProprio = new ArrayList<JoinOffresUsers>();

		for (Offres value : offresAvancees) {
			contraintes.addAll(contrainteDao.findContraintesByIdOffre(value.getId_offre()));
			services.addAll(serviceDao.findServicesByIdOffre(value.getId_offre()));
			infosProprio.addAll(offresDao.findUserByIdOffre(value.getId_offre()));

		}

		Boolean connected = true; // TODO : changer la valeur de "connected" une fois qu'on aura géré la connexion
		// (session et/ou cookie)
		model.addAttribute("listeContrainteRecherche", listeContrainteRecherche);
		model.addAttribute("listeServiceRecherche", listeServiceRecherche);
		model.addAttribute("offres", offresAvancees);
		model.addAttribute("contraintes", contraintes);
		model.addAttribute("services", services);
		model.addAttribute("infosProprio", infosProprio);
		model.addAttribute("motC", mc);
		model.addAttribute("lieu", lieu);
		model.addAttribute("listeC", listeContraintes);
		model.addAttribute("listeS", listeServices);
		model.addAttribute("connected", connected);
		return "Accueil";
	}

}
