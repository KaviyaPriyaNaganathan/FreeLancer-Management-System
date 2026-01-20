package com.freelancing.dto.response;

public class PrioritizedFreelancerDTO {

	
	private Long freelancerId;
	
    private String freelancerName;
    
    private Double averageRating;
    
    private Long totalReviews;
    
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
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	public Long getTotalReviews() {
		return totalReviews;
	}
	public void setTotalReviews(Long totalReviews) {
		this.totalReviews = totalReviews;
	}

    
}
