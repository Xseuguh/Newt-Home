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
import org.wtg.entities.Message;
import org.wtg.entities.MessageInfoAdd;
import org.wtg.entities.MessageInfoGet;

@Controller
@RequestMapping(path = "/messagerie")
public class MessagerieController {
	private int USER_ID = 1; // A MODIFIER UNE FOIS QUE LES SESSIONS DES UTILISATEURS SERONT EN PLACE: ON
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

	/*
	 * Récupére les messages d'une conversation via son id (en GET)
	 */
	@GetMapping(path = "/getMessages")
	public @ResponseBody List<MessageInfoGet> getMessages(@RequestParam Integer conversationID) {
		List<Message> rawMessages = messageDao.findMessagesByConversationID(conversationID);
		List<MessageInfoGet> messages = new ArrayList<>();
		for (Message message : rawMessages) {
			messages.add(new MessageInfoGet(message.getMsg(), message.getId_origine() == USER_ID ? "moi" : "autre",
					message.getDate_message()));
		}
		return messages;
	}

	/*
	 * Ajoute un message à une conversation existante ou en crée une nouvelle (en
	 * POST)
	 */
	@PostMapping(path = "/add")
	public ResponseEntity<Object> addMessage(
			@RequestBody MessageInfoAdd messageInfo
//			Integer conversationID, Integer receiverID, String message
			) {
				Integer conversationID = messageInfo.getConversationID();
				Integer receiverID = messageInfo.getReceiverID();
				String message = messageInfo.getMessage();

		if (message.length() > 0) {
			if (conversationID == null) { // Si la conversation n'est pas passé en requête, on en crée une nouvelle
				if (receiverID == null) {
					System.out.println("TOTOTOTOOTO");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Oups, le destinataire du message n'est pas défini...");
				}
				// On vérifie quand même que le couple (user, receiver) n'existe pas dans la bdd
				Integer convPossibleID = conversationDao.getConversationByIdCouple(USER_ID, receiverID);
				if (convPossibleID == null) {
					conversationDao.addConversation(USER_ID, receiverID);
					conversationID = conversationDao.getIDLastInsert();
				}
				else {
					conversationID = convPossibleID;
				}
			}
			messageDao.addMessage(conversationID, message, USER_ID);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("C'est honteux, le message est vide !");
	}
}
