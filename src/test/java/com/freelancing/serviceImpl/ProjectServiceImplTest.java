package com.freelancing.serviceImpl;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freelancing.dto.request.ProjectRequestDTO;
import com.freelancing.dto.response.ProjectResponseDTO;
import com.freelancing.enums.ProjectStatus;
import com.freelancing.mappers.ProjectMapper;
import com.freelancing.models.Manager;
import com.freelancing.models.Project;
import com.freelancing.repository.ManagerRepository;
import com.freelancing.repository.ProjectRepository;

public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        // DTO input
        ProjectRequestDTO dto = new ProjectRequestDTO();
        dto.setTitle("Website Development");
        dto.setDescription("Build company website");
        dto.setDeadline(LocalDate.now().plusDays(10));
        dto.setBudget(5000);
        dto.setManagerId(1L);

        // Manager initialization
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setName("Ramesh");
        manager.setEmail("ramesh@gmail.com");
        manager.setDepartment("IT");

        when(managerRepository.findById(1L)).thenReturn(Optional.of(manager));

        // Project entity that mapper will return
        Project project = ProjectMapper.toEntity(dto, manager);
        project.setProjectId(1L);

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectResponseDTO response = projectService.createProject(dto);

        assertEquals("Website Development", response.getTitle());
        assertEquals("Build company website", response.getDescription());
        assertEquals("Ramesh", response.getManagerName());
        verify(projectRepository, times(1)).save(any(Project.class));
        verify(managerRepository, times(1)).findById(1L);
    }

    @Test
    void testAssignManagerToProject() {
        // Project initialization
        Project project = new Project();
        project.setProjectId(1L);
        project.setTitle("Website");
        project.setAssignedFreelancers(Collections.emptyList());

        // Manager initialization
        Manager manager = new Manager();
        manager.setManagerId(2L);
        manager.setName("Suresh");
        manager.setEmail("suresh@gmail.com");
        manager.setDepartment("IT");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(managerRepository.findById(2L)).thenReturn(Optional.of(manager));
        when(projectRepository.save(any(Project.class))).thenAnswer(i -> i.getArgument(0));

        ProjectResponseDTO response = projectService.assignManagerToProject(1L, 2L);

        assertEquals("Suresh", response.getManagerName());
        assertEquals("suresh@gmail.com", response.getManagerEmail());
        verify(projectRepository, times(1)).findById(1L);
        verify(managerRepository, times(1)).findById(2L);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testUpdateProjectByStatus() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setStatus(ProjectStatus.NEW);
        project.setAssignedFreelancers(Collections.emptyList());
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setName("Ramesh");
        project.setManager(manager);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenAnswer(i -> i.getArgument(0));

        ProjectResponseDTO response = projectService.updateProjectByStatus(1L, ProjectStatus.COMPLETED);

        assertEquals(ProjectStatus.COMPLETED, project.getStatus());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testGetAllProjects() {
        Project p1 = new Project();
        p1.setProjectId(1L);
        p1.setTitle("Website");
        Manager m1 = new Manager();
        m1.setManagerId(1L);
        m1.setName("Ramesh");
        p1.setManager(m1);
        p1.setAssignedFreelancers(Collections.emptyList());

        Project p2 = new Project();
        p2.setProjectId(2L);
        p2.setTitle("App");
        Manager m2 = new Manager();
        m2.setManagerId(2L);
        m2.setName("Suresh");
        p2.setManager(m2);
        p2.setAssignedFreelancers(Collections.emptyList());

        when(projectRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<ProjectResponseDTO> projects = projectService.getAllProjects();

        assertEquals(2, projects.size());
        assertEquals("Website", projects.get(0).getTitle());
        assertEquals("App", projects.get(1).getTitle());
        assertEquals("Ramesh", projects.get(0).getManagerName());
        assertEquals("Suresh", projects.get(1).getManagerName());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectById() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setTitle("Website");
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setName("Ramesh");
        project.setManager(manager);
        project.setAssignedFreelancers(Collections.emptyList());

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectResponseDTO response = projectService.getProjectById(1L);

        assertEquals("Website", response.getTitle());
        assertEquals("Ramesh", response.getManagerName());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProjectDetails() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setTitle("Old Title");
        project.setDescription("Old Desc");
        Manager manager = new Manager();
        manager.setManagerId(1L);
        manager.setName("Ramesh");
        project.setManager(manager);
        project.setAssignedFreelancers(Collections.emptyList());

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenAnswer(i -> i.getArgument(0));

        ProjectRequestDTO dto = new ProjectRequestDTO();
        dto.setTitle("New Title");
        dto.setDescription("New Desc");
        dto.setDeadline(LocalDate.now().plusDays(5));
        dto.setBudget(10000);
        dto.setManagerId(1L); // not used in update details

        ProjectResponseDTO response = projectService.updateProjectDetails(1L, dto);

        assertEquals("New Title", project.getTitle());
        assertEquals("New Desc", project.getDescription());
        assertEquals(10000, project.getBudget());
        assertEquals("Ramesh", response.getManagerName());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(project);
    }
}
