package com.travel.agent.service;

import java.util.List;

import com.travel.agent.model.Location;

public interface LocationService {
	
	Location findById(long id);

	Location save(Location location);
		
    List<Location> findAll();

}