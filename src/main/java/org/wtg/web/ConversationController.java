package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.ConversationRepository;
import org.wtg.entities.Conversation;

@Controller
public class ConversationController {
	@Autowired
	private ConversationRepository conversationDao;
	@RequestMapping(value = "/conversations/search")
	public String search(Model model, @RequestParam(name = "motCle",
	defaultValue = "") Long mc) {
	List<Conversation> conversations = conversationDao.findByIdConversation(mc);
	model.addAttribute("conversations", conversations);
	model.addAttribute("motC", mc);
	return "listConversations";
	}
	/*définir ici les différents méthodes (search, add, delete...) nécéssaires 
	 lors de la manip de la table Contraintes
*/

}
