package com.freelancing.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.enums.ProjectStatus;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;


public interface ProjectService {

	ProjectResponseDTO assignManagerToProject(Long projectId, Long managerId);

	ProjectResponseDTO createProject(ProjectRequestDTO dto);

	List<ProjectResponseDTO> getAllProjects();

	ProjectResponseDTO getProjectById(Long projectId);

	ProjectResponseDTO updateProjectByStatus(Long projectId, ProjectStatus status);

	ProjectResponseDTO updateProjectDetails(Long projectId, ProjectRequestDTO dto);

	List<ProjectResponseDTO> getProjectByManager(Long managerId);


	
}
