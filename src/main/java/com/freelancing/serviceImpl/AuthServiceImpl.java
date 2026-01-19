package com.freelancing.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.LoginRequestDTO;
import com.freelancing.dto.response.LoginResponseDTO;
import com.freelancing.exception.UnauthorizedException;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Manager;
import com.freelancing.repository.AdminRepository;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {


    private final FreelancerRepository freelancerRepository;
    
    
    private final ManagerRepository managerRepository;
    
    private final AdminRepository adminRepository;

    @Autowired
    public AuthServiceImpl(FreelancerRepository freelancerRepository,ManagerRepository managerRepository,
    		AdminRepository adminRepository) {
        this.freelancerRepository = freelancerRepository;
        this.managerRepository = managerRepository;
        this.adminRepository = adminRepository;
    }
    
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    
    
    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        log.info("Login attempt for email: {}", dto.getEmail());

        Freelancer freelancer = freelancerRepository.findByEmail(dto.getEmail()).orElse(null);
        
        
        if (freelancer != null && freelancer.getPassword().equals(dto.getPassword())) 
        {
            log.info("Freelancer {} logged in successfully", dto.getEmail());
            return buildResponse(freelancer.getFreelancerId(),freelancer.getName(), freelancer.getEmail(),"FREELANCER");
        }

        
        
        Manager manager = managerRepository.findByEmail(dto.getEmail()).orElse(null);
        
        
        if (manager != null && manager.getPassword().equals(dto.getPassword()))
        {
            log.info("Manager {} logged in successfully", dto.getEmail());
            return buildResponse(manager.getManagerId(), manager.getName(), manager.getEmail(), "MANAGER");
        }

        
        log.warn("Unauthorized login attempt for email: {}", dto.getEmail());
        throw new UnauthorizedException("Invalid email or password");
    }

    private LoginResponseDTO buildResponse(Long id,String name,String email,String role) 
    {
        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(id);
        response.setName(name);
        response.setEmail(email);
        response.setRole(role);
        return response;
    }
}
