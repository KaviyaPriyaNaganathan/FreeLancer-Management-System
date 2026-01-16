package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Freelancer;

public interface FreelancerRepository extends JpaRepository<Freelancer,Long>{

	boolean existsByEmail(String email);

}
