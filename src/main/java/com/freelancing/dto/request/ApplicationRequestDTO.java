package com.freelancing.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ApplicationRequestDTO {
	
		@NotNull(message = "Job ID is required")
		@Positive(message = "Job ID must be a positive number")
	    private Long jobId;

		
		@NotNull(message = "Freelancer ID is required")
	    @Positive(message = "Freelancer ID must be a positive number")
	    private Long freelancerId;

		public Long getJobId() {
			return jobId;
		}

		public void setJobId(Long jobId) {
			this.jobId = jobId;
		}

		public Long getFreelancerId() {
			return freelancerId;
		}

		public void setFreelancerId(Long freelancerId) {
			this.freelancerId = freelancerId;
		}
	    
	    
}
