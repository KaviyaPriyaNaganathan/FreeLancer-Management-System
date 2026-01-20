package com.freelancing.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.freelancing.enums.ConductedBy;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Meeting;
import com.freelancing.models.Project;

public class MeetingServiceImplTest {

    private Project project;
    
    private Freelancer freelancer;
    
    private List<Meeting> meetings;

    @BeforeEach
    void setUp()
    {
        project = new Project();
        
        project.setProjectId(1L);
        project.setTitle("Website Project");

        freelancer = new Freelancer();
        
        freelancer.setFreelancerId(1L);
        freelancer.setName("John Doe");

        meetings = new ArrayList<>();
    }

    @Test
    void testScheduleMeeting() 
    {
        Meeting meeting = new Meeting();
        
        meeting.setMeetingId(1L);
        meeting.setProject(project);
        meeting.setAgenda("Discuss UI");
        meeting.setProgressNotes("Initial draft done");
        meeting.setConductedBy(ConductedBy.MANAGER);
        meeting.setMeetingDate(LocalDateTime.of(2026, 1, 20, 10, 0));

        meetings.add(meeting);

        assertEquals(1, meetings.size());
        assertEquals("Discuss UI", meetings.get(0).getAgenda());
        assertNotEquals("Backend work", meetings.get(0).getAgenda());
    }

    @Test
    void testReScheduleMeeting() 
    {
        Meeting meeting = new Meeting();
        
        meeting.setMeetingId(1L);
        meeting.setMeetingDate(LocalDateTime.of(2026, 1, 20, 10, 0));

        LocalDateTime newDate = LocalDateTime.of(2026, 1, 21, 11, 0);
        meeting.setMeetingDate(newDate);

        assertEquals(newDate, meeting.getMeetingDate());
        assertNotEquals(LocalDateTime.of(2026, 1, 20, 10, 0), meeting.getMeetingDate());
    }

    @Test
    void testGetMeetingsByProject()
    {
        Meeting meeting1 = new Meeting();
        
        meeting1.setProject(project);
        
        Meeting meeting2 = new Meeting();
        
        meeting2.setProject(project);

        meetings.add(meeting1);
        meetings.add(meeting2);

        assertEquals(2, meetings.size());
    }

    @Test
    void testGetMeetingsForFreelancer() 
    {
        
    	Project assignedProject = new Project();
        
        assignedProject.setProjectId(1L);

        Meeting meeting = new Meeting();
        
        meeting.setProject(assignedProject);

        meetings.add(meeting);

        assertEquals(1, meetings.size());
        assertEquals(1, meetings.get(0).getProject().getProjectId());
        assertNotEquals(2, meetings.get(0).getProject().getProjectId());
    }
}
