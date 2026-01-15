package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Application;

public interface ApplicationRepository extends JpaRepository<Application,Long>{

}
