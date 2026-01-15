package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.ReviewRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}
	
	

}
