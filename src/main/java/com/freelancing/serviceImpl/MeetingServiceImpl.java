package com.freelancing.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.dto.request.MeetingRequestDTO;
import com.freelancing.dto.response.MeetingResponseDTO;
import com.freelancing.exception.BadRequestException;
import com.freelancing.exception.ResourceNotFoundException;
import com.freelancing.mappers.MeetingMapper;
import com.freelancing.models.Freelancer;
import com.freelancing.models.Meeting;
import com.freelancing.models.Project;
import com.freelancing.repository.FreelancerRepository;
import com.freelancing.repository.MeetingRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.service.MeetingService;
@Service
public class MeetingServiceImpl implements MeetingService
{

	private final MeetingRepository meetingRepository;
	private final ProjectRepository projectRepository;
	private final FreelancerRepository freelancerRepository;
	
	private static final Logger log = LoggerFactory.getLogger(MeetingServiceImpl.class);
	

	
	@Autowired
	public MeetingServiceImpl(MeetingRepository meetingRepository, 
			ProjectRepository projectRepository, FreelancerRepository freelancerRepository) {
		super();
		this.meetingRepository = meetingRepository;
		this.projectRepository = projectRepository;
		this.freelancerRepository = freelancerRepository;
	}


	@Override
	public MeetingResponseDTO scheduleMeeting(MeetingRequestDTO dto) {
		// TODO Auto-generated method stub
        log.info("Scheduling meeting for projectId: {} on date: {}", dto.getProjectId(), dto.getMeetingDate());

		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(()->new ResourceNotFoundException("Project not found to schedule meeting. "+ dto.getProjectId()));
		
		Meeting meeting = MeetingMapper.toEntity(dto, project);
		Meeting savedMeeting = meetingRepository.save(meeting);
		
        log.info("Meeting scheduled successfully with id: {}", savedMeeting.getMeetingId());

		return MeetingMapper.toResponse(savedMeeting);
	}



	@Override
	public MeetingResponseDTO reScheduleMeeting(Long meetingId, LocalDateTime meetingDate) {
		// TODO Auto-generated method stub
        log.info("Rescheduling meeting with id: {} to new date: {}", meetingId, meetingDate);

		Meeting meeting = meetingRepository.findById(meetingId)
				.orElseThrow(() -> new ResourceNotFoundException("Meeting not found with id :"+meetingId));
		
		meeting.setMeetingDate(meetingDate);
		Meeting savedMeeting = meetingRepository.save(meeting);

        log.info("Meeting rescheduled successfully for id: {}", savedMeeting.getMeetingId());

		return MeetingMapper.toResponse(savedMeeting);
	}


	@Override
	public List<MeetingResponseDTO> getMeetingByProject(Long projectId) {
		// TODO Auto-generated method stub
        log.info("Fetching meetings for projectId: {}", projectId);

		
		Project project = projectRepository.findById(projectId)
				.orElseThrow(()->new ResourceNotFoundException("ProjectId not exists. "+projectId));
		
		List<Meeting> meeting = meetingRepository.findByProject_ProjectId(projectId);
		
		
		
		if (meeting.isEmpty())
		{
            log.warn("No meetings found for projectId: {}", projectId);
	        throw new BadRequestException("No meetings scheduled for project " + projectId);
		}

	    return meeting.stream().map(MeetingMapper::toResponse).toList();
	}


	@Override
	public List<MeetingResponseDTO> getMeetingsForFreelancer(Long freelancerId) {
		// TODO Auto-generated method stub
        log.info("Fetching meetings for freelancerId: {}", freelancerId);

		Freelancer freelancer = freelancerRepository.findById(freelancerId)
				.orElseThrow(()->new ResourceNotFoundException("Freelancer does not exists. "+freelancerId));
		
		 List<Project> projects = projectRepository.findByAssignedFreelancers_FreelancerId(freelancerId);

		 if (projects.isEmpty())
		 {
	          log.warn("No projects assigned to freelancerId: {}", freelancerId);
		     throw new RuntimeException("No projects assigned to freelancer " + freelancerId);
		 }
		 
		 
		 
		 List<Meeting> meetings = meetingRepository.findByProjectIn(projects);

		 if (meetings.isEmpty())
		 {
	           log.warn("No meetings scheduled for freelancerId: {}", freelancerId);
		      throw new BadRequestException("No meetings scheduled for freelancer " + freelancerId);
		 }
		 
	      log.info("Found {} meetings for freelancerId: {}", meetings.size(), freelancerId);
		 return meetings.stream().map(MeetingMapper::toResponse).toList();	
		}
}
