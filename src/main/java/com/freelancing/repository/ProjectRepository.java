package com.freelancing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

	List<Project> findByManager_ManagerId(Long managerId);

}
