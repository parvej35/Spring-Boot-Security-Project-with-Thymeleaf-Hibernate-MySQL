package com.travel.agent.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.agent.model.User;
import com.travel.agent.service.UserService;
import com.travel.agent.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private UserService userService;
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class.getName());

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		
		logger.log(Level.INFO, "Redirect to signup page");
		
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {
		
		if(registrationDto.getFullname().length() == 0) {
			return "redirect:/registration?error&m=Full name is required";
		}
		
		if(registrationDto.getEmail().length() == 0) {
			return "redirect:/registration?error&m=Email is required";
		}
		
		if(registrationDto.getPassword().length() == 0) {
			return "redirect:/registration?error&m=Password is required";
		}
		
		if(registrationDto.getRepassword().length() == 0) {
			return "redirect:/registration?error&m=Re enter required";
		}
		
		if(!registrationDto.getRepassword().equals(registrationDto.getPassword())) {
			return "redirect:/registration?error&m=Password mismatch";
		}
		
		User existing = userService.findByEmail(registrationDto.getEmail());
        if (existing != null){
        	return "redirect:/registration?error&m=Email is already registered";
        }
        
        logger.log(Level.INFO, "Validation Success. Now creating user....");
        
        userService.save(registrationDto);
        
        logger.log(Level.INFO, "User registration completed.");
        
        return "redirect:/feedback?success&m=User successfully registered";
	}
}
