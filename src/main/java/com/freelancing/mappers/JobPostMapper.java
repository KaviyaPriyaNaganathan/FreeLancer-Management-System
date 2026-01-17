package com.freelancing.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.repository.ProjectRepository;


public class JobPostMapper {

	@Autowired
	private static ProjectRepository projectRepository;
	
	public static JobPost toEntity(JobRequestDTO dto, Project project)
	{
		JobPost jobPost = new JobPost();
		jobPost.setTitle(dto.getTitle());
		jobPost.setDescription(dto.getDescription());
		jobPost.setRequiredSkills(dto.getRequiredSkills());
		jobPost.setPostedBy(dto.getPostedBy());
		jobPost.setBudget(dto.getBudget());
		jobPost.setProject(project);
		
		return jobPost;
	}
	
	
	public static JobResponseDTO toResponse(JobPost dto)
	{
		JobResponseDTO response = new JobResponseDTO();
		response.setJobId(dto.getJobId());
		response.setTitle(dto.getTitle());
		response.setDescription(dto.getDescription());
		response.setRequiredSkills(dto.getRequiredSkills());
		response.setPostedBy(dto.getPostedBy());
		response.setBudget(dto.getBudget());
		response.setStatus(dto.getStatus());
		response.setCreatedAt(dto.getCreatedAt());
		response.setUpdatedAt(dto.getUpdatedAt());
		response.setProjectId(dto.getProject().getProjectId());
		response.setProjectTitle(dto.getProject().getTitle());
		return response;
	}
	
	
	
	
}
