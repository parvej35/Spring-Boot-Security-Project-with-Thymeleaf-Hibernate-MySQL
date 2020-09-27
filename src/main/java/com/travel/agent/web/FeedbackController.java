package com.travel.agent.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.agent.Constants;
import com.travel.agent.model.Feedback;
import com.travel.agent.model.Location;
import com.travel.agent.model.User;
import com.travel.agent.service.FeedbackService;
import com.travel.agent.service.LocationService;
import com.travel.agent.service.UserService;
import com.travel.agent.web.dto.CustomFeedbackDto;
import com.travel.agent.web.dto.FeedbackDto; 

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	
    @Autowired
	FeedbackService feedbackService;
	
    @Autowired
	LocationService locationService;
	
    @Autowired
	UserService userService;
    
    private static final Logger logger = Logger.getLogger(FeedbackController.class.getName());
    
    @RequestMapping
    public String showFeedback(Model model) {
    	logger.log(Level.INFO, "Redirecting feedback page.");
    	
    	List<Location> locationList = locationService.findAll();
    	
    	model.addAttribute("locationList", locationList);
    	
        return "feedback";
    }

    @PostMapping
    public String save(@ModelAttribute("feedback") @Validated FeedbackDto feedbackDto, BindingResult result){

    	if(feedbackDto.getLocationId() == 0) {
			return "redirect:/feedback?error&m=Select Checkin";
		}
		
		if(feedbackDto.getStatus().length() == 0) {
			return "redirect:/feedback?error&m=Write status";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String id = ""; 
		if (auth.getPrincipal() instanceof UserDetails)
        	id = ((UserDetails) auth.getPrincipal()).getUsername();
        else if (auth.getPrincipal() instanceof String)
        	id = (String) auth.getPrincipal();
		
		logger.log(Level.INFO, "Login user email " + id);
		
		User user = userService.findByEmail(id);
    	
    	if (user == null){
    		return "redirect:/login";
    	} else {    	
    		
    		feedbackDto.setUserId(user.getId());
    		
    		feedbackService.save(feedbackDto);
	        
	        logger.log(Level.INFO, "New post saved");
	        
	        return "redirect:/feedback?success&m=Feedback saved";
    	}
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String emailId = ""; 
		if (auth.getPrincipal() instanceof UserDetails)
			emailId = ((UserDetails) auth.getPrincipal()).getUsername();
        else if (auth.getPrincipal() instanceof String)
        	emailId = (String) auth.getPrincipal();
		
		List<Feedback> feedbackList;
		
    	if(emailId == Constants.ADMIN_EMAIL) {
    		feedbackList = feedbackService.findAll();
    	} else {
    		User user = userService.findByEmail(emailId);
    		
    		feedbackList = feedbackService.findAllByUserId(user.getId());
    	}
    	
    	CustomFeedbackDto customFeedback = null; 
    	
    	User user;
    	Location location;
    	List<CustomFeedbackDto> customFeedbackList = new ArrayList<CustomFeedbackDto>();
    	
    	for (Feedback feedback : feedbackList) {
    		user = userService.findById(feedback.getUserId());
    		
    		location = locationService.findById(feedback.getLocationId());
    		
    		customFeedback = new CustomFeedbackDto();
    		
    		customFeedback.setId(feedback.getId());
    		customFeedback.setUsername(user.getFullname());
    		customFeedback.setStatus(feedback.getStatus());
    		customFeedback.setPrivacy((feedback.getPrivacy() == 1) ? "Public" : "Private");
    		customFeedback.setLocation(location.getName());
    		
    		customFeedbackList.add(customFeedback);
        } 
    	
    	model.addAttribute("customFeedbackList", customFeedbackList);
    	
        return "feedbacklist";
    }
    
    @RequestMapping("/show/{id}")
    public String get(@PathVariable("id") int id, Model model) {
    	
    	System.out.println(id);
		
    	Feedback feedback = feedbackService.findById(id);
    	
    	List<Location> locationList = locationService.findAll();
    	
    	System.out.println("Toal " + locationList.size());
    	
    	model.addAttribute("locationList", locationList);
    	
    	model.addAttribute("feedback", feedback);
    	
        return "showFeedback";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute("feedback") @Validated FeedbackDto feedbackDto, BindingResult result){

    	if(feedbackDto.getLocationId() == 0) {
			return "redirect:/showFeedback?error&m=Select Checkin";
		}
		
		if(feedbackDto.getStatus().length() == 0) {
			return "redirect:/showFeedback?error&m=Write status";
		}
		
		System.out.println("feedbackDto.getId() : " + feedbackDto.getId());
		Feedback oldFeedback = feedbackService.findById(feedbackDto.getId());
		System.out.println("oldFeedback.getId() : " + oldFeedback.getId());
		
		feedbackDto.setId(oldFeedback.getId());
		feedbackDto.setUserId(oldFeedback.getUserId());
		
		feedbackService.save(feedbackDto);
        
        logger.log(Level.INFO, "Post modified");
        
        return "redirect:/feedback/list";
    }

}
