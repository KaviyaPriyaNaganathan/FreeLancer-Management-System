package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.ApplicationRepository;

@Service
public class ApplicationService {

	private final ApplicationRepository applicationRepository;

	@Autowired
	public ApplicationService(ApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}

}
