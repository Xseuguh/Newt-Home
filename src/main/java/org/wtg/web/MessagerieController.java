package org.wtg.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wtg.dao.ConversationRepository;
import org.wtg.dao.MessageRepository;
import org.wtg.dao.UserRepository;
import org.wtg.entities.ConversationInfo;
import org.wtg.entities.ConversationInfoAdd;
import org.wtg.entities.Message;
import org.wtg.entities.MessageInfoAdd;
import org.wtg.entities.MessageInfoGet;
import org.wtg.entities.UserInfo;

@Controller
@RequestMapping(path = "/messagerie")
public class MessagerieController {

	
//	private Long USER_ID = 1l;
	@Autowired
	private ConversationRepository conversationDao;
	@Autowired
	private MessageRepository messageDao;

	@Autowired
	private UserRepository userDao;
	private Long getId(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		if (principal == "anonymousUser") {
			return null;

		}
		String username = ((UserDetails) principal).getUsername();
		UserInfo user = userDao.findByMail(username);
		return user.getId_user();
	}

	@GetMapping(path = "/")
	public String messagerie(Model model) {
		List<ConversationInfo> conversations = conversationDao.findConversationsByUserID(getId());
		model.addAttribute("conversations", conversations);

		return "Messagerie";
	}

	@PostMapping(path = "/")
	public String messagerieWithDefaultOpenConversation(Model model, ConversationInfoAdd conversationInfo) {
		List<ConversationInfo> conversations = conversationDao.findConversationsByUserID(getId());
		model.addAttribute("conversations", conversations);

		Long receiverID = conversationInfo.getReceiverID();
		System.out.println("REC ID = " + receiverID);
		if (receiverID != null && !receiverID.equals(getId())) {
			Long conversationID = conversationDao.getConversationByIdCouple(getId(), receiverID);
			System.out.println("CONV ID = " + conversationID);
			// on vérifie qu'une conversation n'existe pas déjà entre les deux utilisateurs
			if (conversationID == null) {
				conversationDao.addConversation(getId(), receiverID);
				conversationID = conversationDao.getIDLastInsert();
				System.out.println("Nouvelle conv");
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
		return conversationDao.findConversationsByUserID(getId());
	}

	/*
	 * Récupére les messages d'une conversation via son id (en GET)
	 */
	@GetMapping(path = "/getMessages")
	public @ResponseBody List<MessageInfoGet> getMessages(@RequestParam Long conversationID) {
		List<Message> rawMessages = messageDao.findMessagesByConversationID(conversationID);
		List<MessageInfoGet> messages = new ArrayList<>();
		for (Message message : rawMessages) {
			messages.add(new MessageInfoGet(message.getMsg(), message.getId_origine() == getId() ? "moi" : "autre",
					message.getDate_message()));
		}
		return messages;
	}

	/*
	 * Ajoute un message à une conversation existante (en POST)
	 */
	@PostMapping(path = "/add")
	public ResponseEntity<Object> addMessage(MessageInfoAdd messageInfo) {
		Long conversationID = messageInfo.getConversationID();
		String message = messageInfo.getMessage();

		if (message.length() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("C'est honteux, le message est vide !");
		}

		messageDao.addMessage(conversationID, message, getId());
		return ResponseEntity.ok(true);

	}
}
