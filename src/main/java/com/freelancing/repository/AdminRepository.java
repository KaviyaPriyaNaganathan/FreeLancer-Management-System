package com.freelancing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancing.models.Admin;
import com.freelancing.models.Freelancer;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{

	Optional<Admin> findByEmail(String email);


}
