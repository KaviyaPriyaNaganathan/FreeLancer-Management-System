package com.freelancing.dto.response;

import java.time.LocalDateTime;

import com.freelancing.models.Project;

public class ReviewResponseDTO {


	    private Long reviewId;

	    private Long freelancerId;
	    
	    private String freelancerName;

	    private Long jobId;
	    
	    private String jobTitle;

	    private int rating;
	    
	    private String comment;

	    
	    private LocalDateTime createdAt;
	    
	    private Long projectId;
	    private String projectTitle;



		public Long getProjectId() {
			return projectId;
		}

		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}

		public String getProjectTitle() {
			return projectTitle;
		}

		public void setProjectTitle(String projectTitle) {
			this.projectTitle = projectTitle;
		}

		public Long getReviewId() {
			return reviewId;
		}

		public void setReviewId(Long reviewId) {
			this.reviewId = reviewId;
		}

		public Long getFreelancerId() {
			return freelancerId;
		}

		public void setFreelancerId(Long freelancerId) {
			this.freelancerId = freelancerId;
		}

		public String getFreelancerName() {
			return freelancerName;
		}

		public void setFreelancerName(String freelancerName) {
			this.freelancerName = freelancerName;
		}

		public Long getJobId() {
			return jobId;
		}

		public void setJobId(Long jobId) {
			this.jobId = jobId;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

	

}
