package com.freelancing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Freelancer;
import com.freelancing.models.Manager;


public interface ManagerRepository extends JpaRepository<Manager,Long>
{

	Optional<Manager> findByEmail(String email);

}
