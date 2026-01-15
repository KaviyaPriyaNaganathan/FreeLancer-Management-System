package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

}
