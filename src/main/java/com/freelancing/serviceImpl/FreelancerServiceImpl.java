package com.freelancing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ProjectRequestDTO;
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

	@Override
	public List<Freelancer> getAllFreelancers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Freelancer deactivateFreelancer(Long freelancerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Freelancer createProject(ProjectRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
