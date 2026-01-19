package com.freelancing.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FreelancerRatingDTO {

	
	@NotNull(message = "Freelancer ID is required")
    @Positive(message = "Freelancer ID must be positive")
    private Long freelancerId;
    
	
	@NotBlank(message = "Freelancer name is required")
    private String freelancerName;

	
	@DecimalMin(value = "0.0", message = "Average rating must be at least 0")
    @DecimalMax(value = "5.0", message = "Average rating cannot exceed 5")
    private double averageRating;
    
	@Min(value = 0, message = "Total reviews must be 0 or more")
    private long totalReviews;

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

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public long getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(long totalReviews) {
		this.totalReviews = totalReviews;
	}
    
    
	
}
