package com.freelancing.service;


import java.util.List;

import com.freelancing.dto.request.ManagerRequestDTO;
import com.freelancing.dto.response.ManagerResponseDTO;
import com.freelancing.models.Manager;


public interface ManagerService {

	ManagerResponseDTO addManager(ManagerRequestDTO dto);

	ManagerResponseDTO deactivateManager(Long managerId);

	List<ManagerResponseDTO> getAllManagers();

   
}
