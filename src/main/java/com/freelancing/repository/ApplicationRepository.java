package com.freelancing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;

public interface ApplicationRepository extends JpaRepository<Application,Long>{

	boolean existsByJobAndFreelancer(JobPost job, Freelancer freelancer);

	List<Application> findByJob_JobId(Long jobId);

	Optional<Application> findByJob_JobIdAndFreelancer_FreelancerId(
		    Long jobId,
		    Long freelancerId
		);


}
