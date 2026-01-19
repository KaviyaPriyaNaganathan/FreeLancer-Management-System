package com.freelancing.mappers;

import java.time.LocalDateTime;

import com.freelancing.dto.request.MeetingRequestDTO;
import com.freelancing.dto.response.MeetingResponseDTO;
import com.freelancing.enums.ConductedBy;
import com.freelancing.models.Meeting;
import com.freelancing.models.Project;

public class MeetingMapper {
	
	public static Meeting toEntity(MeetingRequestDTO dto, Project project)
	{
		Meeting meeting = new Meeting();
		meeting.setMeetingDate(dto.getMeetingDate());
		meeting.setProject(project);
		meeting.setAgenda(dto.getAgenda());
		meeting.setProgressNotes(dto.getProgressNotes());
		meeting.setConductedBy(dto.getConductedBy());
		return meeting;
		
	}

	
	public static MeetingResponseDTO toResponse(Meeting dto)
	{
		MeetingResponseDTO response = new MeetingResponseDTO();
		response.setMeetingId(dto.getMeetingId());
		response.setProjectId(dto.getProject().getProjectId());
		response.setProjectTitle(dto.getProject().getTitle());
		response.setMeetingDate(dto.getMeetingDate());
		response.setAgenda(dto.getAgenda());
		response.setProgressNotes(dto.getProgressNotes());
		response.setConductedBy(dto.getConductedBy());
		return response;
	}

}