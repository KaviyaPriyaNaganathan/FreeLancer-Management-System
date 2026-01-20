package com.freelancing.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.enums.ProjectStatus;
import com.freelancing.exception.BadRequestException;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.ProjectMapper;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;
	private final  ManagerRepository managerRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository,
			ManagerRepository managerRepository)
	{
		
		super();
		
		this.projectRepository = projectRepository;
		this.managerRepository = managerRepository;
	}
    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Override
	public ProjectResponseDTO assignManagerToProject(Long projectId, Long managerId) {
		// TODO Auto-generated method stub
        log.info("Assigning managerId: {} to projectId: {}", managerId, projectId);

		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new ResourceNotFoundException("Project id not found "+projectId));

		Manager manager = managerRepository.findById(managerId)
				.orElseThrow(()->new ResourceNotFoundException("Manager id not found "+managerId));

		project.setManager(manager);
		
		Project savedProject = projectRepository.save(project);
        log.info("Manager assigned successfully to projectId: {}", projectId);

		return ProjectMapper.toResponse(savedProject);
	}

	@Override
	public ProjectResponseDTO createProject(ProjectRequestDTO dto) {
		// TODO Auto-generated method stub
        log.info("Creating project: {} with managerId: {}", dto.getTitle(), dto.getManagerId());

		Manager manager = managerRepository.findById(dto.getManagerId())
				.orElseThrow(()->new ResourceNotFoundException("Manager id not found "+dto.getManagerId()));
		
				
		Project project =  ProjectMapper.toEntity(dto,manager);
		Project savedProject = projectRepository.save(project);
        log.info("Project created successfully with projectId: {}", savedProject.getProjectId());

		return ProjectMapper.toResponse(savedProject);
	}

	@Override
	public List<ProjectResponseDTO> getAllProjects() {
		// TODO Auto-generated method stub
        log.info("Fetching all projects");

		return projectRepository.findAll()
				.stream()
				.map(ProjectMapper::toResponse).toList();
	}

	@Override
	public ProjectResponseDTO getProjectById(Long projectId) {
		// TODO Auto-generated method stub
        log.info("Fetching project by projectId: {}", projectId);
		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new ResourceNotFoundException("Project id not found "+projectId));
        log.info("Project fetched successfully: {}", project.getTitle());

		return ProjectMapper.toResponse(project);
	}

	@Override
	public ProjectResponseDTO updateProjectByStatus(Long projectId, ProjectStatus status) {
		// TODO Auto-generated method stub
        log.info("Updating status for projectId: {} to {}", projectId, status);

		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new ResourceNotFoundException("Project id not found "+projectId));
		
		project.setStatus(status);
		Project savedProject = projectRepository.save(project);
        log.info("Project status updated successfully for projectId: {}", projectId);

		return  ProjectMapper.toResponse(savedProject);
	}

	@Override
	public ProjectResponseDTO updateProjectDetails(Long projectId, ProjectRequestDTO dto) {
		// TODO Auto-generated method stub
        log.info("Updating details for projectId: {}", projectId);

		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new ResourceNotFoundException("Project id not found "+projectId));
		
		
		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setDeadline(dto.getDeadline());
		project.setBudget(dto.getBudget());
	
		Project savedProject = projectRepository.save(project);
        log.info("Project details updated successfully for projectId: {}", projectId);

		
		return ProjectMapper.toResponse(savedProject);
	}

	@Override
	public List<ProjectResponseDTO> getProjectByManager(Long managerId) {
		// TODO Auto-generated method stub
        log.info("Fetching projects for managerId: {}", managerId);

		List<Project> projects = projectRepository.findByManager_ManagerId(managerId);
		
		if (projects.isEmpty()) 
		{
            log.warn("No projects found for managerId: {}", managerId);

			throw new BadRequestException("No projects found for manager id: " + managerId);
        }
		
        log.info("Found {} projects for managerId: {}", projects.size(), managerId);

		return projects.stream().map(ProjectMapper::toResponse).toList();
	}

	@Override
	public ProjectResponseDTO assignBackupManager(Long projectId, Long managerId) {
		  Project project = projectRepository.findById(projectId)
		            .orElseThrow(() -> new RuntimeException("Project not found"));

		    Manager backupManager = managerRepository.findById(managerId)
		            .orElseThrow(() -> new RuntimeException("Manager not found"));

		    project.setBackupManager(backupManager);
		    projectRepository.save(project);

		    return ProjectMapper.toResponse(project);
	}




}
