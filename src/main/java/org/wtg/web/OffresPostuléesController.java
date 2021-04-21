package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.OffresPostuléesRepository;
import org.wtg.entities.OffresPostulées;

@Controller
public class OffresPostuléesController {
	@Autowired
	private OffresPostuléesRepository offresPostuléesDao;
	@RequestMapping(value = "/offres_postulées/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") Long mc) {
	List<OffresPostulées> offresPostulées = offresPostuléesDao.findByIdOffre(mc);
	model.addAttribute("offresPostulées", offresPostulées);
	model.addAttribute("motC", mc);
	return "listOffresPostulées";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/

}
