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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.ApplicationRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.service.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

	@Autowired
	private final ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		super();
		this.applicationService = applicationService;
	}

	@PostMapping("/apply")
	public ResponseEntity<ApplicationResponseDTO> applyForJob(@RequestBody ApplicationRequestDTO dto) {
		return ResponseEntity.ok(applicationService.applyForJob(dto));
	}

	@PutMapping("/{applicationId}/status")
	public ResponseEntity<ApplicationResponseDTO> updateApplicationStatus(@PathVariable Long applicationId,
			@RequestParam ApplicationStatus status) {
		return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, status));

	}


//	@GetMapping("/job/{jobId}")
//	public List<ApplicationResponseDTO> getApplicationsByJob(@PathVariable Long jobId) {
//		return applicationService.getApplicationsByJob(jobId);
//	}
	

}
