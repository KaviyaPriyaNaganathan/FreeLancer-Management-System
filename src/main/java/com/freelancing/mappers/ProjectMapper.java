package com.freelancing.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
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
	
	public static ProjectResponseDTO toResponse(Project project) {
	    ProjectResponseDTO response = new ProjectResponseDTO();

	    // Basic project details
	    response.setProjectId(project.getProjectId());
	    response.setTitle(project.getTitle());
	    response.setDescription(project.getDescription());
	    response.setCreatedDate(project.getCreatedDate());
	    response.setDeadline(project.getDeadline());
	    response.setStatus(project.getStatus());
	    response.setBudget(project.getBudget());

	    if (project.getManager() != null) {
	        response.setManagerId(project.getManager().getManagerId());
	        response.setManagerName(project.getManager().getName());
	        response.setManagerEmail(project.getManager().getEmail());
	        response.setManagerDepartment(project.getManager().getDepartment());
	    }

	    // Backup manager details (only if assigned)
	    if (project.getBackupManager() != null) {
	        response.setBackupManagerId(project.getBackupManager().getManagerId());
	        response.setBackupManagerName(project.getBackupManager().getName());
	        response.setBackupManagerEmail(project.getBackupManager().getEmail());
	    }

	    return response;
	}

	
	
}