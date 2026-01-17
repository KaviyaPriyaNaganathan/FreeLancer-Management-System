package com.freelancing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.enums.FreelancerStatus;
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

	@Override
	public FreelancerResponseDTO registerFreelancer(FreelancerSignupDTO dto) {
		// TODO Auto-generated method stub
		if (freelancerRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exists.");
		}
		Freelancer freelancer = FreelancerMapper.toEntity(dto);
		Freelancer savedFreelancer = freelancerRepository.save(freelancer);
		return FreelancerMapper.toResponse(savedFreelancer);

	}

	@Override
	public List<FreelancerResponseDTO> getAllFreelancers() {
		// TODO Auto-generated method stub
		return freelancerRepository.findAll().stream()
				.map(FreelancerMapper::toResponse).toList();
	}

	@Override
	public FreelancerResponseDTO deactivateFreelancer(Long freelancerId) {
		// TODO Auto-generated method stub
		Freelancer freelancer = freelancerRepository.findById(freelancerId)
				.orElseThrow(()->new RuntimeException("Freelancer id not exists "+freelancerId));
		freelancer.setStatus(FreelancerStatus.INACTIVE);
		Freelancer freelancerEntity = freelancerRepository.save(freelancer);
		return FreelancerMapper.toResponse(freelancerEntity);

	}

	@Override
	public FreelancerResponseDTO getFreenlancerById(Long freelancerId) {
		// TODO Auto-generated method stub
		Freelancer freelancer = freelancerRepository.findById(freelancerId)
				.orElseThrow(()->new RuntimeException("Freelancer id not exists "+freelancerId));
		return FreelancerMapper.toResponse(freelancer);
	}

	@Override
	public List<FreelancerResponseDTO> getFreelancerBySkills(String skills) {
		// TODO Auto-generated method stub
		
		List<Freelancer> freelancer = freelancerRepository.findBySkillsContainingIgnoreCase(skills);
		return freelancer.stream()
				.map(FreelancerMapper::toResponse)
				.toList();
	}



}
