package com.freelancing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {

	List<Review> findByFreelancer_FreelancerId(Long freelancerId);

	List<Review> findByProject_ProjectId(Long projectId);

}
