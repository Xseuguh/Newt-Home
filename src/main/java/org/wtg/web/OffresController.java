package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.OffresRepository;
import org.wtg.entities.Offres;

@Controller
public class OffresController {
	@Autowired
	private OffresRepository offresDao;
	@RequestMapping(value = "/offres/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") String mc) {
	List<Offres> offres = offresDao.findByName("%" + mc + "%");
	model.addAttribute("offres", offres);
	model.addAttribute("motC", mc);
	return "listOffres";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/

}
