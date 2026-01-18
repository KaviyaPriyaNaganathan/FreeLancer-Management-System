package com.freelancing.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ApplicationRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.mappers.ApplicationMapper;
import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.repository.ApplicationRepository;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.JobPostRepository;
import com.freelancing.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	private ApplicationRepository applicationRepository;

	private JobPostRepository jobPostRepository;

	private FreelancerRepository freelancerRepository;

	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobPostRepository jobPostRepository,
			FreelancerRepository freelancerRepository) {
		super();
		this.applicationRepository = applicationRepository;
		this.jobPostRepository = jobPostRepository;
		this.freelancerRepository = freelancerRepository;
	}

	@Override
	public ApplicationResponseDTO applyForJob(ApplicationRequestDTO dto) {

		JobPost job = jobPostRepository.findById(dto.getJobId())
				.orElseThrow(() -> new RuntimeException("Job not found with id: " + dto.getJobId()));

		Freelancer freelancer = freelancerRepository.findById(dto.getFreelancerId())
				.orElseThrow(() -> new RuntimeException("Freelancer not found with id: " + dto.getFreelancerId()));

		boolean alreadyApplied = applicationRepository.existsByJobAndFreelancer(job, freelancer);

		if (alreadyApplied)
			throw new RuntimeException("You have already applied for this job");

		Application application = ApplicationMapper.toEntity(dto,freelancer ,job );
		Application savedApplication = applicationRepository.save(application);

		return ApplicationMapper.toResponse(savedApplication);
	}

	@Override
	public ApplicationResponseDTO updateApplicationStatus(Long applicationId, ApplicationStatus status) {
		// TODO Auto-generated method stub
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new RuntimeException("Application not found: " + applicationId));

		application.setStatus(status);
		application.setReviewedAt(LocalDateTime.now());

		Application savedApplication = applicationRepository.save(application);

		return ApplicationMapper.toResponse(savedApplication);
	}

	@Override
	public ApplicationResponseDTO getApplicationById(Long applicationId) {
		// TODO Auto-generated method stub

		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new RuntimeException("Application not found: " + applicationId));

		return ApplicationMapper.toResponse(application);
	}

	@Override
	public ApplicationResponseDTO updateApplicationStatus(ApplicationStatus status, Long applicationId) {
		// TODO Auto-generated method stub
		
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new RuntimeException("Application not found: " + applicationId));

		application.setStatus(status);
		Application savedApplication = applicationRepository.save(application);
		
		return ApplicationMapper.toResponse(savedApplication);
	}

	

}
