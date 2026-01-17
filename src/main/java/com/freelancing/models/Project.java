package com.freelancing.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.freelancing.enums.ProjectStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false, length = 1000)
	private String description;
	
	@Column(nullable = false)
	private LocalDateTime createdDate;
	
	@Column(nullable = false)
	private LocalDate deadline;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length =40)
	private ProjectStatus status = ProjectStatus.NEW;
	
	@ManyToOne
	@JoinColumn(name = "manager_id", nullable = false)
	private Manager manager;
	
	@ManyToMany(mappedBy = "appliedProjects")
	private List<Freelancer> assignedFreelancers;
	
	@Column(nullable = false)
	private double budget;


	public Project(Long projectId, String title, String description, LocalDateTime createdDate, LocalDate deadline,
			ProjectStatus status, double budget) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.description = description;
		this.createdDate = LocalDateTime.now();
		this.deadline = deadline;
		this.status = ProjectStatus.NEW;
		this.budget = budget;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public Project() {
		this.createdDate = LocalDateTime.now();
		this.status = ProjectStatus.NEW;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}


	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Freelancer> getAssignedFreelancers() {
		return assignedFreelancers;
	}

	public void setAssignedFreelancers(List<Freelancer> assignedFreelancers) {
		this.assignedFreelancers = assignedFreelancers;
	}
	
	
}


