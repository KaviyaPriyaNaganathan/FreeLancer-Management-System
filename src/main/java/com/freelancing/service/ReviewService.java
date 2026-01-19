package com.freelancing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ReviewRequestDTO;
import com.freelancing.dto.response.ReviewResponseDTO;

public interface ReviewService {

	ReviewResponseDTO addReview(ReviewRequestDTO dto);

	List<ReviewResponseDTO> getReviewForFreelancer(Long freelancerId);

	List<ReviewResponseDTO> getReviewForProject(Long projectId);

	

}
