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

import com.freelancing.dto.request.MeetingRequestDTO;
import com.freelancing.dto.response.MeetingResponseDTO;
import com.freelancing.enums.ConductedBy;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Meeting;
import com.freelancing.models.Project;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.MeetingRepository;
import com.freelancing.repository.ProjectRepository;

public class MeetingServiceImplTest {

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private FreelancerRepository freelancerRepository;

    @InjectMocks
    private MeetingServiceImpl meetingService;

    private Project defaultProject;
    private Freelancer defaultFreelancer;

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
    }

    @Test
    void testScheduleMeeting_Success() {
        MeetingRequestDTO dto = new MeetingRequestDTO();
        dto.setProjectId(1L);
        dto.setAgenda("Discuss UI");
        dto.setProgressNotes("Initial draft done");
        dto.setConductedBy(ConductedBy.MANAGER);
        dto.setMeetingDate(LocalDateTime.of(2026, 1, 20, 10, 0));

        when(projectRepository.findById(1L)).thenReturn(Optional.of(defaultProject));
        when(meetingRepository.save(any(Meeting.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MeetingResponseDTO response = meetingService.scheduleMeeting(dto);

        assertNotNull(response);
        assertEquals("Discuss UI", response.getAgenda());
        verify(projectRepository, times(1)).findById(1L);
        verify(meetingRepository, times(1)).save(any(Meeting.class));
    }

    @Test
    void testScheduleMeeting_ProjectNotFound() {
        MeetingRequestDTO dto = new MeetingRequestDTO();
        dto.setProjectId(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> meetingService.scheduleMeeting(dto));
        assertTrue(ex.getMessage().contains("Project not found"));
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void testReScheduleMeeting_Success() {
        Meeting meeting = new Meeting();
        meeting.setMeetingId(1L);
        meeting.setProject(defaultProject);
        meeting.setAgenda("Initial");
        meeting.setProgressNotes("Notes");

        LocalDateTime newDate = LocalDateTime.of(2026, 1, 21, 11, 0);

        when(meetingRepository.findById(1L)).thenReturn(Optional.of(meeting));
        when(meetingRepository.save(any(Meeting.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MeetingResponseDTO response = meetingService.reScheduleMeeting(1L, newDate);

        assertEquals(newDate, response.getMeetingDate());
        verify(meetingRepository, times(1)).findById(1L);
        verify(meetingRepository, times(1)).save(meeting);
    }

    @Test
    void testReScheduleMeeting_NotFound() {
        when(meetingRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> meetingService.reScheduleMeeting(1L, LocalDateTime.now()));
        assertTrue(ex.getMessage().contains("Meeting not found"));
        verify(meetingRepository, times(1)).findById(1L);
    }

    @Test
    void testGetMeetingByProject_Success() {
        Meeting meeting1 = new Meeting();
        meeting1.setMeetingId(1L);
        meeting1.setProject(defaultProject);

        Meeting meeting2 = new Meeting();
        meeting2.setMeetingId(2L);
        meeting2.setProject(defaultProject);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(defaultProject));
        when(meetingRepository.findByProject_ProjectId(1L)).thenReturn(Arrays.asList(meeting1, meeting2));

        List<MeetingResponseDTO> meetings = meetingService.getMeetingByProject(1L);

        assertEquals(2, meetings.size());
        verify(projectRepository, times(1)).findById(1L);
        verify(meetingRepository, times(1)).findByProject_ProjectId(1L);
    }

    @Test
    void testGetMeetingByProject_NoMeetings() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(defaultProject));
        when(meetingRepository.findByProject_ProjectId(1L)).thenReturn(Collections.emptyList());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> meetingService.getMeetingByProject(1L));
        assertTrue(ex.getMessage().contains("No meetings scheduled"));
    }

    @Test
    void testGetMeetingsForFreelancer_Success() {
        Project assignedProject = new Project();
        assignedProject.setProjectId(1L);

        Meeting meeting = new Meeting();
        meeting.setMeetingId(1L);
        meeting.setProject(assignedProject);

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(projectRepository.findByAssignedFreelancers_FreelancerId(1L)).thenReturn(Collections.singletonList(assignedProject));
        when(meetingRepository.findByProjectIn(Collections.singletonList(assignedProject)))
                .thenReturn(Collections.singletonList(meeting));

        List<MeetingResponseDTO> meetings = meetingService.getMeetingsForFreelancer(1L);

        assertEquals(1, meetings.size());
        verify(freelancerRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).findByAssignedFreelancers_FreelancerId(1L);
        verify(meetingRepository, times(1)).findByProjectIn(Collections.singletonList(assignedProject));
    }

    @Test
    void testGetMeetingsForFreelancer_NoProjects() {
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(projectRepository.findByAssignedFreelancers_FreelancerId(1L)).thenReturn(Collections.emptyList());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> meetingService.getMeetingsForFreelancer(1L));
        assertTrue(ex.getMessage().contains("No projects assigned"));
    }

    @Test
    void testGetMeetingsForFreelancer_NoMeetings() {
        Project assignedProject = new Project();
        assignedProject.setProjectId(1L);

        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(defaultFreelancer));
        when(projectRepository.findByAssignedFreelancers_FreelancerId(1L)).thenReturn(Collections.singletonList(assignedProject));
        when(meetingRepository.findByProjectIn(Collections.singletonList(assignedProject)))
                .thenReturn(Collections.emptyList());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> meetingService.getMeetingsForFreelancer(1L));
        assertTrue(ex.getMessage().contains("No meetings scheduled"));
    }
}
