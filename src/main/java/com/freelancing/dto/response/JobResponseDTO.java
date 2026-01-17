package com.freelancing.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.freelancing.enums.JobStatus;
import com.freelancing.enums.PostedBy;


public class JobResponseDTO {

	private Long jobId;
	
    private String title;
    
    private String description;
    
    private String requiredSkills;
    
    private PostedBy postedBy;
    
    private BigDecimal budget;
    
    private JobStatus status;
    
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;



	private Long projectId;
    private String projectTitle;
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
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
	public JobStatus getStatus() {
		return status;
	}
	public void setStatus(JobStatus status) {
		this.status = status;
	}
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
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
