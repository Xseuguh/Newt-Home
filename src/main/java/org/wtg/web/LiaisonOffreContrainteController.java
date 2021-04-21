package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.LiaisonOffreContrainteRepository;
import org.wtg.entities.LiaisonOffreContrainte;

@Controller
public class LiaisonOffreContrainteController {
	@Autowired
	private LiaisonOffreContrainteRepository liaisonContrainteDao;
	@RequestMapping(value = "/liaisons_contraintes/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") Long mc) {
	List<LiaisonOffreContrainte> liaisonContrainte = liaisonContrainteDao.findByIdLiaison(mc);
	model.addAttribute("liaisonContrainte", liaisonContrainte);
	model.addAttribute("motC", mc);
	return "listLiaisonContraintes";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/
}
