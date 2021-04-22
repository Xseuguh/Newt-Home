package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wtg.dao.ConversationRepository;
import org.wtg.dao.MessageRepository;
import org.wtg.entities.ConversationInfo;
import org.wtg.entities.Message;

@Controller
@RequestMapping(path = "/messagerie")
public class MessagerieController {
	@Autowired
	private ConversationRepository conversationDao;
	@Autowired
	private MessageRepository messageDao;

	@GetMapping(path = "/")
	public String search(Model model) {
		int USER_ID = 1;// A MODIFIER UNE FOIS QUE LES SESSIONS DES UTILISATEURS SERONT EN PLACE: ON
						// DOIT RÉCUPÉRER L'ID DE L'USER
		List<ConversationInfo> conversations = conversationDao.findConversationsByUserID(USER_ID);
		model.addAttribute("conversations", conversations);
		return "Messagerie";
	}

	/*
	 * Récupére les messages d'une conversation via son id (en GET)
	 */
	@GetMapping(path = "/getMessages")
	public @ResponseBody List<Message> getMessages(@RequestParam Integer conversationID) {
		System.out.println("Get messages pour " + conversationID);
		return messageDao.findMessagesByConversationID(conversationID);
	}

	/*
	 * Ajoute un message à une conversation existante ou en crée une nouvelle (en
	 * POST)
	 */
	@PostMapping(path = "/add")
	public ResponseEntity<Object> addMessage(
			@RequestParam(value = "conversationID", required = false) Integer conversationID,
			@RequestParam(value = "userID", required = false) Integer userID,
			@RequestParam(value = "receiverID", required = false) Integer receiverID, @RequestParam String message) {

		if (message.length() > 0) {
			if (conversationID == null) { // Si la conversation n'est pas passé en requête, on en crée une nouvelle
				if (userID == null || receiverID == null) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Oups, l'utilisateur ou le destinataire du message n'est pas défini...");
				}
				// On vérifie quand même que le couple (user, receiver) n'existe pas dans la bdd
				Integer convPossibleID = conversationDao.getConversationByIdCouple(userID, receiverID);
				if (convPossibleID == null) {
					conversationDao.addConversation(userID, receiverID);
					conversationID = conversationDao.getIDLastInsert();
				}
				else {
					conversationID = convPossibleID;
				}
			}
			messageDao.addMessage(conversationID, message);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("C'est honteux, le message est vide !");
	}
}
