package com.freelancing.dto.request;

import com.freelancing.enums.JobStatus;


public class JobUpdateStatusRequestDTO {
	
	private JobStatus status;

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}
	
	
}
