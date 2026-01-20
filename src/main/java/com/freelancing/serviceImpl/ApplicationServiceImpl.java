package com.freelancing.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ApplicationRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.exception.BadRequestException;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.ApplicationMapper;
import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.repository.ApplicationRepository;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.JobPostRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	private ApplicationRepository applicationRepository;

	private JobPostRepository jobPostRepository;

	private FreelancerRepository freelancerRepository;
	
	
	private ProjectRepository projectRepository;


	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobPostRepository jobPostRepository,
			FreelancerRepository freelancerRepository, ProjectRepository projectRepository) {
		super();
		this.applicationRepository = applicationRepository;
		this.jobPostRepository = jobPostRepository;
		this.freelancerRepository = freelancerRepository;
		this.projectRepository = projectRepository;
	}

	@Override
	public ApplicationResponseDTO applyForJob(ApplicationRequestDTO dto) {

        log.info("Attempting to apply for job: {} by freelancer: {}", dto.getJobId(), dto.getFreelancerId());

    	JobPost job = jobPostRepository.findById(dto.getJobId())
				.orElseThrow(() -> 
				{
                    log.error("Job not found with id: {}", dto.getJobId());
                    return new ResourceNotFoundException("Job not found with id: " + dto.getJobId());
                });
		
    	Freelancer freelancer = freelancerRepository.findById(dto.getFreelancerId())
				.orElseThrow(() -> 
				{
                    log.error("Freelancer not found with id: {}", dto.getFreelancerId());
                    return new ResourceNotFoundException("Freelancer not found with id: " + dto.getFreelancerId());
                });

		boolean alreadyApplied = applicationRepository.existsByJobAndFreelancer(job, freelancer);

		if (alreadyApplied)
			throw new BadRequestException("You have already applied for this job");

		Application application = ApplicationMapper.toEntity(dto,freelancer ,job );
		Application savedApplication = applicationRepository.save(application);
        log.info("Application saved successfully with id: {}", savedApplication.getApplicationId());

		return ApplicationMapper.toResponse(savedApplication);
	}

	@Override
	public ApplicationResponseDTO updateApplicationStatus(Long applicationId, ApplicationStatus status) {
		// TODO Auto-generated method 
        log.info("Updating status of application {} to {}", applicationId, status);

		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Application not found: " + applicationId));

		application.setStatus(status);
		application.setReviewedAt(LocalDateTime.now());

		Application savedApplication = applicationRepository.save(application);
        log.info("Application {} status updated to {}", applicationId, status);

		return ApplicationMapper.toResponse(savedApplication);
	}

	@Override
	public ApplicationResponseDTO getApplicationById(Long applicationId) {
		// TODO Auto-generated method stub
        log.info("Fetching application with id: {}", applicationId);

		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Application not found: " + applicationId));

		return ApplicationMapper.toResponse(application);
	}

	

	@Override
	public List<ApplicationResponseDTO> getAllApplicationsByJobId(Long jobId) {
		// TODO Auto-generated method stub
        log.info("Fetching all applications for job id: {}", jobId);

		JobPost job = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));
		
		List<Application> applications = applicationRepository.findByJob_JobId(jobId);
		
		if(applications.isEmpty()) 
		{
            log.warn("No applications found for job id: {}", jobId);
            throw new ResourceNotFoundException("No applications found for this job id "+jobId);

		}
		log.info("{} applications found for job id: {}", applications.size(), jobId);

		return applications.stream().map(ApplicationMapper::toResponse).toList();

	}

	

}
