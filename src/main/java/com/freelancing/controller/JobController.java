package com.freelancing.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.service.JobPostService;

@RestController
@RequestMapping("/jobs")
public class JobController {

private JobPostService jobService;

	
	@Autowired
	public JobController(JobPostService jobService) {
		super();
		this.jobService = jobService;
	}
	
	@GetMapping("/open-jobs")
	public List<JobResponseDTO> getAllOpenJobs()
	{
		return jobService.getAllOpenJobs();
	}
	
	@GetMapping("/search")
	public List<JobResponseDTO> searchJobs(@RequestParam(required = false) String title,
	        @RequestParam(required = false) String requiredSkills,@RequestParam(required = false) BigDecimal minBudget,
	        @RequestParam(required = false) BigDecimal maxBudget	) {
	    
		return jobService.searchJobs(title, requiredSkills, minBudget, maxBudget);
	}
	
	@GetMapping
	public List<JobResponseDTO> getAllJobs()
	{
		return jobService.getAllJobs();
	}

	
	
}
