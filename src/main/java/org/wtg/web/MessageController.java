package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.MessageRepository;
import org.wtg.entities.Message;

@Controller

public class MessageController {
	@Autowired
	private MessageRepository messageDao;
	@RequestMapping(value = "/messages/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") Long id) {
	List<Message> message = messageDao.findByIdSender(id);
	model.addAttribute("message", message);
	model.addAttribute("motC", id);
	return "listMessage";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/

}
