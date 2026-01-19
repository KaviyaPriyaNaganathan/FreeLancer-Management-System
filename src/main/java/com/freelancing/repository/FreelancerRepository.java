package com.freelancing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Freelancer;

public interface FreelancerRepository extends JpaRepository<Freelancer,Long>{

	boolean existsByEmail(String email);

	List<Freelancer> findBySkillsContainingIgnoreCase(String skills);

	Optional<Freelancer> findByEmail(String email);

}
