package com.travel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.agent.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
	Location findByName(String name);
	
}
