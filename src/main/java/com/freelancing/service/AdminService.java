package com.freelancing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancing.models.Manager;


public interface AdminService {

	Manager addManager(Manager manager);

	Manager deactivateManager(Long managerId);

	List<Manager> getAllManagers();
}
