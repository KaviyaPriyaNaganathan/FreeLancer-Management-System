package com.freelancing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.models.Freelancer;

public interface FreelancerService {

	Freelancer registerFreelancer(Freelancer freelancer);

	List<Freelancer> getAllFreelancers();

	Freelancer deactivateFreelancer(Long freelancerId);

	Freelancer createProject(ProjectRequestDTO dto);
	
	

}
