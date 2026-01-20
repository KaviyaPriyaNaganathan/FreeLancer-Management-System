//package com.freelancing.config;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.freelancing.models.Manager;
//import com.freelancing.repository.ManagerRepository;
//
//@Configuration
//public class ManagerPasswordResetRunner implements CommandLineRunner {
//
//    private final ManagerRepository managerRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public ManagerPasswordResetRunner(ManagerRepository managerRepository,
//                                      PasswordEncoder passwordEncoder) {
//        this.managerRepository = managerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) {
//        Manager manager = managerRepository.findByEmail("manager101@gmail.com")
//                .orElseThrow(() -> new RuntimeException("Manager not found"));
//
//        manager.setPassword(passwordEncoder.encode("manager123"));
//        managerRepository.save(manager);
//
//        System.out.println("✅ Manager password reset to manager123");
//    }
//}
