package com.travel.agent;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.travel.agent.model.Location;
import com.travel.agent.model.Role;
import com.travel.agent.model.User;
import com.travel.agent.repository.LocationRepository;
import com.travel.agent.repository.UserRepository;
import com.travel.agent.service.UserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(UserService userService, UserRepository userRepo, LocationRepository locaRepo) {
	    return (args) -> {
	    	
	    	User existing = userRepo.findByEmail(Constants.ADMIN_EMAIL);
	        if (existing == null){
	        	//roleRepo.save(new Role("Admin"));
		    	//roleRepo.save(new Role("Contributor"));
		    	
		    	User user = new User(Constants.ADMIN_FULLNAME, Constants.ADMIN_EMAIL, Constants.ADMIN_PASSWORD, 
						Arrays.asList(new Role(Constants.ADMIN)));
		    	
		    	userService.saveAdmin(user);
		    	
		    	locaRepo.save(new Location("Dhaka"));
		    	locaRepo.save(new Location("Sylhet"));
		    	locaRepo.save(new Location("Chittagonj"));
	        }
	    	
	    	

	        
	    };
	}

}
