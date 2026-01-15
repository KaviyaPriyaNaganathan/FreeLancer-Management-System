package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.AdminRepository;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}
	
	
}
