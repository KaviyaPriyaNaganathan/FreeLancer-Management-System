package com.freelancing.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.security.CustomUserDetails;
import com.freelancing.service.FreelancerService;

@RestController
@RequestMapping("/freelancer")
@PreAuthorize("hasRole('FREELANCER')")
public class FreelancerController {

    private final FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @GetMapping("/me")
    public FreelancerResponseDTO getMyProfile(@AuthenticationPrincipal CustomUserDetails user) 
    {

        return freelancerService.getFreelancerByEmail(user.getEmail());
    }
    


}
