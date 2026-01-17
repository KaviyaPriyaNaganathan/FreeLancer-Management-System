package com.freelancing.service;

import java.math.BigDecimal;
import java.util.List;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.enums.JobStatus;

public interface JobPostService {

	JobResponseDTO publishJob(JobRequestDTO dto);

	JobResponseDTO updateJobStatus(JobStatus status, Long jobId);

	JobResponseDTO closeJob(Long jobId);

	List<JobResponseDTO> getAllOpenJobs();

	JobResponseDTO getJobById(Long jobId);

	List<JobResponseDTO> searchJobs(String title, String skills, BigDecimal minBudget, BigDecimal maxBudget);


	
	
}
