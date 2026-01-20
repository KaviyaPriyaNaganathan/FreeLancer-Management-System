package com.freelancing.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.freelancing.enums.ProjectStatus;


public class ProjectResponseDTO {

	private Long projectId;
	
	private String title;
	
	private String description;
	
	private LocalDateTime createdDate;
	
	private LocalDate deadline;
	
	private ProjectStatus status;
	private double budget;
	
	
	private Long managerId;
	private String managerName;
	
	private String managerEmail;
	
	private String managerDepartment;
	
	
	private Long backupManagerId;
	
	private String backupManagerName;
	private String backupManagerEmail;
	
	
	
	
	public ProjectResponseDTO(Long projectId, String title, String description, LocalDateTime createdDate,
			LocalDate deadline, ProjectStatus status, double budget, Long managerId, String managerName,
			String managerEmail, String managerDepartment, Long backupManagerId, String backupManagerName,
			String backupManagerEmail) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.description = description;
		this.createdDate = createdDate;
		this.deadline = deadline;
		this.status = status;
		this.budget = budget;
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerEmail = managerEmail;
		this.managerDepartment = managerDepartment;
		this.backupManagerId = backupManagerId;
		this.backupManagerName = backupManagerName;
		this.backupManagerEmail = backupManagerEmail;
	}
	public Long getBackupManagerId() {
		return backupManagerId;
	}
	public void setBackupManagerId(Long backupManagerId) {
		this.backupManagerId = backupManagerId;
	}
	public String getBackupManagerName() {
		return backupManagerName;
	}
	public void setBackupManagerName(String backupManagerName) {
		this.backupManagerName = backupManagerName;
	}
	public String getBackupManagerEmail() {
		return backupManagerEmail;
	}
	public void setBackupManagerEmail(String backupManagerEmail) {
		this.backupManagerEmail = backupManagerEmail;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}

	public ProjectResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerDepartment() {
		return managerDepartment;
	}
	public void setManagerDepartment(String managerDepartment) {
		this.managerDepartment = managerDepartment;
	}
	
}
