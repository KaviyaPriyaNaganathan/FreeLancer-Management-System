package com.freelancing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.enums.ManagerStatus;
import com.freelancing.models.Manager;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService{

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

	
}
