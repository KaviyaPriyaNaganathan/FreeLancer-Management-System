package com.freelancing.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProjectRequestDTO {
	
    @NotBlank(message = "Project title is required")
	private String title;
    
    @NotBlank(message = "Project description is required")
	private String description;
    
    @NotNull(message = "Project deadline is required")
    @Future(message = "Project deadline must be in the future")
	private LocalDate deadline;
	
    @Positive(message = "Budget must be greater than 0")
    private double budget;
	
    @NotNull(message = "Manager ID is required")
    @Positive(message = "Manager ID must be positive")
    private Long managerId;
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
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	
	
}
