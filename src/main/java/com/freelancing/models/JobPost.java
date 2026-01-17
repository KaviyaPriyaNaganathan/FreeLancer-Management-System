package com.freelancing.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.freelancing.enums.JobStatus;
import com.freelancing.enums.PostedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_posts")
public class JobPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false, length =1000)
	private String description;
	
	@Column(nullable=false)
	private String requiredSkills;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private PostedBy postedBy;
	
	@Column(nullable=false)
	private BigDecimal budget;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private JobStatus status = JobStatus.OPEN;
	
	@ManyToOne
	@JoinColumn(name="project_id", nullable = false)
	private Project project;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	public JobPost(Long jobId, String title, String description, String requiredSkills, PostedBy postedBy,
			BigDecimal budget, JobStatus status, Project project, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.jobId = jobId;
		this.title = title;
		this.description = description;
		this.requiredSkills = requiredSkills;
		this.postedBy = postedBy;
		this.budget = budget;
		this.status = JobStatus.OPEN;
		this.project = project;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}


	public JobPost() {
		super();
		this.status = JobStatus.OPEN;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public Long getJobId() {
		return jobId;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

}
