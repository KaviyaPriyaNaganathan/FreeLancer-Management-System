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

import com.freelancing.dto.request.FreelancerSignupDTO;
import com.freelancing.dto.response.FreelancerResponseDTO;
import com.freelancing.enums.FreelancerStatus;
import com.freelancing.mappers.FreelancerMapper;
import com.freelancing.models.Freelancer;
import com.freelancing.repository.FreelancerRepository;

public class FreelancerServiceImplTest {

    @Mock
    private FreelancerRepository freelancerRepository;

    @InjectMocks
    private FreelancerServiceImpl freelancerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterFreelancer() {
        FreelancerSignupDTO dto = new FreelancerSignupDTO();
        dto.setName("Kaviya");
        dto.setEmail("kaviya@gmail.com");
        dto.setPassword("12345");
        dto.setSkills("Java, Spring");
        dto.setExperienceYears(2.0);

        // Mock email check
        when(freelancerRepository.existsByEmail("kaviya@gmail.com")).thenReturn(false);

        Freelancer freelancer = FreelancerMapper.toEntity(dto);
        freelancer.setFreelancerId(1L);

        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);

        FreelancerResponseDTO response = freelancerService.registerFreelancer(dto);

        assertEquals("Kaviya", response.getName());
        assertEquals("kaviya@gmail.com", response.getEmail());
        verify(freelancerRepository, times(1)).save(any(Freelancer.class));
    }

    @Test
    void testDeactivateFreelancer() {
        Freelancer freelancer = new Freelancer();
        freelancer.setFreelancerId(1L);
        freelancer.setName("Kaviya");
        freelancer.setStatus(FreelancerStatus.ACTIVE);

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenAnswer(i -> i.getArgument(0));

        FreelancerResponseDTO response = freelancerService.deactivateFreelancer(1L);

        assertEquals(FreelancerStatus.INACTIVE, freelancer.getStatus());
        assertEquals("Kaviya", response.getName());

        verify(freelancerRepository, times(1)).findById(1L);
        verify(freelancerRepository, times(1)).save(freelancer);
    }

    @Test
    void testGetAllFreelancers() {
        Freelancer freelancer1 = new Freelancer();
        freelancer1.setFreelancerId(1L);
        freelancer1.setName("Kaviya");

        Freelancer freelancer2 = new Freelancer();
        freelancer2.setFreelancerId(2L);
        freelancer2.setName("Priya");

        when(freelancerRepository.findAll()).thenReturn(Arrays.asList(freelancer1, freelancer2));

        List<FreelancerResponseDTO> freelancers = freelancerService.getAllFreelancers();

        assertEquals(2, freelancers.size());
        assertEquals("Kaviya", freelancers.get(0).getName());
        assertEquals("Priya", freelancers.get(1).getName());

        verify(freelancerRepository, times(1)).findAll();
    }

    @Test
    void testGetFreelancerById() {
        Freelancer freelancer = new Freelancer();
        freelancer.setFreelancerId(1L);
        freelancer.setName("Kaviya");

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));

        FreelancerResponseDTO response = freelancerService.getFreenlancerById(1L);

        assertEquals("Kaviya", response.getName());
        verify(freelancerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFreelancerBySkills() {
        Freelancer freelancer = new Freelancer();
        freelancer.setFreelancerId(1L);
        freelancer.setName("Kaviya");
        freelancer.setSkills("Java, Spring");

        when(freelancerRepository.findBySkillsContainingIgnoreCase("Java")).thenReturn(List.of(freelancer));

        List<FreelancerResponseDTO> freelancers = freelancerService.getFreelancerBySkills("Java");

        assertEquals(1, freelancers.size());
        assertEquals("Kaviya", freelancers.get(0).getName());
        verify(freelancerRepository, times(1)).findBySkillsContainingIgnoreCase("Java");
    }
}
