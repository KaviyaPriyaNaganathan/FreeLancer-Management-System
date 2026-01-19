package com.freelancing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.response.MeetingResponseDTO;
import com.freelancing.service.MeetingService;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/meetings")
@Validated
public class MeetingController {

	private MeetingService meetingService;

	@Autowired
	public MeetingController(MeetingService meetingService) {
		super();
		this.meetingService = meetingService;
	}

	
	@GetMapping("/freelancer/{freelancerId}")
	public List<MeetingResponseDTO> getMeetingsForFreelancer(@PathVariable @Positive(message = "Freelancer ID must be a positive number") Long freelancerId)
	{
		return meetingService.getMeetingsForFreelancer(freelancerId);
	}
 	
}
