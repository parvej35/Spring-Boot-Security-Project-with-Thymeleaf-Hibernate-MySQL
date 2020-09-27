package com.travel.agent.service;

import java.util.List;

import com.travel.agent.model.Feedback;
import com.travel.agent.web.dto.FeedbackDto;

public interface FeedbackService {

	Feedback save(FeedbackDto feedbackDto);
	
	Feedback update(FeedbackDto feedbackDto);
	
	Feedback findById(long id);
	
	List<Feedback> findAllByUserId(long userId);
	
	List<Feedback> findAll();
	
	List<Feedback> findAllByPrivacy(int privacyId);
	
}
