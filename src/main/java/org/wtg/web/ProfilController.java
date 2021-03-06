package org.wtg.web;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wtg.dao.OffresPostuleesRepository;
import org.wtg.dao.OffresRepository;
import org.wtg.dao.UserInfoRepository;
import org.wtg.entities.CustomUserDetails;
import org.wtg.entities.JoinOffresOffresPostulees;
import org.wtg.entities.Offres;
import org.wtg.entities.Settings;
import org.wtg.entities.UserInfo;
import org.wtg.entities.UserInfoLight;

@Controller
@RequestMapping(path = "/profil")
public class ProfilController {
//	private long USER_ID = 1l;
	@Autowired
	private UserInfoRepository userDao;
	@Autowired
	private OffresRepository offreDao;
	@Autowired
	private OffresPostuleesRepository postulaDAO;

	private Long getId() {
		return AuthReController.getId();
	}

	@GetMapping(path = "/")
	public String profil() {
		return "Profil";
	}

	@GetMapping(path = "/getAnnonces")
	public ResponseEntity<List<Offres>> getAnnonces() {
		return ResponseEntity.ok(offreDao.findByUserId(getId()));
	}

	@PostMapping(path = "/approveUser")
	public ResponseEntity<String> approveUser(long offreID, long userID) {
		offreDao.setPourvuToTrue(offreID);
		postulaDAO.acceptUserForOffer(offreID, userID);
		postulaDAO.refuseAllUserExceptOneForOffer(offreID, userID);
		return ResponseEntity.ok("Tout s'est bien pass??");
	}

	@PostMapping(path = "/refuseUser")
	public ResponseEntity<String> refuseUser(long offreID, long userID) {
		postulaDAO.refuseUserForOffer(offreID, userID);
		return ResponseEntity.ok("Tout s'est bien pass??");
	}

	@GetMapping(path = "/getUserWhoApply")
	public ResponseEntity<List<UserInfoLight>> getUserWhoApply(@RequestParam Long offreID) {
		return ResponseEntity.ok(postulaDAO.findUserWhoApply(offreID));
	}

	@GetMapping(path = "/getReservations")
	public ResponseEntity<List<JoinOffresOffresPostulees>> getReservations() {
		return ResponseEntity.ok(postulaDAO.findByUserId(getId()));
	}


	@GetMapping(path = "/getInfo")
	public ResponseEntity<UserInfo> getUserInfo() {
		return ResponseEntity.ok(userDao.findById(getId()).get());
	}

	@PostMapping(path = "/updateInfo")
	public ResponseEntity<String> updateInfo(Settings newSettings) {
		String email = newSettings.getEmail();
		if (!isEmailValid(email)) {
			return ResponseEntity.status(500).body("L'email est invalide !");
		}
		userDao.updateEmail(email, getId());

		String newPassword = newSettings.getNewPassword();
		if (newPassword != null) {
			if (!isPasswordValid(newPassword)) {
				return ResponseEntity.status(500).body("Le nouveau mot de passe n'est pas valide !");
			}
			String passwordInDBB = userDao.getPassword(getId());
			// TODO Hasher le mot de passe pour tester
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if (!encoder.matches(newSettings.getOldPassword(), passwordInDBB)) {
				return ResponseEntity.status(500).body("L'ancien mot de passe ne correspond pas !");
			}
			userDao.updatePassword(encoder.encode(newPassword), getId());
		}

		return ResponseEntity.ok("Tout s'est bien pass??");
	}

	// TODO Ranger ??a au bon endroit
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
		
		
		offreDao.removeOffer(offreID, getId());
		
		String path = "/src/main/resources/static/images/photosAnnonces/";
	    String OS = System.getProperty("os.name").toLowerCase();

		if (!OS.equals("mac os x") && !OS.equals("linux")) {
			path = path.replace("/", "\\");
		}
		
		String userDir=System.getProperty("user.dir");
		String whereForUser = userDir+ path +offreID;
		File folder = new File(whereForUser);
		try {
			FileUtils.deleteDirectory(folder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		return ResponseEntity.ok("Tout s'est bien pass??");
	}

	@PostMapping(path = "remove/reservations")
	public ResponseEntity<String> removeApply(long offreID) {
		postulaDAO.removeUserFromOffer(offreID, getId());
		return ResponseEntity.ok("Tout s'est bien pass??");
	}

	@PostMapping(path = "/updateAvatar")
	public ResponseEntity<String> updateAvatar(String avatar) {
		String partSeparator = ",";
		System.out.println(avatar);

		if (!avatar.contains(partSeparator)) {
			return ResponseEntity.status(500).body("Oops quelque chose s'est mal pass?? !");
		}

			String encodedImg = avatar.split(partSeparator)[1];
			byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
			if (decodedImg.length > 1000000) {
				return ResponseEntity.status(500).body("L'image est trop grande !");
			}
			Path destinationFile = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/user",
					getId() + ".png");
			try {
				Files.write(destinationFile, decodedImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok("Tout s'est bien pass??");

	}

}
