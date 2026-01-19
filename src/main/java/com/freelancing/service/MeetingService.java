package com.freelancing.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.freelancing.dto.request.MeetingRequestDTO;
import com.freelancing.dto.response.MeetingResponseDTO;


public interface MeetingService {

	MeetingResponseDTO scheduleMeeting(MeetingRequestDTO dto);

	MeetingResponseDTO reScheduleMeeting(Long meetingId, LocalDateTime meetingDate);

	List<MeetingResponseDTO> getMeetingByProject(Long projectId);

	List<MeetingResponseDTO> getMeetingsForFreelancer(Long freelancerId);

	
	
	

}
