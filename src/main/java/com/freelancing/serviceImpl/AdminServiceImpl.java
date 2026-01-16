package com.freelancing.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.enums.FreelancerStatus;
import com.freelancing.enums.ManagerStatus;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private final ManagerRepository managerRepository;
	private final FreelancerRepository freelancerRepository;
	private final ProjectRepository projectRepository;
	
	@Autowired
	public AdminServiceImpl(ManagerRepository managerRepository,
			FreelancerRepository freelancerRepository,
			ProjectRepository projectRepository) {
		super();
		this.managerRepository = managerRepository;
		this.freelancerRepository = freelancerRepository;
		this.projectRepository = projectRepository;
	}


//
//	@Override
//	public List<Project> getAllProjects() {
//		// TODO Auto-generated method stub
//		return projectRepository.findAll();
//	}
//
//	@Override
//	public ProjectResponseDTO createProject(ProjectRequestDTO dto) {
//		// TODO Auto-generated method stub
//		
//		Manager manager = managerRepository.findById(dto.getManagerId())
//				.orElseThrow(()->new RuntimeException("Manager Id not found "+dto.getManagerId()));
//		
//		Project project = new Project();
//		project.setTitle(dto.getTitle());
//		project.setDescription(dto.getDescription());
//		project.setDeadline(dto.getDeadline());
//		project.setBudget(dto.getBudget());
//		project.setManager(manager);
//		Project savedProject = projectRepository.save(project);
//
//		return new ProjectResponseDTO(
//				savedProject.getProjectId(),
//				savedProject.getTitle(),
//				savedProject.getDescription(),
//				savedProject.getCreatedDate(),
//				savedProject.getDeadline(),
//				savedProject.getStatus(),
//				savedProject.getBudget(),
//				
//				
//				manager.getManagerId(),
//				manager.getName(),
//				manager.getEmail(),
//				manager.getDepartment()				
//				);
//	}
//
//	

	
}
