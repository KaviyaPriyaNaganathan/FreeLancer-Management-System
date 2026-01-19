package com.freelancing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.ReviewRequestDTO;
import com.freelancing.dto.response.ReviewResponseDTO;
import com.freelancing.service.ReviewService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/review")
@Validated
public class ReviewController {

	private final ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	
	@PostMapping
	public ReviewResponseDTO addReview(@RequestBody  @Valid ReviewRequestDTO dto)
	{
		
		return reviewService.addReview(dto);
	}
	
	@GetMapping("/freelancer/{freelancerId}")
	public List<ReviewResponseDTO> getReviewForFreelancer(@PathVariable @Positive(message = "Freelancer ID must be a positive number") Long freelancerId)
	{
		return reviewService.getReviewForFreelancer(freelancerId);
	}
	
	@GetMapping("/project/{projectId}")
	public List<ReviewResponseDTO> getReviewForProject(@PathVariable @Positive(message = "Project ID must be a positive number") Long projectId)
	{
		return reviewService.getReviewForProject(projectId);
	}
	
}
