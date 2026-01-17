package com.freelancing.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.enums.JobStatus;
import com.freelancing.models.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost,Long>{

	List<JobPost> findByStatus(JobStatus open);


	List<JobPost> findByTitleContainingIgnoreCase(String title);


	List<JobPost> findByBudgetBetween(BigDecimal minBudget, BigDecimal maxBudget);


	List<JobPost> findByRequiredSkillsContainingIgnoreCase(String requiredSkills);

}
