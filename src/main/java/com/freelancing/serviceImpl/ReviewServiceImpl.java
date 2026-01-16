package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.ReviewRepository;
import com.freelancing.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService{

	private final ReviewRepository reviewRepository;

	@Autowired
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}
	
	
}
