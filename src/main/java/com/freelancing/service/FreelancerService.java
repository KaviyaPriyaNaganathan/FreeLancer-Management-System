package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.FreelancerRepository;

@Service
public class FreelancerService {
	
	private final FreelancerRepository freelancerRepository;

	@Autowired
	public FreelancerService(FreelancerRepository freelancerRepository) {
		super();
		this.freelancerRepository = freelancerRepository;
	}
	


}
