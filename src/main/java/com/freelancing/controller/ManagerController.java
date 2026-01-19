package com.freelancing.controller;


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

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.request.JobUpdateStatusRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.dto.response.MeetingResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.service.ApplicationService;
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

	
	@Autowired
	public ManagerController(JobPostService jobService,
			ApplicationService applicationService, MeetingService meetingService) {
		super();
		this.jobService = jobService;
		this.applicationService = applicationService;
		this.meetingService= meetingService;
	}
	
	@PostMapping("/jobs/publish-job")
	public ResponseEntity<JobResponseDTO> publishJob(@Valid @RequestBody JobRequestDTO dto)
	{
		return ResponseEntity.ok(jobService.publishJob(dto));
	}
	
	@PutMapping("/jobs/update-job-status/{jobId}/status")
	public ResponseEntity<JobResponseDTO> updateJobStatus(@Valid @Positive(message = "Job ID must be positive") @RequestBody JobUpdateStatusRequestDTO dto ,@PathVariable Long jobId)
	{
		return ResponseEntity.ok(jobService.updateJobStatus(dto.getStatus(), jobId));
	}
	
	@PutMapping("/jobs/close-job/{jobId}")
	public ResponseEntity<JobResponseDTO> closeJob(@Valid  @PathVariable @Positive(message = "Job ID must be positive") Long jobId)
	{
		return ResponseEntity.ok(jobService.closeJob(jobId));
	}
	
	@GetMapping("/jobs/{jobId}")
	public JobResponseDTO getJobById(@PathVariable @Positive(message = "Job ID must be positive") Long jobId)
	{
		return jobService.getJobById(jobId);
	}
	
	
	
	@GetMapping("/{applicationId}")
	public ApplicationResponseDTO getApplicationById(@PathVariable @Positive(message = "Application ID must be positive") Long applicationId)
	{
		return applicationService.getApplicationById(applicationId);
	}

	
	@PutMapping("/{applicationId}/status")
	public ApplicationResponseDTO updateApplicationStatus(@RequestParam ApplicationStatus status,
			@PathVariable  @Positive(message = "Application ID must be positive") Long applicationId)
	{
		return applicationService.updateApplicationStatus(applicationId, status);
	}
	
	@GetMapping("/applications/{jobId}")
	public List<ApplicationResponseDTO> getAllApplicationsByJobId(@PathVariable  @Positive(message = "Job ID must be positive") Long jobId)
	{
		return applicationService.getAllApplicationsByJobId(jobId);
	}
	
	@GetMapping("/meetings/{projectId}")
	public List<MeetingResponseDTO> getMeetingByProject(@PathVariable @Positive(message = "Project ID must be positive") Long projectId)
	{
		return meetingService.getMeetingByProject(projectId);
	}
	
}
