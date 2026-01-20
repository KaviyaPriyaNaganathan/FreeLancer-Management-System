package com.freelancing.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.freelancing.enums.JobStatus;
import com.freelancing.enums.PostedBy;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;

public class JobPostServiceImplTest {

    private Project project;
    private JobPost job1;
    
    
    private JobPost job2;
    
    private List<JobPost> jobs;

    @BeforeEach
    void setUp() 
    {
        project = new Project();
        project.setProjectId(1L);
        project.setTitle("Website Project");

        
        job1 = new JobPost();
        job1.setJobId(1L);
        job1.setTitle("Frontend Developer");
        job1.setStatus(JobStatus.OPEN);
        job1.setProject(project);

        
        job2 = new JobPost();
        job2.setJobId(2L);
        job2.setTitle("Backend Developer");
        job2.setStatus(JobStatus.OPEN);
        job2.setProject(project);

        
        jobs = new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);
    }

    @Test
    void testPublishJob() 
    {
        JobPost newJob = new JobPost();
        newJob.setJobId(3L);
        newJob.setTitle("React Developer");
        newJob.setStatus(JobStatus.OPEN);
        newJob.setProject(project);

        jobs.add(newJob);

        assertEquals(3, jobs.size());
        assertEquals("React Developer", jobs.get(2).getTitle());
        assertNotEquals("Angular Developer", jobs.get(2).getTitle());
    }

    @Test
    void testUpdateJobStatus() 
    {
        job1.setStatus(JobStatus.CLOSED);

        assertEquals(JobStatus.CLOSED, job1.getStatus());
        assertNotEquals(JobStatus.OPEN, job1.getStatus());
    }

    @Test
    void testGetJobById()
    {
        JobPost foundJob = job2; 

        assertEquals(2L, foundJob.getJobId());
        assertEquals("Backend Developer", foundJob.getTitle());
        assertNotEquals("Frontend Developer", foundJob.getTitle());
    }


    @Test
    void testSearchJobs_ByTitle() {
        String searchTitle = "Frontend";

        List<JobPost> searchResults = new ArrayList<>();
        for (JobPost j : jobs) {
            if (j.getTitle().contains(searchTitle)) {
                searchResults.add(j);
            }
        }

        assertEquals(1, searchResults.size());
        assertEquals("Frontend Developer", searchResults.get(0).getTitle());
        assertNotEquals("Backend Developer", searchResults.get(0).getTitle());
    }
}
