package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Manager;


public interface ManagerRepository extends JpaRepository<Manager,Long>
{

}
