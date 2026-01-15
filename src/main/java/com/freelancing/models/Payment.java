package com.freelancing.models;

import java.time.LocalDateTime;

import com.freelancing.enums.PaymentMethod;
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
	
    @Column(nullable = false)
    private String accountHolderName;

    private String bankName;

    private String accountNumber;

    private String ifscCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    private String upi;

	public Payment(Long privateId, Freelancer freelancer, Project project, double amount, LocalDateTime paymentDate,
			PaymentStatus paymentStatus, String transactionReference, String accountHolderName, String bankName,
			String accountNumber, String ifscCode, PaymentMethod paymentMethod, String upi) {
		super();
		this.privateId = privateId;
		this.freelancer = freelancer;
		this.project = project;
		this.amount = amount;
		this.paymentDate = LocalDateTime.now();
		this.paymentStatus = PaymentStatus.PENDING;
		this.transactionReference = transactionReference;
		this.paymentMethod = paymentMethod;
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

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getUpi() {
		return upi;
	}

	public void setUpi(String upi) {
		this.upi = upi;
	}

	
	
}
