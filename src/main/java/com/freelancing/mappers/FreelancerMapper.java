package com.freelancing.mappers;

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.enums.FreelancerStatus;
import com.freelancing.models.Freelancer;

public class FreelancerMapper {

	public static Freelancer toEntity(FreelancerSignupDTO dto)
	{
		Freelancer freelancer = new Freelancer();
		freelancer.setName(dto.getName());
		freelancer.setEmail(dto.getEmail());
		freelancer.setPassword(dto.getPassword());
		freelancer.setSkills(dto.getSkills());
		freelancer.setExperienceYears(dto.getExperienceYears());
		return freelancer;
	}


	public static FreelancerResponseDTO toResponse(Freelancer dto)
	{
		FreelancerResponseDTO response = new FreelancerResponseDTO();
		response.setFreelancerId(dto.getFreelancerId());
		response.setName(dto.getName());
		response.setEmail(dto.getEmail());
		response.setSkills(dto.getSkills());
		response.setExperienceYears(dto.getExperienceYears());
		response.setRating(dto.getRating());
		response.setStatus(dto.getStatus());
		return response;
	}
}
