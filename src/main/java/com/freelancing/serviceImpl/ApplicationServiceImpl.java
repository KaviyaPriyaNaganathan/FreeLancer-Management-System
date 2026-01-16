package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.ApplicationRepository;
import com.freelancing.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService{

	private final ApplicationRepository applicationRepository;

	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}
}

