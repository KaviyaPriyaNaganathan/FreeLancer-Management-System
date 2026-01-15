package com.freelancing.models;

import java.time.LocalDateTime;

import com.freelancing.enums.PaymentStatus;

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
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long privateId;
	
	@ManyToOne
	@JoinColumn(name = "freelancer_id", nullable = false)
	private Freelancer freelancer;
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private LocalDateTime paymentDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus paymentStatus = PaymentStatus.PENDING;
	
	@Column(nullable = false, unique = true)
	private String transactionReference;

	public Payment(Long privateId, Freelancer freelancer, Project project, double amount, LocalDateTime paymentDate,
			PaymentStatus paymentStatus, String transactionReference) {
		super();
		this.privateId = privateId;
		this.freelancer = freelancer;
		this.project = project;
		this.amount = amount;
		this.paymentDate = LocalDateTime.now();
		this.paymentStatus = PaymentStatus.PENDING;
		this.transactionReference = transactionReference;
	}

	public Payment() {
		super();
		this.paymentDate = LocalDateTime.now();
		this.paymentStatus = PaymentStatus.PENDING;
	}

	public Long getPrivateId() {
		return privateId;
	}

	public void setPrivateId(Long privateId) {
		this.privateId = privateId;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}
	
	
	
}
