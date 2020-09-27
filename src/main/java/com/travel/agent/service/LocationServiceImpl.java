package com.travel.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.agent.model.Location;
import com.travel.agent.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
    LocationRepository locationRepository;

    public Location save(Location location){
    	Location newLocation = new Location();
    	newLocation.setName(location.getName());
        return locationRepository.save(newLocation);
    }   
    
    public List<Location> findAll() {
        //return locationRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return locationRepository.findAll();
    }

    public Location findById(long id) {
		return locationRepository.findById(id).get();
	}
}