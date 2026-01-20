package com.freelancing.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.freelancing.models.Admin;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Manager;
import com.freelancing.repository.AdminRepository;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.ManagerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent())
        {
        	Admin admin = adminOpt.get();
           
        	return new CustomUserDetails(admin.getEmail(), admin.getPassword(), admin.getRole());
        }

        Optional<Manager> managerOpt = managerRepository.findByEmail(email);
        if (managerOpt.isPresent())
        {
            Manager manager = managerOpt.get();
            
            return new CustomUserDetails(manager.getEmail(), manager.getPassword(), manager.getRole());
        }

        Optional<Freelancer> freelancerOpt = freelancerRepository.findByEmail(email);
        if (freelancerOpt.isPresent())
        {
            Freelancer freelancer = freelancerOpt.get();
            
            return new CustomUserDetails(freelancer.getEmail(), freelancer.getPassword(), freelancer.getRole());
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
