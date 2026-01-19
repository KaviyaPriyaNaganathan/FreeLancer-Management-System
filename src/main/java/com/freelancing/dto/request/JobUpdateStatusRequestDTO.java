package com.freelancing.dto.request;

import com.freelancing.enums.JobStatus;

import jakarta.validation.constraints.NotNull;


public class JobUpdateStatusRequestDTO {
	
    @NotNull(message = "Job status is required")
	private JobStatus status;

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}
	
	
}
