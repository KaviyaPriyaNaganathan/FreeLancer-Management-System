package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.mappers.JobPostMapper;
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
	
	
	}
