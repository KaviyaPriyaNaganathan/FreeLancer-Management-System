package com.freelancing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.models.Manager;
import com.freelancing.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private AdminService adminService;

	
    @PostMapping("/addManager")
    public ResponseEntity<Manager> addManager(@RequestBody Manager manager) {
        return ResponseEntity.ok(adminService.addManager(manager));
    }
    
    
    @PutMapping("/manager/{maOnagerId}/deactivate")
    public ResponseEntity<Manager> deactivateManager(@PathVariable Long managerId)
    {
    	return ResponseEntity.ok(adminService.deactivateManager(managerId));
    }

    @GetMapping("/manager")
    public List<Manager> getAllManagers()
    {
    	return adminService.getAllManagers();
    }
    
  
    
    
    
}
