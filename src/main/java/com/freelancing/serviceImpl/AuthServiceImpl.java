package com.freelancing.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.LoginRequestDTO;
import com.freelancing.dto.response.LoginResponseDTO;
import com.freelancing.exception.UnauthorizedException;
import com.freelancing.models.Admin;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Manager;
import com.freelancing.repository.AdminRepository;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.security.CustomUserDetails;
import com.freelancing.security.JwtService;
import com.freelancing.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {


    private final FreelancerRepository freelancerRepository;
    
    private final JwtService jwtService;
    private final ManagerRepository managerRepository;
    
    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthServiceImpl(FreelancerRepository freelancerRepository,ManagerRepository managerRepository,
    		AdminRepository adminRepository,JwtService jwtService,PasswordEncoder passwordEncoder) {
        this.freelancerRepository = freelancerRepository;
        this.managerRepository = managerRepository;
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
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


	@Override
    public String resetPassword(String token, String newPassword) {
        String email = jwtService.extractUsername(token);

        if (jwtService.isTokenExpired(token)) throw new RuntimeException("Token expired");

        Object userEntity = findUserByEmail(email);
        if (userEntity == null) throw new RuntimeException("User not found");

        String encoded = passwordEncoder.encode(newPassword);

        if (userEntity instanceof Freelancer f) {
            f.setPassword(encoded);
            freelancerRepository.save(f);
        } else if (userEntity instanceof Manager m) {
            m.setPassword(encoded);
            managerRepository.save(m);
        } else {
            Admin a = (Admin) userEntity;
            a.setPassword(encoded);
            adminRepository.save(a);
        }

        return "Password reset successfully";
    }
	
	 private Object findRawUserByEmail(String email) {
	        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
	        if (adminOpt.isPresent()) return adminOpt.get();

	        Optional<Manager> managerOpt = managerRepository.findByEmail(email);
	        if (managerOpt.isPresent()) return managerOpt.get();

	        Optional<Freelancer> freelancerOpt = freelancerRepository.findByEmail(email);
	        return freelancerOpt.orElse(null);
	    }

	    private com.freelancing.enums.Role getRole(Object user) {
	        if (user instanceof Admin) return com.freelancing.enums.Role.ADMIN;
	        if (user instanceof Manager) return com.freelancing.enums.Role.MANAGER;
	        return com.freelancing.enums.Role.FREELANCER;
	    }

	    @Override
	    public String generateResetToken(String email) {

	        Object userEntity = findUserByEmail(email);
	        if (userEntity == null) throw new RuntimeException("User not found");

	        CustomUserDetails userDetails;
	        if (userEntity instanceof Freelancer f) {
	            userDetails = new CustomUserDetails(f.getEmail(), f.getPassword(), f.getRole());
	        } else if (userEntity instanceof Manager m) {
	            userDetails = new CustomUserDetails(m.getEmail(), m.getPassword(), m.getRole());
	        } else { 
	            Admin a = (Admin) userEntity;
	            userDetails = new CustomUserDetails(a.getEmail(), a.getPassword(), a.getRole());
	        }

	        return jwtService.generateToken(userDetails, 15); 
	    }
	    
	    private Object findUserByEmail(String email) {
	        Optional<Freelancer> f = freelancerRepository.findByEmail(email);
	        if (f.isPresent()) return f.get();

	        Optional<Manager> m = managerRepository.findByEmail(email);
	        if (m.isPresent()) return m.get();

	        Optional<Admin> a = adminRepository.findByEmail(email);
	        return a.orElse(null);
	    }
}
