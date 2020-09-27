package com.travel.agent.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.agent.model.Feedback;
import com.travel.agent.model.Location;
import com.travel.agent.model.User;
import com.travel.agent.service.FeedbackService;
import com.travel.agent.service.LocationService;
import com.travel.agent.service.UserService;
import com.travel.agent.web.dto.CustomFeedbackDto;

import java.util.logging.Level; 
import java.util.logging.Logger; 

@Controller
public class MainController {
	
	@Autowired
	FeedbackService feedbackService;
	
    @Autowired
	LocationService locationService;
	
    @Autowired
	UserService userService;
    
    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    
    @RequestMapping("/login")
	public String login() {
		
		logger.log(Level.INFO, "Redirect to login page");
        
		return "login";
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		
		logger.log(Level.INFO, "Redirect to home page");
		
		List<Feedback> feedbackList = feedbackService.findAllByPrivacy(1);
		
		CustomFeedbackDto customFeedback = null; 
    	
    	User user;
    	Location location;
    	List<CustomFeedbackDto> customFeedbackList = new ArrayList<CustomFeedbackDto>();
    	
    	for (Feedback feedback : feedbackList) {
    		user = userService.findById(feedback.getUserId());
    		
    		location = locationService.findById(feedback.getLocationId());
    		
    		customFeedback = new CustomFeedbackDto();
    		
    		customFeedback.setUsername(user.getFullname());
    		customFeedback.setStatus(feedback.getStatus());
    		customFeedback.setPrivacy((feedback.getPrivacy() == 1) ? "Public" : "Private");
    		customFeedback.setLocation(location.getName());
    		
    		customFeedbackList.add(customFeedback);
    		
        } 
    	
    	logger.log(Level.INFO, "Total post : " + customFeedbackList.size());
    	
    	model.addAttribute("customFeedbackList", customFeedbackList);
    	
		return "index";
	}
	
	
	@RequestMapping("/accessdenied")
	public String accessdenied() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String id = ""; 
		if (auth.getPrincipal() instanceof UserDetails)
        	id = ((UserDetails) auth.getPrincipal()).getUsername();
        else if (auth.getPrincipal() instanceof String)
        	id = (String) auth.getPrincipal();
		
		System.out.println("<br>id : "+id);
		
		if (auth != null) {
			System.out.println("User '" + auth.getName() + "' attempted to access the protected URL: ");
			System.out.println("<br>auth : "+auth.isAuthenticated());
			System.out.println("<br>Role : "+auth.getAuthorities());
		}

		
		return "accessdenied";
	}
}
