package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancing.models.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{


}
