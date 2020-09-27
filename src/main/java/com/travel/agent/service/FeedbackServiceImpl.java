package com.travel.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.agent.model.Feedback;
import com.travel.agent.repository.FeedbackRepository;
import com.travel.agent.web.dto.FeedbackDto;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public Feedback save(FeedbackDto feedbackDto){
    	Feedback newObj = new Feedback();
    	newObj.setUserId(feedbackDto.getUserId());
    	newObj.setLocationId(feedbackDto.getLocationId());
    	newObj.setStatus(feedbackDto.getStatus());
    	newObj.setPrivacy(feedbackDto.getPrivacy());
    	
        return feedbackRepository.save(newObj);
    }
	
	public List<Feedback> findAll() {
       return feedbackRepository.findAll();
   }
	
	public List<Feedback> findAllByUserId(long userId) {
        return feedbackRepository.findAllByUserId(userId);
    }
	
	public List<Feedback> findAllByPrivacy(int privacyId) {
        return feedbackRepository.findAllByPrivacy(privacyId);
    }

	public Feedback findById(long id) {
		return feedbackRepository.findById(id).get();
	}

	public Feedback update(FeedbackDto feedbackDto) {
		Feedback newObj = new Feedback();
		
		newObj.setId(feedbackDto.getId());
    	newObj.setUserId(feedbackDto.getUserId());
    	newObj.setLocationId(feedbackDto.getLocationId());
    	newObj.setStatus(feedbackDto.getStatus());
    	newObj.setPrivacy(feedbackDto.getPrivacy());
    	
        return feedbackRepository.save(newObj);
	}

}
