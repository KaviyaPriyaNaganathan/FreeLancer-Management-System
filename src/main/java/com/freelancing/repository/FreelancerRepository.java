package com.freelancing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Freelancer;
import com.freelancing.models.Project;

public interface FreelancerRepository extends JpaRepository<Freelancer,Long>{

	boolean existsByEmail(String email);

	List<Freelancer> findBySkillsContainingIgnoreCase(String skills);

}
