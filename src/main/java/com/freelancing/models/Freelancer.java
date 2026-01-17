package com.freelancing.models;

import java.time.LocalDateTime;
import java.util.List;

import com.freelancing.enums.FreelancerStatus;
import com.freelancing.enums.PriorityLevel;
import com.freelancing.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="freelancers")
public class Freelancer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long freelancerId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String skills;
	
	@Column(nullable = false)
	private double experienceYears;
	
	@Column(nullable = false, length = 5)
	private double rating;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role = Role.FREELANCER;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FreelancerStatus status = FreelancerStatus.ACTIVE;

	
	@ManyToMany
	@JoinTable(
	    name = "freelancer_projects",
	    joinColumns = @JoinColumn(name = "freelancer_id"),
	    inverseJoinColumns = @JoinColumn(name = "project_id")
	)
	
	private List<Project> appliedProjects;
	
	
	private int totalReviews;
	private int completedProjects;
	private double priorityScore;
	
	@Enumerated(EnumType.STRING)
	private PriorityLevel priorityLevel;
	private LocalDateTime lastReviewedDate;
	
	public Freelancer(Long freelancerId, String name, String email, String password, String skills,
			double experienceYears) {
		super();
		this.freelancerId = freelancerId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.skills = skills;
		this.experienceYears = experienceYears;
		this.rating = 0;
		this.role = Role.FREELANCER;
		this.status = FreelancerStatus.ACTIVE;
		this.totalReviews = 0;
		this.completedProjects = 0;
		this.priorityScore = 0.0;
		this.priorityLevel = PriorityLevel.LOW;
	}

	public Freelancer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(Long freelancerId) {
		this.freelancerId = freelancerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public double getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(double experienceYears) {
		this.experienceYears = experienceYears;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public FreelancerStatus getStatus() {
		return status;
	}

	public void setStatus(FreelancerStatus status) {
		this.status = status;
	}

	public List<Project> getAppliedProjects() {
		return appliedProjects;
	}

	public void setAppliedProjects(List<Project> appliedProjects) {
		this.appliedProjects = appliedProjects;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}

	public int getCompletedProjects() {
		return completedProjects;
	}

	public void setCompletedProjects(int completedProjects) {
		this.completedProjects = completedProjects;
	}

	public double getPriorityScore() {
		return priorityScore;
	}

	public void setPriorityScore(double priorityScore) {
		this.priorityScore = priorityScore;
	}

	public PriorityLevel getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(PriorityLevel priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public LocalDateTime getLastReviewedDate() {
		return lastReviewedDate;
	}

	public void setLastReviewedDate(LocalDateTime lastReviewedDate) {
		this.lastReviewedDate = lastReviewedDate;
	}
	
	
	
}
