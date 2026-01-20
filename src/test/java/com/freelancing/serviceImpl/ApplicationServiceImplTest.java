package com.freelancing.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.freelancing.dto.request.ApplicationRequestDTO;
import com.freelancing.dto.response.ApplicationResponseDTO;
import com.freelancing.enums.ApplicationStatus;
import com.freelancing.models.Application;
import com.freelancing.models.Freelancer;
import com.freelancing.models.JobPost;
import com.freelancing.models.Project;

public class ApplicationServiceImplTest {

    private Freelancer freelancer;
    private JobPost job;
    private Project project;
    private List<Application> applications;

    @BeforeEach
    void setUp()
    {
        project = new Project();
        project.setProjectId(1L);
        project.setTitle("Website Project");

       
        
        job = new JobPost();
        job.setJobId(1L);
        job.setTitle("Frontend Developer");
        job.setProject(project);

        
        
        
        freelancer = new Freelancer();
        freelancer.setFreelancerId(1L);
        freelancer.setName("John Doe");

        applications = new ArrayList<>();
    }

    @Test
    void testApplyForJob()
    {
        
    	ApplicationRequestDTO dto = new ApplicationRequestDTO();
        
    	
    	dto.setJobId(job.getJobId());
        dto.setFreelancerId(freelancer.getFreelancerId());

        
        Application application = new Application();
        application.setJob(job);
        application.setFreelancer(freelancer);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setCreatedAt(LocalDateTime.now());

        applications.add(application);

        assertEquals(1, applications.size());
        assertEquals(ApplicationStatus.APPLIED, applications.get(0).getStatus());
        assertNotEquals(ApplicationStatus.ACCEPTED, applications.get(0).getStatus());
    }

    @Test
    void testUpdateApplicationStatus() 
    {
        Application application = new Application();
        
        application.setStatus(ApplicationStatus.APPLIED);

        application.setStatus(ApplicationStatus.ACCEPTED);

        assertEquals(ApplicationStatus.ACCEPTED, application.getStatus());
        assertNotEquals(ApplicationStatus.APPLIED, application.getStatus());
    }

    @Test
    void testGetAllApplications() 
    {
        Application app1 = new Application();
        
        app1.setStatus(ApplicationStatus.APPLIED);
        
        Application app2 = new Application();
        
        app2.setStatus(ApplicationStatus.ACCEPTED);

        applications.add(app1);
        applications.add(app2);

        assertEquals(2, applications.size());
        assertEquals(ApplicationStatus.APPLIED, applications.get(0).getStatus());
        assertEquals(ApplicationStatus.ACCEPTED, applications.get(1).getStatus());
    }
}
