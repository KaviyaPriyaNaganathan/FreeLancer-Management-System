package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.JobPostRepository;

@Service
public class JobPostService {

	private final JobPostRepository jobPostRepository;

	@Autowired
	public JobPostService(JobPostRepository jobPostRepository) {
		super();
		this.jobPostRepository = jobPostRepository;
	}
	
	
}
