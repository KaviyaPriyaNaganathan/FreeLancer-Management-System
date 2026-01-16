package com.freelancing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.service.FreelancerService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private final FreelancerService freelancerService;
	
	public AuthController(FreelancerService freelancerService) {
		super();
		this.freelancerService = freelancerService;
	}


	@PostMapping("/freelancer/signup")
	public ResponseEntity<FreelancerResponseDTO> registerFreelancer(@RequestBody FreelancerSignupDTO freelancer)
	{
		return ResponseEntity.ok(freelancerService.registerFreelancer(freelancer));
	}
	

	
}
