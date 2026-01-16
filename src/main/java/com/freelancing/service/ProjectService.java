package com.freelancing.service;

import com.freelancing.models.Project;


public interface ProjectService {

	Project assignManagerToProject(Long projectId, Long managerId);

	
	
}
