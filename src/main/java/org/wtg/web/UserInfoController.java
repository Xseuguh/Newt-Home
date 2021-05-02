package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.UserInfoRepository;
import org.wtg.entities.UserInfo;

@Controller

@RequestMapping(path = "/user")
public class UserInfoController {
	@Autowired
	private UserInfoRepository usersDao;

	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc) {
		List<UserInfo> users = usersDao.findByName("%" + mc + "%");
		model.addAttribute("users", users);
		model.addAttribute("motC", mc);
		return "listUsers";
	}
	/*
	 * définir ici les différents méthodes (search, add, delete...) nécéssaires lors
	 * de la manip de la table Contraintes
	 */
}
