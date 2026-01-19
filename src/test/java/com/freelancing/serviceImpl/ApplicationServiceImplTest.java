package com.freelancing.serviceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freelancing.dto.request.ApplicationRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.repository.ApplicationRepository;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.JobPostRepository;
import com.freelancing.repository.ProjectRepository;

public class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private JobPostRepository jobPostRepository;

    @Mock
    private FreelancerRepository freelancerRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    private Freelancer defaultFreelancer;
    private JobPost defaultJob;
    private Project defaultProject;
    private Application defaultApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Default project
        defaultProject = new Project();
        defaultProject.setProjectId(1L);
        defaultProject.setTitle("Website Project");

        // Default freelancer
        defaultFreelancer = new Freelancer();
        defaultFreelancer.setFreelancerId(1L);
        defaultFreelancer.setName("John Doe");

        // Default job
        defaultJob = new JobPost();
        defaultJob.setJobId(1L);
        defaultJob.setTitle("Frontend Developer");
        defaultJob.setProject(defaultProject);

        // Default application
        defaultApplication = new Application();
        defaultApplication.setJob(defaultJob);
        defaultApplication.setFreelancer(defaultFreelancer);
        defaultApplication.setStatus(ApplicationStatus.APPLIED);
        defaultApplication.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testApplyForJob_Success() {
        ApplicationRequestDTO dto = new ApplicationRequestDTO();
        dto.setJobId(1L);
        dto.setFreelancerId(1L);

        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(defaultJob));
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(applicationRepository.existsByJobAndFreelancer(defaultJob, defaultFreelancer)).thenReturn(false);
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ApplicationResponseDTO response = applicationService.applyForJob(dto);

        assertNotNull(response);
        assertEquals(ApplicationStatus.APPLIED, response.getStatus());
        verify(jobPostRepository, times(1)).findById(1L);
        verify(freelancerRepository, times(1)).findById(1L);
        verify(applicationRepository, times(1)).save(any(Application.class));
    }

    @Test
    void testApplyForJob_AlreadyApplied() {
        ApplicationRequestDTO dto = new ApplicationRequestDTO();
        dto.setJobId(1L);
        dto.setFreelancerId(1L);

        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(defaultJob));
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(applicationRepository.existsByJobAndFreelancer(defaultJob, defaultFreelancer)).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> applicationService.applyForJob(dto));
        assertTrue(ex.getMessage().contains("already applied"));
    }

    @Test
    void testUpdateApplicationStatus_Success() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(defaultApplication));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ApplicationResponseDTO response = applicationService.updateApplicationStatus(1L, ApplicationStatus.ACCEPTED);

        assertNotNull(response);
        assertEquals(ApplicationStatus.ACCEPTED, response.getStatus());
        verify(applicationRepository, times(1)).findById(1L);
        verify(applicationRepository, times(1)).save(any(Application.class));
    }

    @Test
    void testUpdateApplicationStatus_NotFound() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> applicationService.updateApplicationStatus(1L, ApplicationStatus.ACCEPTED));
        assertTrue(ex.getMessage().contains("Application not found"));
    }

    @Test
    void testGetApplicationById_Success() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(defaultApplication));

        ApplicationResponseDTO response = applicationService.getApplicationById(1L);

        assertNotNull(response);
        assertEquals(ApplicationStatus.APPLIED, response.getStatus());
        verify(applicationRepository, times(1)).findById(1L);
    }

    @Test
    void testGetApplicationById_NotFound() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> applicationService.getApplicationById(1L));
        assertTrue(ex.getMessage().contains("Application not found"));
    }

    @Test
    void testGetAllApplicationsByJobId_Success() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(defaultJob));
        when(applicationRepository.findByJob_JobId(1L)).thenReturn(Arrays.asList(defaultApplication));

        List<ApplicationResponseDTO> applications = applicationService.getAllApplicationsByJobId(1L);

        assertEquals(1, applications.size());
        assertEquals(defaultApplication.getStatus(), applications.get(0).getStatus());
        verify(applicationRepository, times(1)).findByJob_JobId(1L);
    }

    @Test
    void testGetAllApplicationsByJobId_NoApplications() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(defaultJob));
        when(applicationRepository.findByJob_JobId(1L)).thenReturn(Collections.emptyList());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> applicationService.getAllApplicationsByJobId(1L));
        assertTrue(ex.getMessage().contains("No applications found"));
    }
}
