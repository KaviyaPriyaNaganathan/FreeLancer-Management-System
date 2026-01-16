package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.PaymentRepository;
import com.freelancing.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService{

	private final PaymentRepository paymentRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		super();
		this.paymentRepository = paymentRepository;
	}
	
}
