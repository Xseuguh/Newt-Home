package org.wtg.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.Selection;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.entities.Annonce;
import org.wtg.entities.JoinOffresUsers;
import org.wtg.entities.Offres;

@Controller
public class AnnonceController {
	@Autowired
	OffresRepository offresDao;
	@Autowired
	LiaisonOffreContrainteRepository contrainteDao;
	@Autowired
	LiaisonOffreServiceRepository serviceDao;
	
	@RequestMapping(value = "/Accueil")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") String mc) {
	
	List<Offres> offres = offresDao.findAll();
	List<Annonce> contraintes = new ArrayList<Annonce>();
	List<Annonce> services = new ArrayList<Annonce>();
	List<JoinOffresUsers> infosProprio = new ArrayList<JoinOffresUsers>();
	
	for(Offres value : offres){
		 contraintes.addAll(contrainteDao.findContraintesByIdOffre(value.getId_offre()));
		 services.addAll(serviceDao.findServicesByIdOffre(value.getId_offre()));
		 infosProprio.addAll(offresDao.findUserByIdOffre(value.getId_offre()));
		
	}
	
	Boolean connected = true;
	model.addAttribute("offres", offres);
	model.addAttribute("contraintes", contraintes);
	model.addAttribute("services", services);
	model.addAttribute("infosProprio", infosProprio);
	model.addAttribute("motC", mc);
	model.addAttribute("connected", connected);
	
	return "Accueil";
	}
	
	/*@RequestMapping(value = "/Accueil/Recherche_Avancee")
	public String rechercheAvancee(Model model, @RequestParam(name = "motCle",
	defaultValue = "") String mc, List<Integer> listeContraintes,List<Integer> listeServices, Date dateDebut ) {
	List<Offres> offresAvancees = offresDao.findAvancee("%" + mc + "%", listeContraintes, listeServices, dateDebut);
	model.addAttribute("offresAvancees", offresAvancees);
	model.addAttribute("motC", mc);
	model.addAttribute("lc", listeContraintes);
	model.addAttribute("ls", listeServices);
	model.addAttribute("date", dateDebut);
	return "Accueil";
	}*/

}

