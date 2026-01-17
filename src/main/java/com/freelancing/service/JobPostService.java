package com.freelancing.service;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;

public interface JobPostService {

	JobResponseDTO publishJob(JobRequestDTO dto);

	
	
}
