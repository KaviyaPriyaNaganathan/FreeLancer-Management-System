package com.freelancing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.JobRequestDTO;
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
	
	@PostMapping("/publish-job")
	public ResponseEntity<JobResponseDTO> publishJob(@RequestBody JobRequestDTO dto)
	{
		return ResponseEntity.ok(jobService.publishJob(dto));
	}
	
	
	
}
