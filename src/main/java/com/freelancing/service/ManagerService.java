package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.ManagerRepository;


@Service
public class ManagerService {

	private final ManagerRepository managerRepository;

	@Autowired
	public ManagerService(ManagerRepository managerRepository) {
		super();
		this.managerRepository = managerRepository;
	}
	

}
