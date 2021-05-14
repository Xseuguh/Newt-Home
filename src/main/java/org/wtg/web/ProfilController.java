package org.wtg.web;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.OffresPostuléesRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.dao.UserInfoRepository;
import org.wtg.entities.JoinOffresOffresPostulees;
import org.wtg.entities.MessageInfoAdd;
import org.wtg.entities.Offres;
import org.wtg.entities.Settings;
import org.wtg.entities.UserInfo;
import org.wtg.entities.UserInfoLight;

@Controller
@RequestMapping(path = "/profil")
public class ProfilController {
	private long USER_ID = 1l; // A MODIFIER UNE FOIS QUE LES SESSIONS DES UTILISATEURS SERONT EN PLACE: ON
								// DOIT RÉCUPÉRER L'ID DE L'USER
	@Autowired
	private UserInfoRepository userDao;
	@Autowired
	private OffresRepository offreDao;
	@Autowired
	private OffresPostuléesRepository postulaDAO;

	@GetMapping(path = "/")
	public String profil() {
		return "Profil";
	}

	@GetMapping(path = "/getAnnonces")
	public ResponseEntity<List<Offres>> getAnnonces() {
		return ResponseEntity.ok(offreDao.findByUserId(USER_ID));
	}

	@PostMapping(path = "/approveUser")
	public ResponseEntity<String> approveUser(long offreID, long userID) {
		offreDao.setPourvuToTrue(offreID);
		postulaDAO.setAccepteToTrue(offreID, userID);
		return ResponseEntity.ok("Tout s'est bien passé");
	}

	@PostMapping(path = "/refuseUser")
	public ResponseEntity<String> refuseUser(long offreID, long userID) {
		postulaDAO.removeUserFromOffer(offreID, userID);
		return ResponseEntity.ok("Tout s'est bien passé");
	}

	@GetMapping(path = "/getUserWhoApply")
	public ResponseEntity<List<UserInfoLight>> getUserWhoApply(@RequestParam Long offreID) {
		return ResponseEntity.ok(postulaDAO.findUserWhoApply(offreID));
	}

	@GetMapping(path = "/getReservations")
	public ResponseEntity<List<JoinOffresOffresPostulees>> getReservations() {
		return ResponseEntity.ok(postulaDAO.findByUserId(USER_ID));
	}


	@GetMapping(path = "/getInfo")
	public ResponseEntity<UserInfo> getUserInfo() {
		return ResponseEntity.ok(userDao.findById(USER_ID).get());
	}

	@PostMapping(path = "/updateInfo")
	public ResponseEntity<String> updateInfo(Settings newSettings) {
		String email = newSettings.getEmail();
		if (!isEmailValid(email)) {
			return ResponseEntity.status(500).body("L'email est invalide !");
		}
		userDao.updateEmail(email, USER_ID);

		String newPassword = newSettings.getNewPassword();
		if (newPassword != null) {
			if (!isPasswordValid(newPassword)) {
				return ResponseEntity.status(500).body("Le nouveau mot de passe n'est pas valide !");
			}
			String passwordInDBB = userDao.getPassword(USER_ID);
			// TODO Hasher le mot de passe pour tester
			if (!newSettings.getOldPassword().equals(passwordInDBB)) {
				return ResponseEntity.status(500).body("L'ancien mot de passe ne correspond pas !");
			}
			userDao.updatePassword(newPassword, USER_ID);
		}

		return ResponseEntity.ok("Tout s'est bien passé");
	}

	// TODO Ranger ça au bon endroit
	private boolean isPasswordValid(String newPassword) {
		return newPassword.length() >=8 
				&& Pattern.compile("[a-z]").matcher(newPassword).find()
				&& Pattern.compile("[A-Z]").matcher(newPassword).find()
				&& Pattern.compile("[0-9]").matcher(newPassword).find();
		
	}

	private boolean isEmailValid(String email) {
		if (email.length() < 2 || !email.contains("@")) {
			return false;
		}
		return true;
	}
	// Fin TODO

	@PostMapping(path = "remove/annonces")
	public ResponseEntity<String> removeAnnonce(long offreID) {
		offreDao.removeOffer(offreID, USER_ID);
		return ResponseEntity.ok("Tout s'est bien passé");
	}

	@PostMapping(path = "remove/reservations")
	public ResponseEntity<String> removeApply(long offreID) {
		postulaDAO.removeUserFromOffer(offreID, USER_ID);
		return ResponseEntity.ok("Tout s'est bien passé");
	}

	@PostMapping(path = "/updateAvatar")
	public ResponseEntity<String> updateAvatar(@RequestBody MessageInfoAdd messageInfo) {

		return ResponseEntity.ok("Tout s'est bien passé");
	}

}
