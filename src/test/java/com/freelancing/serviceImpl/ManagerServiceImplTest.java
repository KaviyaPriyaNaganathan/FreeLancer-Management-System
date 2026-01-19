package com.freelancing.serviceImpl;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freelancing.dto.request.ManagerRequestDTO;
import com.freelancing.dto.response.ManagerResponseDTO;
import com.freelancing.enums.ManagerStatus;
import com.freelancing.mappers.ManagerMapper;
import com.freelancing.models.Manager;
import com.freelancing.repository.ManagerRepository;

public class ManagerServiceImplTest {

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ManagerServiceImpl managerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddManager() {
        ManagerRequestDTO dto = new ManagerRequestDTO();
        dto.setName("Ramesh");
        dto.setEmail("ramesh@gmail.com");
        dto.setPassword("12345");
        dto.setDepartment("IT");

        Manager manager = ManagerMapper.toEntity(dto);
        manager.setManagerId(1L);

        when(managerRepository.save(any(Manager.class))).thenReturn(manager);

        ManagerResponseDTO response = managerService.addManager(dto);

        assertEquals("Ramesh", response.getName());
        assertEquals("ramesh@gmail.com", response.getEmail());
        assertEquals("IT", response.getDepartment());

        verify(managerRepository, times(1)).save(any(Manager.class));
    }

    @Test
    void testDeactivateManager() {
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setName("Ramesh");
        manager.setStatus(ManagerStatus.ACTIVE);

        when(managerRepository.findById(1L)).thenReturn(Optional.of(manager));
        when(managerRepository.save(any(Manager.class))).thenAnswer(i -> i.getArgument(0));

        ManagerResponseDTO response = managerService.deactivateManager(1L);

        assertEquals(ManagerStatus.INACTIVE, manager.getStatus());
        assertEquals("Ramesh", response.getName());

        verify(managerRepository, times(1)).findById(1L);
        verify(managerRepository, times(1)).save(manager);
    }

    @Test
    void testGetAllManagers() {
        Manager manager1 = new Manager();
        manager1.setManagerId(1L);
        manager1.setName("Ramesh");
        manager1.setEmail("ramesh@gmail.com");
        manager1.setDepartment("IT");

        Manager manager2 = new Manager();
        manager2.setManagerId(2L);
        manager2.setName("Suresh");
        manager2.setEmail("suresh@gmail.com");
        manager2.setDepartment("HR");

        when(managerRepository.findAll()).thenReturn(Arrays.asList(manager1, manager2));

        List<ManagerResponseDTO> managers = managerService.getAllManagers();

        assertEquals(2, managers.size());
        assertEquals("Ramesh", managers.get(0).getName());
        assertEquals("Suresh", managers.get(1).getName());

        verify(managerRepository, times(1)).findAll();
    }
}
