package com.freelancing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.enums.ProjectStatus;
import com.freelancing.mappers.ProjectMapper;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;
import com.freelancing.repository.FreelancerRepository;
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

	@Override
	public ProjectResponseDTO assignManagerToProject(Long projectId, Long managerId) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new RuntimeException("Project id not found "+projectId));

		Manager manager = managerRepository.findById(managerId)
				.orElseThrow(()->new RuntimeException("Manager id not found "+managerId));

		project.setManager(manager);
		
		Project savedProject = projectRepository.save(project);
		return ProjectMapper.toResponse(savedProject);
	}

	@Override
	public ProjectResponseDTO createProject(ProjectRequestDTO dto) {
		// TODO Auto-generated method stub
		Manager manager = managerRepository.findById(dto.getManagerId())
				.orElseThrow(()->new RuntimeException("Manager id not found "+dto.getManagerId()));
		
				
		Project project =  ProjectMapper.toEntity(dto,manager);
		Project savedProject = projectRepository.save(project);
		return ProjectMapper.toResponse(savedProject);
	}

	@Override
	public List<ProjectResponseDTO> getAllProjects() {
		// TODO Auto-generated method stub
		return projectRepository.findAll()
				.stream()
				.map(ProjectMapper::toResponse).toList();
	}

	@Override
	public ProjectResponseDTO getProjectById(Long projectId) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new RuntimeException("Project id not found "+projectId));
		
		return ProjectMapper.toResponse(project);
	}

	@Override
	public ProjectResponseDTO updateProjectByStatus(Long projectId, ProjectStatus status) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new RuntimeException("Project id not found "+projectId));
		
		project.setStatus(status);
		Project savedProject = projectRepository.save(project);
		return  ProjectMapper.toResponse(savedProject);
	}

	@Override
	public ProjectResponseDTO updateProjectDetails(Long projectId, ProjectRequestDTO dto) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new RuntimeException("Project id not found "+projectId));
		
		
		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setDeadline(dto.getDeadline());
		project.setBudget(dto.getBudget());
	
		Project savedProject = projectRepository.save(project);
		
		return ProjectMapper.toResponse(savedProject);
	}

	@Override
	public List<ProjectResponseDTO> getProjectByManager(Long managerId) {
		// TODO Auto-generated method stub
		List<Project> projects = projectRepository.findByManager_ManagerId(managerId);
		return projects.stream().map(ProjectMapper::toResponse).toList();
	}




}
