package com.freelancing.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.ManagerRequestDTO;
import com.freelancing.dto.response.ManagerResponseDTO;
import com.freelancing.enums.ManagerStatus;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.ManagerMapper;
import com.freelancing.models.Manager;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService{

    private static final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

	
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

	@Override
	public ManagerResponseDTO addManager(ManagerRequestDTO dto) {
		// TODO Auto-generated method stub
        log.info("Adding new manager with name: {}", dto.getName());

		Manager manager=ManagerMapper.toEntity(dto);
		Manager savedManager = managerRepository.save(manager);
		
        log.info("Manager added successfully with id: {}", savedManager.getManagerId());

		return ManagerMapper.toResponseDTO(savedManager);
	}


	@Override
	public ManagerResponseDTO deactivateManager(Long managerId) {
		// TODO Auto-generated method stub
        log.info("Deactivating manager with id: {}", managerId);

		Manager manager = managerRepository.findById(managerId)
				.orElseThrow(()-> new ResourceNotFoundException("Mangerid not exists "+managerId));
		
		manager.setStatus(ManagerStatus.INACTIVE);
		
		Manager savedManager = managerRepository.save(manager);
        log.info("Manager id {} deactivated", managerId);

		return ManagerMapper.toResponseDTO(savedManager);
	}

	@Override
	public List<ManagerResponseDTO> getAllManagers() {
		// TODO Auto-generated method stub
        log.info("Fetching all managers");

		return managerRepository.findAll()
				.stream().map(ManagerMapper::toResponseDTO)
				.toList();
	}
	
}
