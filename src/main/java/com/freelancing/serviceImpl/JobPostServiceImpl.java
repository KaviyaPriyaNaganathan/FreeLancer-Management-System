package com.freelancing.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.enums.JobStatus;
import com.freelancing.mappers.JobPostMapper;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.repository.JobPostRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.JobPostService;
@Service
public class JobPostServiceImpl implements JobPostService{

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
		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(()->new RuntimeException("Project id not found."+dto.getProjectId()));
		
		JobPost job = JobPostMapper.toEntity(dto, project);
		JobPost savedJob = jobPostRepository.save(job);
		return JobPostMapper.toResponse(savedJob);
	}


	@Override
	public JobResponseDTO updateJobStatus(JobStatus status, Long jobId) {
		// TODO Auto-generated method stub
		
		JobPost job = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new RuntimeException("Job id not found. "+jobId));
		
		job.setStatus(status);
		
		return JobPostMapper.toResponse(job);
	}

	@Override
	public JobResponseDTO closeJob(Long jobId) {
		// TODO Auto-generated method stub
		JobPost job = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new RuntimeException("Job id not found. "+jobId));
		
		job.setStatus(JobStatus.CLOSED);
		return JobPostMapper.toResponse(job);
	}

	@Override
	public List<JobResponseDTO> getAllOpenJobs() {
		// TODO Auto-generated method stub
		List<JobPost> openJobs = jobPostRepository.findByStatus(JobStatus.OPEN);
		return openJobs.stream().map(JobPostMapper::toResponse).toList();
	}

	@Override
	public JobResponseDTO getJobById(Long jobId) {
		// TODO Auto-generated method stub
		JobPost job = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new RuntimeException("Job id not found. "+jobId));
		
		return JobPostMapper.toResponse(job);
		
	}

	@Override
	public List<JobResponseDTO> searchJobs(String title, String requiredSkills, BigDecimal minBudget, BigDecimal maxBudget) {
		// TODO Auto-generated method stub
		
		List<JobPost> jobs;
		
		if(title!=null)
			jobs = jobPostRepository.findByTitleContainingIgnoreCase(title);
		
		else if(requiredSkills!=null)
			jobs= jobPostRepository.findByRequiredSkillsContainingIgnoreCase(requiredSkills);
		
		else if(minBudget!=null && maxBudget!=null)
			jobs= jobPostRepository.findByBudgetBetween(minBudget, maxBudget);
		
		else
			throw new RuntimeException("Search can be done by title, skills and budget only.");
			
			
		return jobs.stream().map(JobPostMapper::toResponse).toList();
	}


	
	}
