package com.freelancing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;


public interface AdminService {

	ProjectResponseDTO assignBackupManagerToProject(Long projectId, Long managerId);
	
}
