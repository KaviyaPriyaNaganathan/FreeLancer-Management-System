package com.freelancing.dto.request;

import java.math.BigDecimal;

import com.freelancing.enums.PostedBy;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class JobRequestDTO {

    @NotBlank(message = "Job title is required")
	private String title;
	
    
    @NotBlank(message = "Job description is required")
    private String description;
    
    
    @NotBlank(message = "Required skills are required")
    private String requiredSkills; 
    
    @NotNull(message = "PostedBy is required")
    private PostedBy postedBy; 
    
    @NotNull(message = "Budget is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0")
    private BigDecimal budget;
    
    @NotNull(message = "Project ID is required")
    @Positive(message = "Project ID must be positive")
    
    private Long projectId;
    
    

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public PostedBy getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(PostedBy postedBy) {
		this.postedBy = postedBy;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}    

	
	
}
