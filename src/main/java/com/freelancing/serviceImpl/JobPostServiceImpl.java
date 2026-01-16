package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.JobPostRepository;
import com.freelancing.service.JobPostService;
@Service
public class JobPostServiceImpl implements JobPostService{

	private final JobPostRepository jobPostRepository;

	@Autowired
	public JobPostServiceImpl(JobPostRepository jobPostRepository) {
		super();
		this.jobPostRepository = jobPostRepository;
	}
	
	
}
