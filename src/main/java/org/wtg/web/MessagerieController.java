package org.wtg.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wtg.dao.ConversationRepository;
import org.wtg.dao.MessageRepository;
import org.wtg.entities.ConversationInfo;
import org.wtg.entities.ConversationInfoAdd;
import org.wtg.entities.Message;
import org.wtg.entities.MessageInfoAdd;
import org.wtg.entities.MessageInfoGet;

@Controller
@RequestMapping(path = "/messagerie")
public class MessagerieController {
	private Long USER_ID = 1l; // A MODIFIER UNE FOIS QUE LES SESSIONS DES UTILISATEURS SERONT EN PLACE: ON
								// DOIT RÉCUPÉRER L'ID DE L'USER
	@Autowired
	private ConversationRepository conversationDao;
	@Autowired
	private MessageRepository messageDao;

	@GetMapping(path = "/")
	public String search(Model model) {
		List<ConversationInfo> conversations = conversationDao.findConversationsByUserID(USER_ID);
		model.addAttribute("conversations", conversations);

		return "Messagerie";
	}

	@PostMapping(path = "/")
	public String search(Model model, ConversationInfoAdd conversationInfo) {
		List<ConversationInfo> conversations = conversationDao.findConversationsByUserID(USER_ID);
		model.addAttribute("conversations", conversations);

		Long receiverID = conversationInfo.getReceiverID();
		if (receiverID != null) {
			Long conversationID = conversationDao.getConversationByIdCouple(USER_ID, receiverID);
			// on vérifie qu'une conversation n'existe pas déjà entre les deux utilisateurs
			if (conversationID == null) {
				conversationDao.addConversation(USER_ID, receiverID);
				conversationID = conversationDao.getIDLastInsert();
			}
			model.addAttribute("openConversationID", conversationID);
		}

		return "Messagerie";
	}

	/*
	 * Récupére les conversations via l'id d'user (en GET)
	 */
	@GetMapping(path = "/getConversations")
	public @ResponseBody List<ConversationInfo> getConversations() {
		return conversationDao.findConversationsByUserID(USER_ID);
	}

	/*
	 * Récupére les messages d'une conversation via son id (en GET)
	 */
	@GetMapping(path = "/getMessages")
	public @ResponseBody List<MessageInfoGet> getMessages(@RequestParam Long conversationID) {
		List<Message> rawMessages = messageDao.findMessagesByConversationID(conversationID);
		List<MessageInfoGet> messages = new ArrayList<>();
		for (Message message : rawMessages) {
			messages.add(new MessageInfoGet(message.getMsg(), message.getId_origine() == USER_ID ? "moi" : "autre",
					message.getDate_message()));
		}
		return messages;
	}

	/*
	 * Ajoute un message à une conversation existante (en POST)
	 */
	@PostMapping(path = "/add")
	public ResponseEntity<Object> addMessage(@RequestBody MessageInfoAdd messageInfo) {
		Long conversationID = messageInfo.getConversationID();
		String message = messageInfo.getMessage();

		if (message.length() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("C'est honteux, le message est vide !");
		}

		messageDao.addMessage(conversationID, message, USER_ID);
		return ResponseEntity.ok(true);

	}
}
