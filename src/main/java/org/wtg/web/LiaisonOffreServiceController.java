package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.LiaisonOffreServiceRepository;
import org.wtg.entities.LiaisonOffreService;

@Controller
public class LiaisonOffreServiceController {
	@Autowired
	private LiaisonOffreServiceRepository liaisonServiceDao;
	@RequestMapping(value = "/liaisons_services/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") Long mc) {
	List<LiaisonOffreService> liaisonService = liaisonServiceDao.findByIdLiaison(mc);
	model.addAttribute("liaisonService", liaisonService);
	model.addAttribute("motC", mc);
	return "listLiaisonServices";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/
}
