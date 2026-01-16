package com.freelancing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ManagerRequestDTO;
import com.freelancing.dto.response.ManagerResponseDTO;
import com.freelancing.enums.ManagerStatus;
import com.freelancing.mappers.ManagerMapper;
import com.freelancing.models.Manager;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService{

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

	@Override
	public ManagerResponseDTO addManager(ManagerRequestDTO dto) {
		// TODO Auto-generated method stub
		Manager manager=ManagerMapper.toEntity(dto);
		Manager savedManager = managerRepository.save(manager);
		return ManagerMapper.toResponseDTO(savedManager);
	}


	@Override
	public ManagerResponseDTO deactivateManager(Long managerId) {
		// TODO Auto-generated method stub
		Manager manager = managerRepository.findById(managerId)
				.orElseThrow(()-> new RuntimeException("Mangerid not exists "+managerId));
		
		manager.setStatus(ManagerStatus.INACTIVE);
		
		Manager savedManager = managerRepository.save(manager);
		return ManagerMapper.toResponseDTO(savedManager);
	}

	@Override
	public List<ManagerResponseDTO> getAllManagers() {
		// TODO Auto-generated method stub
		return managerRepository.findAll()
				.stream().map(ManagerMapper::toResponseDTO)
				.toList();
	}
	
}
