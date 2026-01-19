package com.freelancing.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.ManagerRequestDTO;
import com.freelancing.dto.request.MeetingRequestDTO;
import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.request.ProjectUpdateStatusRequestDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.dto.response.ManagerResponseDTO;
import com.freelancing.dto.response.MeetingResponseDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.service.AdminService;
import com.freelancing.service.FreelancerService;
import com.freelancing.service.ManagerService;
import com.freelancing.service.MeetingService;
import com.freelancing.service.ProjectService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin")
@Validated

public class AdminController {
	
	
    private AdminService adminService;
	private ProjectService projectService;
	private ManagerService managerService;
	private FreelancerService freelancerService;
	private MeetingService meetingService;
	
	
	@Autowired
    
    public AdminController(AdminService adminService, ProjectService projectService, ManagerService managerService,
			FreelancerService freelancerService, MeetingService meetingService) {
		super();
		this.adminService = adminService;
		this.projectService = projectService;
		this.managerService = managerService;
		this.freelancerService = freelancerService;
		this.meetingService = meetingService;
	}

	
	@PostMapping("/addManager")
    public ResponseEntity<ManagerResponseDTO> addManager(@Valid @RequestBody ManagerRequestDTO dto) {
        return ResponseEntity.ok(managerService.addManager(dto));
    }
    


	@PutMapping("/manager/{managerId}/deactivate")
    public ResponseEntity<ManagerResponseDTO> deactivateManager(@Valid @PathVariable Long managerId)
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
    public ResponseEntity<FreelancerResponseDTO> deactivateFreelancer(@Valid @PathVariable Long freelancerId)
    {
    	return ResponseEntity.ok(freelancerService.deactivateFreelancer(freelancerId));
    }
    
    @GetMapping("/freelancer/{freelancerId}")
    public FreelancerResponseDTO getFreenlancerById(@PathVariable Long freelancerId)
    {
    	return freelancerService.getFreenlancerById(freelancerId);
    }
    
    
    @GetMapping("/freelancer/search-by-skills")
    public List<FreelancerResponseDTO> getFreelancerBySkills(@RequestParam String skills)
    {
    	return freelancerService.getFreelancerBySkills(skills);
    }
     
    
    
    @PostMapping("/project")
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO dto) {
        return ResponseEntity.ok(projectService.createProject(dto));
    }

    @PutMapping("/projects/{projectId}/assign-manager/{managerId}")
    public ResponseEntity<ProjectResponseDTO> assignManagerToProject(
    		@Valid @PathVariable Long projectId,
            @PathVariable Long managerId) {
        return ResponseEntity.ok(projectService.assignManagerToProject(projectId, managerId));
    }

    @GetMapping("/project/{projectId}")
    public ProjectResponseDTO getProjectById(@Valid @PathVariable Long projectId)
    {
    	return projectService.getProjectById(projectId);
    }
    

    
    @GetMapping("/projects")
    public List<ProjectResponseDTO> getAllProjects() {
        return projectService.getAllProjects();
    }
    
    @GetMapping("/project/manager/{managerId}")
    public List<ProjectResponseDTO> getProjectByManager(@Valid @PathVariable Long managerId)
    {
    	return projectService.getProjectByManager(managerId);
    }

    @PutMapping("/project/{projectId}/status")
    public ResponseEntity<ProjectResponseDTO> updateProjectByStatus(@Valid @RequestBody ProjectUpdateStatusRequestDTO dto, @PathVariable Long projectId)
    {
    	return ResponseEntity.ok(projectService.updateProjectByStatus(projectId, dto.getStatus()));
    }
    
    @PutMapping("/project/update-project/{projectId}")
     public ResponseEntity<ProjectResponseDTO> updateProjectDetails(@Valid @PathVariable Long projectId,@RequestBody ProjectRequestDTO dto )
     {
    	return ResponseEntity.ok(projectService.updateProjectDetails(projectId, dto));
     }
    
    
    
    
    
    @PostMapping("/meeting/schedule-meeting")
    public ResponseEntity<MeetingResponseDTO> scheduleMeeting(@Valid @RequestBody MeetingRequestDTO dto)
    {
    	return ResponseEntity.ok(meetingService.scheduleMeeting(dto));
    }
    
    @PutMapping("/meeting/{meetingId}/reschedule-meeting/meetingDate")
    public ResponseEntity<MeetingResponseDTO> reScheduleMeeting(@PathVariable Long meetingId,
            @RequestParam @NotNull(message = "Meeting date is required") LocalDateTime meetingDate) 
    {
    	return  ResponseEntity.ok(meetingService.reScheduleMeeting(meetingId,meetingDate));
   
    }
    
}
