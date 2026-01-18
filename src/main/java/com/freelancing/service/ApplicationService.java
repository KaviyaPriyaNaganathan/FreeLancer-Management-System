package com.freelancing.service;

import java.util.List;

import com.freelancing.dto.request.ApplicationRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.enums.ApplicationStatus;


public interface ApplicationService {

	ApplicationResponseDTO applyForJob(ApplicationRequestDTO request);

	ApplicationResponseDTO updateApplicationStatus(Long applicationId, ApplicationStatus status);

	ApplicationResponseDTO getApplicationById(Long applicationId);

	ApplicationResponseDTO updateApplicationStatus(ApplicationStatus status, Long applicationId);

	List<ApplicationResponseDTO> getAllApplicationsByJobId(Long jobId);

	

}
