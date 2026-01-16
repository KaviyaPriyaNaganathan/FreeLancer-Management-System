package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.models.Project;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Project assignManagerToProject(Long projectId, Long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
