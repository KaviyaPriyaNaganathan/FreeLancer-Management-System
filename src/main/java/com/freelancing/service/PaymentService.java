package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.PaymentRepository;


@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository) {
		super();
		this.paymentRepository = paymentRepository;
	}
	
	

}
