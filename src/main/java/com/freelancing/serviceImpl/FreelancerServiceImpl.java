package com.freelancing.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.enums.FreelancerStatus;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.FreelancerMapper;
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

    private static final Logger log = LoggerFactory.getLogger(FreelancerServiceImpl.class);

    
	@Override
	public FreelancerResponseDTO registerFreelancer(FreelancerSignupDTO dto) {
		// TODO Auto-generated method stub
        log.info("Attempting to register freelancer with email: {}", dto.getEmail());

		if (freelancerRepository.existsByEmail(dto.getEmail())) 
		{
            log.warn("Registration failed: Email {} already exists", dto.getEmail());

			throw new RuntimeException("Email already exists.");
		}
		Freelancer freelancer = FreelancerMapper.toEntity(dto);
		Freelancer savedFreelancer = freelancerRepository.save(freelancer);
		
        log.info("Freelancer registered successfully with id: {}", savedFreelancer.getFreelancerId());

		return FreelancerMapper.toResponse(savedFreelancer);

	}

	@Override
	public List<FreelancerResponseDTO> getAllFreelancers() {
		// TODO Auto-generated method stub
        log.info("Fetching all freelancers");

		return freelancerRepository.findAll().stream()
				.map(FreelancerMapper::toResponse).toList();
	}

	@Override
	public FreelancerResponseDTO deactivateFreelancer(Long freelancerId) {
		// TODO Auto-generated method stub
        log.info("Deactivating freelancer with id: {}", freelancerId);

		Freelancer freelancer = freelancerRepository.findById(freelancerId)
				.orElseThrow(()->new ResourceNotFoundException("Freelancer id not exists "+freelancerId));
		freelancer.setStatus(FreelancerStatus.INACTIVE);
		Freelancer freelancerEntity = freelancerRepository.save(freelancer);
		
        log.info("Freelancer with id {} deactivated", freelancerId);

		return FreelancerMapper.toResponse(freelancerEntity);

	}

	@Override
	public FreelancerResponseDTO getFreenlancerById(Long freelancerId) {
		// TODO Auto-generated method stub
        log.info("Fetching freelancer with id: {}", freelancerId);

		Freelancer freelancer = freelancerRepository.findById(freelancerId)
				.orElseThrow(()->new ResourceNotFoundException("Freelancer id not exists "+freelancerId));
		return FreelancerMapper.toResponse(freelancer);
	}

	@Override
	public List<FreelancerResponseDTO> getFreelancerBySkills(String skills) {
		// TODO Auto-generated method stub
        log.info("Searching freelancers by skill: {}", skills);

		List<Freelancer> freelancer = freelancerRepository.findBySkillsContainingIgnoreCase(skills);
		return freelancer.stream()
				.map(FreelancerMapper::toResponse)
				.toList();
	}



}
