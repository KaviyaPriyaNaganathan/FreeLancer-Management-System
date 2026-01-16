package com.freelancing.service;

import java.util.List;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.models.Project;


public interface ProjectService {

	ProjectResponseDTO assignManagerToProject(Long projectId, Long managerId);

	ProjectResponseDTO createProject(ProjectRequestDTO dto);

	List<ProjectResponseDTO> getAllProjects();	
	
}
