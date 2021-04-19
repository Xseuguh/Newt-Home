package org.wtg.web;

import org.wtg.dao.ContraintesRepository;
import org.wtg.entities.Contraintes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ContraintesController {
	@Autowired
	private ContraintesRepository contrainteDao;
	@RequestMapping(value = "/contraintes/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") String mc) {
	List<Contraintes> contraintes = contrainteDao.findByName("%" + mc + "%");
	model.addAttribute("contraintes", contraintes);
	model.addAttribute("motC", mc);
	return "listContraintes";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/
	
}
