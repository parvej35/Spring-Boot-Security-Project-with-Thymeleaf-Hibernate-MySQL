package com.travel.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.agent.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

	List<Feedback> findAll();
	
	List<Feedback> findAllByUserId(long userId);

	List<Feedback> findAllByPrivacy(int privacyId);
	
	//User findByEmail(String email);
}

