package com.freelancing.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.request.JobUpdateStatusRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.dto.response.MeetingResponseDTO;
import com.freelancing.dto.response.PrioritizedFreelancerDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.service.ApplicationService;
import com.freelancing.service.FreelancerService;
import com.freelancing.service.JobPostService;
import com.freelancing.service.MeetingService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/manager")
@Validated
public class ManagerController {
	
	private JobPostService jobService;
	private ApplicationService applicationService;
	private MeetingService meetingService;
	private FreelancerService freelancerService;

	
	@Autowired
	public ManagerController(JobPostService jobService,
			ApplicationService applicationService, MeetingService meetingService, FreelancerService freelancerService) {
		super();
		this.jobService = jobService;
		this.applicationService = applicationService;
		this.meetingService= meetingService;
		this.freelancerService= freelancerService;
	}
	
	@PostMapping("/jobs/publish-job")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<JobResponseDTO> publishJob(@Valid @RequestBody JobRequestDTO dto)
	{
		return ResponseEntity.ok(jobService.publishJob(dto));
	}
	
	@PutMapping("/jobs/update-job-status/{jobId}/status")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<JobResponseDTO> updateJobStatus(@Valid @Positive(message = "Job ID must be positive") @RequestBody JobUpdateStatusRequestDTO dto ,@PathVariable Long jobId)
	{
		return ResponseEntity.ok(jobService.updateJobStatus(dto.getStatus(), jobId));
	}
	
	@PutMapping("/jobs/close-job/{jobId}")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<JobResponseDTO> closeJob(@Valid  @PathVariable @Positive(message = "Job ID must be positive") Long jobId)
	{
		return ResponseEntity.ok(jobService.closeJob(jobId));
	}
	
	@GetMapping("/jobs/{jobId}")
	@PreAuthorize("hasRole('MANAGER')")
	public JobResponseDTO getJobById(@PathVariable @Positive(message = "Job ID must be positive") Long jobId)
	{
		return jobService.getJobById(jobId);
	}
	
	
	
	@GetMapping("/{applicationId}")
	@PreAuthorize("hasRole('MANAGER')")
	public ApplicationResponseDTO getApplicationById(@PathVariable @Positive(message = "Application ID must be positive") Long applicationId)
	{
		return applicationService.getApplicationById(applicationId);
	}

	
	@PutMapping("/{applicationId}/status")
	@PreAuthorize("hasRole('MANAGER')")
	public ApplicationResponseDTO updateApplicationStatus(@RequestParam ApplicationStatus status,
			@PathVariable  @Positive(message = "Application ID must be positive") Long applicationId)
	{
		return applicationService.updateApplicationStatus(applicationId, status);
	}
	
	@GetMapping("/applications/{jobId}")
	@PreAuthorize("hasRole('MANAGER')")
	public List<ApplicationResponseDTO> getAllApplicationsByJobId(@PathVariable  @Positive(message = "Job ID must be positive") Long jobId)
	{
		return applicationService.getAllApplicationsByJobId(jobId);
	}
	
	@GetMapping("/meetings/{projectId}")
	@PreAuthorize("hasRole('MANAGER')")
	public List<MeetingResponseDTO> getMeetingByProject(@PathVariable @Positive(message = "Project ID must be positive") Long projectId)
	{
		return meetingService.getMeetingByProject(projectId);
	}
	
	@GetMapping("/freelancers/prioritized")
	public ResponseEntity<List<PrioritizedFreelancerDTO>> getPrioritizedFreelancers() {
	    return ResponseEntity.ok(freelancerService.getPrioritizedFreelancers());
	}

	
}
