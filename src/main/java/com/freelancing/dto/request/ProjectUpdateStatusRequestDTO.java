package com.freelancing.dto.request;

import com.freelancing.enums.ProjectStatus;

import jakarta.validation.constraints.NotNull;

public class ProjectUpdateStatusRequestDTO {
    
	@NotNull(message = "Project status is required")
	private ProjectStatus status;

	public ProjectUpdateStatusRequestDTO(ProjectStatus status) {
		super();
		this.status = status;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}
	
	
}
