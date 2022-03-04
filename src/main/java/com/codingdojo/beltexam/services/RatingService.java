package com.codingdojo.beltexam.services;

import org.springframework.stereotype.Service;

import com.codingdojo.beltexam.models.Rating;
import com.codingdojo.beltexam.repositories.RatingRepository;

@Service
public class RatingService {
	
	private final RatingRepository ratingRepo;
	public RatingService(RatingRepository ratingRepo) {
		this.ratingRepo=ratingRepo;
	}
		
	public Rating createRating(Rating rating) {
		return ratingRepo.save(rating);		
	}
}
