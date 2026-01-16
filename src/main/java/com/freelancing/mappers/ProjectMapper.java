package com.freelancing.mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.enums.ProjectStatus;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;
import com.freelancing.repository.ManagerRepository;


public class ProjectMapper {

	private static ManagerRepository managerRepository;
	
	@Autowired
	public ProjectMapper(ManagerRepository managerRepository) {
		super();
		this.managerRepository = managerRepository;
	}
	
	public static Project toEntity(ProjectRequestDTO dto, Manager manager)
	{
		Project project = new Project();
		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setDeadline(dto.getDeadline());
		project.setBudget(dto.getBudget());		
		project.setManager(manager);
		return project;
	}
	
	public static ProjectResponseDTO toResponse(Project dto)
	{
		ProjectResponseDTO response = new ProjectResponseDTO();
		response.setProjectId(dto.getProjectId());
		response.setTitle(dto.getTitle());
		response.setDescription(dto.getDescription());
		response.setCreatedDate(dto.getCreatedDate());
		response.setDeadline(dto.getDeadline());
		response.setStatus(dto.getStatus());
		response.setBudget(dto.getBudget());
		response.setManagerId(dto.getManager().getManagerId());
		response.setManagerName(dto.getManager().getName());
		response.setManagerEmail(dto.getManager().getEmail());
		response.setManagerDepartment(dto.getManager().getDepartment());
		return response;
	}
	
	
}