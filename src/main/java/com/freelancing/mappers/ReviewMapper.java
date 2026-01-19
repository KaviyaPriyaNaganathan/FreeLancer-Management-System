package com.freelancing.mappers;

import java.time.LocalDateTime;

import com.freelancing.dto.request.ReviewRequestDTO;
import com.freelancing.dto.response.ReviewResponseDTO;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.models.Review;

public class ReviewMapper {

	
	public static Review toEntity(ReviewRequestDTO dto, Freelancer freelancer, JobPost job)
	{
		Review review = new Review();
		review.setJob(job);
		review.setProject(job.getProject());
		review.setFreelancer(freelancer);
		review.setRating(dto.getRating());
		review.setComments(dto.getComment());
		review.setReviewedDate(LocalDateTime.now());
		return review;
	}
	public static ReviewResponseDTO toResponse(Review dto)
	{
		ReviewResponseDTO response = new ReviewResponseDTO();
		response.setReviewId(dto.getReviewId());
		response.setFreelancerId(dto.getFreelancer().getFreelancerId());
		response.setFreelancerName(dto.getFreelancer().getName());
		response.setJobId(dto.getJob().getJobId());
		response.setJobTitle(dto.getJob().getTitle());
		response.setRating(dto.getRating());
		response.setComment(dto.getComments());
		response.setCreatedAt(dto.getReviewedDate());
		response.setProjectId(dto.getProject().getProjectId());
		response.setProjectTitle(dto.getProject().getTitle());
		
		return response;
	}
}
