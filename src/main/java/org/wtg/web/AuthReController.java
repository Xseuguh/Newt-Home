package org.wtg.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.wtg.dao.RoleRepository;
import org.wtg.dao.UserRepository;
import org.wtg.entities.Role;
import org.wtg.entities.SignUpForm;
import org.wtg.entities.UserInfo;

@Controller
public class AuthReController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	
    @ModelAttribute("signUpForm")
    public SignUpForm setSignUpForm() {
        return new SignUpForm();
    }
    
	
	@GetMapping("/register")
	public String showRegistrationForm() {
	    
	    return "AuthRe";
	}
	
	
    @PostMapping("/saveSignUpForm")
    public String saveUser(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {

    	Date d = new Date();
    	if((d.getTime() - signUpForm.getBirthday().getTime()) < 60*60*24*365*18*1000) {
    		return "redirect:/register";
    	}
    	else {
    		userRepo.save(createNormalUser(signUpForm));
            return "redirect:/Accueil";
    	}
        
    }
    
    public UserInfo createNormalUser(SignUpForm signUpForm) {
    	
    	Role roleUser = roleRepo.findAll().get(0);
    	UserInfo user = new UserInfo();
    	
    	user.setNom(signUpForm.getNom());
        user.setPrenom(signUpForm.getPrenom());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpForm.getPassword()));
        user.setMail(signUpForm.getEmail());
        user.setBirthday(signUpForm.getBirthday());
        user.setAdmin(false);
        user.addRole(roleUser);
    	
    	return user;
    }
	
	
}
