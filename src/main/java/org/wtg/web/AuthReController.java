package org.wtg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wtg.dao.RoleRepository;
import org.wtg.dao.UserRepository;
import org.wtg.entities.CustomUserDetails;
import org.wtg.entities.Role;
import org.wtg.entities.SignUpForm;
import org.wtg.entities.User;
import org.wtg.services.CustomUserDetailsService;

@Controller
public class AuthReController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	/**
     * Create new signUpForm object for empty from
     * 
     * @return
     */
    @ModelAttribute("signUpForm")
    public SignUpForm setSignUpForm() {
        return new SignUpForm();
    }
    
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		User user = new User();
	    model.addAttribute("user", user);
	    
	    return "AuthRe";
	}
	
	
	
	/**
     * Save User sign up form
     * 
     * @param signUpForm
     * @param model
     * @return
     */
    @PostMapping("/saveSignUpForm")
    public String saveUser(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {

    	//Role roleUser = roleRepo.findByName("User");
    	Role roleUser = roleRepo.findAll().get(0);
    	User user = new User();
    	
        user.setNom(signUpForm.getNom());
        user.setPrenom(signUpForm.getPrenom());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpForm.getPassword()));
        user.setEmail(signUpForm.getEmail());
        user.addRole(roleUser);
        
        

        userRepo.save(user);
        model.addAttribute("message", "User SignUp successfully.");
        model.addAttribute("user", signUpForm);

        return "redirect:/Accueil";
    }
	
	
}
