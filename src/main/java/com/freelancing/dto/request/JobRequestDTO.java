package com.freelancing.dto.request;

import java.math.BigDecimal;

import com.freelancing.enums.PostedBy;


public class JobRequestDTO {

	private String title;
	
    private String description;
    
    private String requiredSkills; 
    
    private PostedBy postedBy; 
    
    private BigDecimal budget;
    
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
