package com.freelancing.models;

import java.time.LocalDateTime;

import com.freelancing.enums.ReviewedBy;

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
@Table(name = "reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	
	@ManyToOne
	@JoinColumn(name = "project_id",nullable = false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="freelancer_id", nullable =false)
	private Freelancer freelancer;
	
	@Column(nullable = false)
	private int rating;
	
	@Column(nullable = false, length=1000)
	private String comments;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReviewedBy reviewedBy;
	
	
	@Column(nullable = false)
    private LocalDateTime reviewedDate;
	
	@ManyToOne
	@JoinColumn(name = "job_id", nullable = false)
	private JobPost job;



	public Review(JobPost job) {
		super();
		this.job = job;
	}


	public Review(Long reviewId, Project project, Freelancer freelancer, int rating, String comments,
			ReviewedBy reviewedBy, LocalDateTime reviewedDate) {
		super();
		this.reviewId = reviewId;
		this.project = project;
		this.freelancer = freelancer;
		this.rating = rating;
		this.comments = comments;
		this.reviewedBy = reviewedBy;
		this.reviewedDate = LocalDateTime.now();
	}


	public JobPost getJob() {
		return job;
	}


	public void setJob(JobPost job) {
		this.job = job;
	}


	public Review() {
		super();
		this.reviewedDate = LocalDateTime.now();
	}


	public Long getReviewId() {
		return reviewId;
	}


	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public Freelancer getFreelancer() {
		return freelancer;
	}


	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public ReviewedBy getReviewedBy() {
		return reviewedBy;
	}


	public void setReviewedBy(ReviewedBy reviewedBy) {
		this.reviewedBy = reviewedBy;
	}


	public LocalDateTime getReviewedDate() {
		return reviewedDate;
	}


	public void setReviewedDate(LocalDateTime reviewedDate) {
		this.reviewedDate = reviewedDate;
	}
	
	
	
	
}

