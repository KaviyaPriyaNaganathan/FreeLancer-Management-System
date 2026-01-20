package com.freelancing.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.request.LoginRequestDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.security.CustomUserDetails;
import com.freelancing.security.CustomUserDetailsService;
import com.freelancing.security.JwtService;
import com.freelancing.service.AuthService;
import com.freelancing.service.FreelancerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
	 private final AuthService authService;
    @Autowired
    private FreelancerService freelancerService;
    
    

    public AuthController(AuthService authService, FreelancerService freelancerService,
			AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService,
			JwtService jwtService) {
		super();
		this.authService = authService;
		this.freelancerService = freelancerService;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
	}

	@PostMapping("/freelancer/signup")
    public ResponseEntity<FreelancerResponseDTO> registerFreelancer(@Valid @RequestBody FreelancerSignupDTO dto) 
	{
        FreelancerResponseDTO response =freelancerService.registerFreelancer(dto);

        return ResponseEntity.ok(response);
    }
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

           
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            CustomUserDetails customUser = (CustomUserDetails) userDetails;

           
            String token = jwtService.generateToken(customUser);

            return ResponseEntity.ok(token);

        } 
        catch (Exception e)
        {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        
    	String email = request.get("email");
        String token = authService.generateResetToken(email);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        
    	String token = request.get("token");
        String newPassword = request.get("newPassword");
        String msg = authService.resetPassword(token, newPassword);
        return ResponseEntity.ok(msg);
    }


}
