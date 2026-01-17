package com.freelancing.service;

import java.util.List;

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.models.Freelancer;

public interface FreelancerService {

	FreelancerResponseDTO registerFreelancer(FreelancerSignupDTO freelancer);

	List<FreelancerResponseDTO> getAllFreelancers();

	FreelancerResponseDTO deactivateFreelancer(Long freelancerId);

	FreelancerResponseDTO getFreenlancerById(Long freelancerId);

	List<FreelancerResponseDTO> getFreelancerBySkills(String skills);


}
