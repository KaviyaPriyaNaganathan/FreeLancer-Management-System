package com.freelancing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Meeting;
import com.freelancing.models.Project;

public interface MeetingRepository extends JpaRepository<Meeting,Long>{

	List<Meeting> findByProject_ProjectId(Long projectId);

	List<Meeting> findByProjectIn(List<Project> projects);

}
