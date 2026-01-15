package com.freelancing.models;

import java.time.LocalDateTime;

import com.freelancing.enums.ApplicationStatus;

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
@Table(name = "applications")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicationId;
	
	@ManyToOne
	@JoinColumn(name ="job_id", nullable = false)
	private JobPost job;
	
	@ManyToOne
	@JoinColumn(name ="freelancer_id", nullable = false)
	private Freelancer freelancer;
	
	@Column(nullable = false)
	private LocalDateTime applicationDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationStatus status = ApplicationStatus.APPLIED;

	public Application(Long applicationId, JobPost job, Freelancer freelancer, LocalDateTime applicationDate,
			ApplicationStatus status) {
		super();
		this.applicationId = applicationId;
		this.job = job;
		this.freelancer = freelancer;
		this.applicationDate = LocalDateTime.now();
		this.status = ApplicationStatus.APPLIED;
	}

	public Application() {
		super();
		this.status = ApplicationStatus.APPLIED;
		this.applicationDate = LocalDateTime.now();
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public JobPost getJob() {
		return job;
	}

	public void setJobId(JobPost job) {
		this.job = job;
	}

	public Freelancer getFreelancerId() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer= freelancer;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
	
	
}
