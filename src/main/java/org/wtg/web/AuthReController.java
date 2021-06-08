package org.wtg.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.wtg.dao.RoleRepository;
import org.wtg.dao.UserRepository;
import org.wtg.entities.Role;
import org.wtg.entities.CustomUserDetails;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			return "redirect:/Accueil";
		}
	    return "AuthRe";
	}
	
	
    @PostMapping("/saveSignUpForm")
    public String saveUser(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {

    	Date d = new Date();
    	if((d.getTime() - signUpForm.getBirthday().getTime()) < 60*60*24*365*18*1000) {
    		return "redirect:/register";
    	}
    	else {
    		UserInfo user = createNormalUser(signUpForm);
    		userRepo.save(user);
    		authWithoutPassword(user);
            return "redirect:/Accueil";
    	}
        
    }
    
    public void authWithoutPassword(UserInfo user){
        
        CustomUserDetails CUD = new CustomUserDetails(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, CUD.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
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
	
	public static Long getId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == "anonymousUser") {
			return null;

		}
		else if(principal instanceof UserInfo) {
			CustomUserDetails CUD = new CustomUserDetails((UserInfo) principal);
			
			return CUD.getId();
		}
		CustomUserDetails CUD = (CustomUserDetails) principal;
		return CUD.getId();
	}
	
}
