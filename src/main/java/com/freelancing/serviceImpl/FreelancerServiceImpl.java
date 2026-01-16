package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.models.Freelancer;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.service.FreelancerService;

@Service
public class FreelancerServiceImpl implements FreelancerService {

	private final FreelancerRepository freelancerRepository;

	@Autowired
	public FreelancerServiceImpl(FreelancerRepository freelancerRepository) {
		super();
		this.freelancerRepository = freelancerRepository;
	}

	@Override
	public Freelancer registerFreelancer(Freelancer freelancer) {
		// TODO Auto-generated method stub
		if (freelancerRepository.existsByEmail(freelancer.getEmail())) {
			throw new RuntimeException("Email already exists.");
		}
		return freelancerRepository.save(freelancer);

	}
}
