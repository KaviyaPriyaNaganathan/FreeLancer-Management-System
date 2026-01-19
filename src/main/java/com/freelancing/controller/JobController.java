package com.freelancing.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.service.JobPostService;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/jobs")
@Validated
public class JobController {

	private JobPostService jobService;

	@Autowired
	public JobController(JobPostService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping("/open-jobs")
	public List<JobResponseDTO> getAllOpenJobs() {
		return jobService.getAllOpenJobs();
	}

	@GetMapping("/search")
	public List<JobResponseDTO> searchJobs(
			@RequestParam(required = false) @Size(min = 2, message = "Title must have at least 2 characters") String title,
			@RequestParam(required = false) @Size(min = 2, message = "Skills must have at least 2 characters") String requiredSkills,
			@RequestParam(required = false) @DecimalMin(value = "0.0", inclusive = true, message = "Min budget must be positive") BigDecimal minBudget,
			@RequestParam(required = false) @DecimalMin(value = "0.0", inclusive = true, message = "Max budget must be positive") BigDecimal maxBudget) {

		return jobService.searchJobs(title, requiredSkills, minBudget, maxBudget);
	}

	@GetMapping
	public List<JobResponseDTO> getAllJobs() {
		return jobService.getAllJobs();
	}

}
