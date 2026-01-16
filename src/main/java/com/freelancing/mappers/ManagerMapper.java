package com.freelancing.mappers;

import com.freelancing.dto.request.ManagerRequestDTO;
import com.freelancing.dto.response.ManagerResponseDTO;
import com.freelancing.enums.ManagerStatus;
import com.freelancing.enums.Role;
import com.freelancing.models.Manager;

public class ManagerMapper {

	public static Manager toEntity(ManagerRequestDTO dto)
	{
		Manager manager = new Manager();
		manager.setName(dto.getName());
		manager.setEmail(dto.getEmail());
		manager.setPassword(dto.getPassword());
		manager.setDepartment(dto.getDepartment());
		return manager;
	}
	
	public static ManagerResponseDTO toResponseDTO(Manager manager)
	{
		ManagerResponseDTO response = new ManagerResponseDTO();
		
		response.setManagerId(manager.getManagerId());
		response.setName(manager.getName());
		response.setEmail(manager.getEmail());
		response.setDepartment(manager.getDepartment());
		response.setRole(manager.getRole());
		response.setStatus(manager.getStatus());
		return response;
	}
}