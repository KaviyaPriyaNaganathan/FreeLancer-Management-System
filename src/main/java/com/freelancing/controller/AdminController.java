package com.freelancing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.ManagerRequestDTO;
import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.dto.response.ManagerResponseDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.service.AdminService;
import com.freelancing.service.FreelancerService;
import com.freelancing.service.ManagerService;
import com.freelancing.service.ProjectService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
    private AdminService adminService;
	private ProjectService projectService;
	private ManagerService managerService;
	private FreelancerService freelancerService;
	
	
	@Autowired
    
    public AdminController(AdminService adminService, ProjectService projectService, ManagerService managerService,
			FreelancerService freelancerService) {
		super();
		this.adminService = adminService;
		this.projectService = projectService;
		this.managerService = managerService;
		this.freelancerService = freelancerService;
	}

	
	@PostMapping("/addManager")
    public ResponseEntity<ManagerResponseDTO> addManager(@RequestBody ManagerRequestDTO dto) {
        return ResponseEntity.ok(managerService.addManager(dto));
    }
    


	@PutMapping("/manager/{managerId}/deactivate")
    public ResponseEntity<ManagerResponseDTO> deactivateManager(@PathVariable Long managerId)
    {
    	return ResponseEntity.ok(managerService.deactivateManager(managerId));
    }

    @GetMapping("/managers")
    public List<ManagerResponseDTO> getAllManagers()
    {
    	return managerService.getAllManagers();
    }
    
    
    
    
    @GetMapping("/freelancers")
    public List<FreelancerResponseDTO> getAllFreelancers()
    {
    	return freelancerService.getAllFreelancers();
    }
    
    @PutMapping("/freelancer/{freelancerId}/deactivate")
    public ResponseEntity<FreelancerResponseDTO> deactivateFreelancer(@PathVariable Long freelancerId)
    {
    	return ResponseEntity.ok(freelancerService.deactivateFreelancer(freelancerId));
    }
    
    
    
    
    @PostMapping("/project")
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO dto) {
        return ResponseEntity.ok(projectService.createProject(dto));
    }

    @PutMapping("/projects/{projectId}/assign-manager/{managerId}")
    public ResponseEntity<ProjectResponseDTO> assignManagerToProject(
            @PathVariable Long projectId,
            @PathVariable Long managerId) {
        return ResponseEntity.ok(projectService.assignManagerToProject(projectId, managerId));
    }

    @GetMapping("/projects")
    public List<ProjectResponseDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    
    
}
