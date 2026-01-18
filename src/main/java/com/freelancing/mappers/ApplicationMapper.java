package com.freelancing.mappers;

import java.time.LocalDateTime;

import com.freelancing.dto.request.ApplicationRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;

public class ApplicationMapper {

	public static Application toEntity(ApplicationRequestDTO dto
			,Freelancer freelancer, JobPost job)
	{
		Application application = new Application();
		application.setJob(job);    
		application.setCreatedAt(LocalDateTime.now());
        application.setFreelancer(freelancer);
        application.setStatus(ApplicationStatus.APPLIED);

        return application;
	}
	public static ApplicationResponseDTO toResponse(Application application) {

        ApplicationResponseDTO dto = new ApplicationResponseDTO();

        dto.setApplicationId(application.getApplicationId());
        dto.setStatus(application.getStatus());
        dto.setCreatedAt(application.getCreatedAt());
        dto.setReviewedAt(application.getReviewedAt());
        dto.setJobId(application.getJob().getJobId());
        dto.setJobTitle(application.getJob().getTitle());
        dto.setProjectId(application.getJob().getProject().getProjectId());
        dto.setProjectTitle(application.getJob().getProject().getTitle());
        dto.setFreelancerId(application.getFreelancer().getFreelancerId());
        dto.setFreelancerName(application.getFreelancer().getName());
        dto.setFreelancerEmail(application.getFreelancer().getEmail());

        return dto;
    }
}
