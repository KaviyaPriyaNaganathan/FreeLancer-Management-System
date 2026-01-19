package com.freelancing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/applications")
@Validated
public class ApplicationController {

	@Autowired
	private final ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		super();
		this.applicationService = applicationService;
	}

	@PostMapping("/apply")
	public ResponseEntity<ApplicationResponseDTO> applyForJob(@Valid @RequestBody ApplicationRequestDTO dto) {
		return ResponseEntity.ok(applicationService.applyForJob(dto));
	}

	@PutMapping("/{applicationId}/status")
	public ResponseEntity<ApplicationResponseDTO> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam @NotNull(message = "Application status is required") ApplicationStatus status)  {
		return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, status));

	}

}
