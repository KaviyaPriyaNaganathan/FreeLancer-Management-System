package com.freelancing.serviceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freelancing.dto.request.JobRequestDTO;
import com.freelancing.dto.response.JobResponseDTO;
import com.freelancing.enums.JobStatus;
import com.freelancing.enums.PostedBy;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;
import com.freelancing.repository.JobPostRepository;
import com.freelancing.repository.ProjectRepository;

public class JobPostServiceImplTest {

    @Mock
    private JobPostRepository jobPostRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private JobPostServiceImpl jobPostService;

    private Project defaultProject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Default project used in all JobPost tests
        defaultProject = new Project();
        defaultProject.setProjectId(1L);
        defaultProject.setTitle("Website Project");
    }

    @Test
    void testPublishJob_Success() {
        JobRequestDTO dto = new JobRequestDTO();
        dto.setProjectId(1L);
        dto.setTitle("Frontend Developer");
        dto.setDescription("React work");
        dto.setRequiredSkills("React, JS");
        dto.setBudget(new BigDecimal("5000"));
        dto.setPostedBy(PostedBy.MANAGER);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(defaultProject));
        when(jobPostRepository.save(any(JobPost.class))).thenAnswer(invocation -> invocation.getArgument(0));

        JobResponseDTO response = jobPostService.publishJob(dto);

        assertNotNull(response);
        assertEquals("Frontend Developer", response.getTitle());
        verify(projectRepository, times(1)).findById(1L);
        verify(jobPostRepository, times(1)).save(any(JobPost.class));
    }

    @Test
    void testPublishJob_ProjectNotFound() {
        JobRequestDTO dto = new JobRequestDTO();
        dto.setProjectId(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> jobPostService.publishJob(dto));
        assertTrue(ex.getMessage().contains("Project id not found"));
        verify(projectRepository, times(1)).findById(1L);
    }



    @Test
    void testUpdateJobStatus_JobNotFound() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> jobPostService.updateJobStatus(JobStatus.CLOSED, 1L));
        assertTrue(ex.getMessage().contains("Job id not found"));
        verify(jobPostRepository, times(1)).findById(1L);
    }


    @Test
    void testGetJobById_Success() {
        JobPost job = new JobPost();
        job.setJobId(1L);
        job.setTitle("Backend Developer");
        job.setProject(defaultProject);

        when(jobPostRepository.findById(1L)).thenReturn(Optional.of(job));

        JobResponseDTO response = jobPostService.getJobById(1L);

        assertEquals("Backend Developer", response.getTitle());
        verify(jobPostRepository, times(1)).findById(1L);
    }

    @Test
    void testGetJobById_NotFound() {
        when(jobPostRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> jobPostService.getJobById(1L));
        assertTrue(ex.getMessage().contains("Job id not found"));
        verify(jobPostRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllOpenJobs() {
        JobPost job1 = new JobPost();
        job1.setJobId(1L);
        job1.setStatus(JobStatus.OPEN);
        job1.setProject(defaultProject);

        JobPost job2 = new JobPost();
        job2.setJobId(2L);
        job2.setStatus(JobStatus.OPEN);
        job2.setProject(defaultProject);

        when(jobPostRepository.findByStatus(JobStatus.OPEN)).thenReturn(Arrays.asList(job1, job2));

        List<JobResponseDTO> openJobs = jobPostService.getAllOpenJobs();

        assertEquals(2, openJobs.size());
        verify(jobPostRepository, times(1)).findByStatus(JobStatus.OPEN);
    }

    @Test
    void testGetAllJobs() {
        JobPost job1 = new JobPost();
        job1.setJobId(1L);
        job1.setProject(defaultProject);

        JobPost job2 = new JobPost();
        job2.setJobId(2L);
        job2.setProject(defaultProject);

        when(jobPostRepository.findAll()).thenReturn(Arrays.asList(job1, job2));

        List<JobResponseDTO> jobs = jobPostService.getAllJobs();

        assertEquals(2, jobs.size());
        verify(jobPostRepository, times(1)).findAll();
    }

    @Test
    void testSearchJobs_ByTitle() {
        JobPost job = new JobPost();
        job.setJobId(1L);
        job.setTitle("React Developer");
        job.setProject(defaultProject);

        when(jobPostRepository.findByTitleContainingIgnoreCase("React"))
                .thenReturn(Collections.singletonList(job));

        List<JobResponseDTO> result = jobPostService.searchJobs("React", null, null, null);

        assertEquals(1, result.size());
        assertEquals("React Developer", result.get(0).getTitle());
        verify(jobPostRepository, times(1)).findByTitleContainingIgnoreCase("React");
    }

    @Test
    void testSearchJobs_NoCriteria() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> jobPostService.searchJobs(null, null, null, null));
        assertTrue(ex.getMessage().contains("Search can be done by title, skills and budget only."));
    }
}
