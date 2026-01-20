package com.freelancing.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.freelancing.enums.ProjectStatus;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;

public class ProjectServiceImplTest {

    private List<Project> projects;
    
    private Manager manager1;
    
    private Manager manager2;

    @BeforeEach
    void setUp() 
    {
        projects = new ArrayList<>();

        manager1 = new Manager();
        manager1.setManagerId(1L);
       
        manager1.setName("Ramesh");

        manager2 = new Manager();
        manager2.setManagerId(2L);
        manager2.setName("Suresh");
    }

    @Test
    void testCreateProject()
    {
        Project project = new Project();
        
        project.setProjectId(1L);
        project.setTitle("Website Development");
        project.setDescription("Build company website");
        project.setDeadline(LocalDate.now().plusDays(10));
        project.setBudget(5000);
        project.setManager(manager1);

        projects.add(project);

        assertEquals(1, projects.size());
        assertEquals("Website Development", projects.get(0).getTitle());
        assertEquals("Ramesh", projects.get(0).getManager().getName());
        assertNotEquals("Suresh", projects.get(0).getManager().getName());
    }

    @Test
    void testAssignManagerToProject()
    {
        Project project = new Project();
        project.setProjectId(1L);
        
        
        project.setTitle("Website");

        project.setManager(manager2);

        assertEquals("Suresh", project.getManager().getName());
        assertNotEquals("Ramesh", project.getManager().getName());
    }

    @Test
    void testUpdateProjectStatus() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setStatus(ProjectStatus.NEW);

        project.setStatus(ProjectStatus.COMPLETED);

        assertEquals(ProjectStatus.COMPLETED, project.getStatus());
        assertNotEquals(ProjectStatus.NEW, project.getStatus());
    }

    @Test
    void testGetAllProjects() {
        Project p1 = new Project();
        p1.setProjectId(1L);
        p1.setTitle("Website");
        p1.setManager(manager1);

        Project p2 = new Project();
        p2.setProjectId(2L);
        p2.setTitle("App");
        p2.setManager(manager2);

        projects.add(p1);
        projects.add(p2);

        assertEquals(2, projects.size());
        assertEquals("Website", projects.get(0).getTitle());
        assertEquals("App", projects.get(1).getTitle());
    }

    @Test
    void testUpdateProjectDetails() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setTitle("Old Title");
        project.setDescription("Old Desc");
        project.setBudget(5000);
        project.setManager(manager1);

        // Update details
        project.setTitle("New Title");
        project.setDescription("New Desc");
        project.setBudget(10000);

        assertEquals("New Title", project.getTitle());
        assertEquals("New Desc", project.getDescription());
        assertEquals(10000, project.getBudget());
        assertEquals("Ramesh", project.getManager().getName());
        assertNotEquals("Old Title", project.getTitle());
    }
}
