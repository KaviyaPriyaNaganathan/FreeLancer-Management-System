package com.freelancing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.enums.ManagerStatus;
import com.freelancing.models.Manager;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private final ManagerRepository managerRepository;
	
	@Autowired
	public AdminServiceImpl(ManagerRepository managerRepository) {
		super();
		this.managerRepository = managerRepository;
	}

	@Override
	public Manager addManager(Manager manager) {
		// TODO Auto-generated method stub
		return managerRepository.save(manager);
	}

	@Override
	public Manager deactivateManager(Long managerId) {
		// TODO Auto-generated method stub
		Manager manager = managerRepository.findById(managerId)
				.orElseThrow(()-> new RuntimeException("Mangerid not exists "+managerId));
		
		manager.setStatus(ManagerStatus.INACTIVE);
		
		return managerRepository.save(manager);
	}

	@Override
	public List<Manager> getAllManagers() {
		// TODO Auto-generated method stub
		return managerRepository.findAll();
	}
	

	
}
