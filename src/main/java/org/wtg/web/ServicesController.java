package org.wtg.web;

import org.wtg.dao.ServicesRepository;
import org.wtg.entities.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ServicesController {
	@Autowired
	private ServicesRepository serviceDao;
	@RequestMapping(value = "/services/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") String mc) {
	List<Services> services = serviceDao.findByName("%" + mc + "%");
	model.addAttribute("services", services);
	model.addAttribute("motC", mc);
	return "listServices";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/
	
}
