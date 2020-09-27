package com.travel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.agent.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	
}