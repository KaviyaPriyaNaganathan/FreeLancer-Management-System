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

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.request.JobUpdateStatusRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.service.JobPostService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	private JobPostService jobService;

	
	@Autowired
	public ManagerController(JobPostService jobService) {
		super();
		this.jobService = jobService;
	}
	
	@PostMapping("/jobs/publish-job")
	public ResponseEntity<JobResponseDTO> publishJob(@RequestBody JobRequestDTO dto)
	{
		return ResponseEntity.ok(jobService.publishJob(dto));
	}
	
	@PutMapping("/jobs/update-job-status/{jobId}/status")
	public ResponseEntity<JobResponseDTO> updateJobStatus(@RequestBody JobUpdateStatusRequestDTO dto ,@PathVariable Long jobId)
	{
		return ResponseEntity.ok(jobService.updateJobStatus(dto.getStatus(), jobId));
	}
	
	@PutMapping("/jobs/close-job/{jobId}")
	public ResponseEntity<JobResponseDTO> closeJob(@PathVariable Long jobId)
	{
		return ResponseEntity.ok(jobService.closeJob(jobId));
	}
	
	@GetMapping("/jobs/{jobId}")
	public JobResponseDTO getJobById(@PathVariable Long jobId)
	{
		return jobService.getJobById(jobId);
	}
	
	
	
	
}
