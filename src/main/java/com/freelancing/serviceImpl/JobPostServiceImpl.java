package com.freelancing.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.enums.JobStatus;
import com.freelancing.exception.BadRequestException;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.JobPostMapper;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.repository.JobPostRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.JobPostService;
@Service
public class JobPostServiceImpl implements JobPostService{

    private static final Logger log = LoggerFactory.getLogger(JobPostServiceImpl.class);

	private final JobPostRepository jobPostRepository;
	private final ProjectRepository  projectRepository;

	@Autowired
	public JobPostServiceImpl(JobPostRepository jobPostRepository,
			ProjectRepository  projectRepository) {
		super();
		this.jobPostRepository = jobPostRepository;
		this.projectRepository = projectRepository;
	}

	@Override
	public JobResponseDTO publishJob(JobRequestDTO dto) {
		// TODO Auto-generated method stub
        log.info("Publishing job for project id: {}", dto.getProjectId());

		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(()->new ResourceNotFoundException("Project id not found."+dto.getProjectId()));
		
		JobPost job = JobPostMapper.toEntity(dto, project);
		JobPost savedJob = jobPostRepository.save(job);
		
        log.info("Job published successfully with id: {}", savedJob.getJobId());

		return JobPostMapper.toResponse(savedJob);
	}


	@Override
	public JobResponseDTO updateJobStatus(JobStatus status, Long jobId) {
		// TODO Auto-generated method stub
		
        log.info("Updating status of job id {} to {}", jobId, status);

		JobPost job = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Job id not found. "+jobId));
		
		job.setStatus(status);
		
		
		
		return JobPostMapper.toResponse(job);
	}

	@Override
	public JobResponseDTO closeJob(Long jobId) {
		// TODO Auto-generated method stub
        log.info("Closing job id {}", jobId);

		JobPost job = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Job id not found. "+jobId));
		
		job.setStatus(JobStatus.CLOSED);
		
        log.info("Job id {} closed", jobId);

		return JobPostMapper.toResponse(job);
	}

	@Override
	public List<JobResponseDTO> getAllOpenJobs() {
		// TODO Auto-generated method stub
		
        log.info("Fetching all open jobs");

		List<JobPost> openJobs = jobPostRepository.findByStatus(JobStatus.OPEN);
		
        log.info("Found {} open jobs", openJobs.size());

		return openJobs.stream().map(JobPostMapper::toResponse).toList();
	}

	@Override
	public JobResponseDTO getJobById(Long jobId) {
		// TODO Auto-generated method stub
        log.info("Fetching job by id {}", jobId);

		JobPost job = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Job id not found. "+jobId));
		
		return JobPostMapper.toResponse(job);
		
	}

	@Override
	public List<JobResponseDTO> searchJobs(String title, String requiredSkills, BigDecimal minBudget, BigDecimal maxBudget) {
		// TODO Auto-generated method stub
        log.info("Searching jobs with title: {}, skills: {}, budget: {}-{}", title, requiredSkills, minBudget, maxBudget);

		List<JobPost> jobs;
		
		if(title!=null)
			jobs = jobPostRepository.findByTitleContainingIgnoreCase(title);
		
		else if(requiredSkills!=null)
			jobs= jobPostRepository.findByRequiredSkillsContainingIgnoreCase(requiredSkills);
		
		else if(minBudget!=null && maxBudget!=null)
			jobs= jobPostRepository.findByBudgetBetween(minBudget, maxBudget);
		
		else
			throw new BadRequestException("Search can be done by title, skills and budget only.");
			
        log.info("Found {} jobs matching search criteria", jobs.size());

		return jobs.stream().map(JobPostMapper::toResponse).toList();
	}

	@Override
	public List<JobResponseDTO> getAllJobs() {
		// TODO Auto-generated method stub
        log.info("Fetching all jobs");

		List<JobPost> allJobs = jobPostRepository.findAll();
		
        log.info("Total jobs found: {}", allJobs.size());

		return allJobs.stream().map(JobPostMapper::toResponse).toList();
	}


	
	}
