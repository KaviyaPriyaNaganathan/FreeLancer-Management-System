package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost,Long>{

}
