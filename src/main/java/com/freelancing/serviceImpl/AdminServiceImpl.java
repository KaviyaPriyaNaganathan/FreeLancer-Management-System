package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.enums.ManagerStatus;
import com.freelancing.mappers.ProjectMapper;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private final ProjectRepository projectRepository;
	private final ManagerRepository managerRepository;

	
	@Autowired
	public AdminServiceImpl(ProjectRepository projectRepository, ManagerRepository managerRepository) {
		super();
		this.projectRepository = projectRepository;
		this.managerRepository = managerRepository;
	}



	@Override
	public ProjectResponseDTO assignBackupManagerToProject(Long projectId, Long managerId) {
		 Project project = projectRepository.findById(projectId)
		            .orElseThrow(() -> new RuntimeException("Project not found"));

		    Manager backupManager = managerRepository.findById(managerId)
		            .orElseThrow(() -> new RuntimeException("Manager not found"));

		    if (project.getManager().getManagerId().equals(managerId))
		    {
		        throw new RuntimeException("Backup manager cannot be the primary manager");
		    }

		    if (!backupManager.getStatus().equals(ManagerStatus.ACTIVE))
		    {
		        throw new RuntimeException("Backup manager must be ACTIVE");
		    }

		    project.setBackupManager(backupManager);
		    projectRepository.save(project);

		    return ProjectMapper.toResponse(project);
	}

	
}
