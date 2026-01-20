package com.freelancing.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.dto.response.PrioritizedFreelancerDTO;
import com.freelancing.enums.FreelancerStatus;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.FreelancerMapper;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Review;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.ReviewRepository;
import com.freelancing.service.FreelancerService;

@Service
public class FreelancerServiceImpl implements FreelancerService {

	private final FreelancerRepository freelancerRepository;
	private final ReviewRepository reviewRepository;

	@Autowired
	public FreelancerServiceImpl(FreelancerRepository freelancerRepository, ReviewRepository reviewRepository) {
		super();
		this.freelancerRepository = freelancerRepository;
		this.reviewRepository = reviewRepository;
	}

	private static final Logger log = LoggerFactory.getLogger(FreelancerServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public FreelancerResponseDTO registerFreelancer(FreelancerSignupDTO dto) {

		log.info("Attempting to register freelancer with email: {}", dto.getEmail());

		if (freelancerRepository.existsByEmail(dto.getEmail())) {
			log.warn("Registration failed: Email {} already exists", dto.getEmail());
			throw new RuntimeException("Email already exists.");
		}

		Freelancer freelancer = FreelancerMapper.toEntity(dto);

		freelancer.setPassword(passwordEncoder.encode(freelancer.getPassword()));

		Freelancer savedFreelancer = freelancerRepository.save(freelancer);

		log.info("Freelancer registered successfully with id: {}", savedFreelancer.getFreelancerId());

		return FreelancerMapper.toResponse(savedFreelancer);
	}

	@Override
	public List<FreelancerResponseDTO> getAllFreelancers() {
		// TODO Auto-generated method stub
		log.info("Fetching all freelancers");

		return freelancerRepository.findAll().stream().map(FreelancerMapper::toResponse).toList();
	}

	@Override
	public FreelancerResponseDTO deactivateFreelancer(Long freelancerId) {
		// TODO Auto-generated method stub
		log.info("Deactivating freelancer with id: {}", freelancerId);

		Freelancer freelancer = freelancerRepository.findById(freelancerId)
				.orElseThrow(() -> new ResourceNotFoundException("Freelancer id not exists " + freelancerId));
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
				.orElseThrow(() -> new ResourceNotFoundException("Freelancer id not exists " + freelancerId));
		return FreelancerMapper.toResponse(freelancer);
	}

	@Override
	public List<FreelancerResponseDTO> getFreelancerBySkills(String skills) {
		// TODO Auto-generated method stub
		log.info("Searching freelancers by skill: {}", skills);

		List<Freelancer> freelancer = freelancerRepository.findBySkillsContainingIgnoreCase(skills);
		return freelancer.stream().map(FreelancerMapper::toResponse).toList();
	}

	@Override
	public FreelancerResponseDTO getFreelancerByEmail(String email) {
		Freelancer freelancer = freelancerRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Freelancer not found"));

		return FreelancerMapper.toResponse(freelancer);
	}

	@Override
	public List<PrioritizedFreelancerDTO> getPrioritizedFreelancers() {

		List<Freelancer> freelancers = freelancerRepository.findAll();

		return freelancers.stream().map(freelancer -> {

			List<Review> reviews = reviewRepository.findByFreelancer_FreelancerId(freelancer.getFreelancerId());

			double avgRating = reviews.isEmpty() ? 0 : reviews.stream().mapToInt(Review::getRating).average().orElse(0);

			PrioritizedFreelancerDTO dto = new PrioritizedFreelancerDTO();

			dto.setFreelancerId(freelancer.getFreelancerId());
			dto.setFreelancerName(freelancer.getName());

			dto.setAverageRating(avgRating);

			dto.setTotalReviews((long) reviews.size());

			return dto;
		})

				.sorted((a, b) -> Double.compare(b.getAverageRating(), a.getAverageRating())).toList();
	}

}
