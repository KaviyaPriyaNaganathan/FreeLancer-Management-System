package com.freelancing.dto.request;

import java.time.LocalDateTime;

import com.freelancing.enums.ConductedBy;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MeetingRequestDTO {

	@NotNull(message = "Project ID is required")
    @Positive(message = "Project ID must be positive")
	private Long projectId;         
	    
	@NotNull(message = "Meeting date is required")
    @Future(message = "Meeting date must be in the future")
	private LocalDateTime meetingDate; 
	    
    @NotBlank(message = "Agenda is required")
	private String agenda;
	    
    @NotBlank(message = "Progress notes are required")
	private String progressNotes;
	    
    @NotNull(message = "ConductedBy is required")
	private ConductedBy conductedBy;

		public Long getProjectId() {
			return projectId;
		}

		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}

		public LocalDateTime getMeetingDate() {
			return meetingDate;
		}

		public void setMeetingDate(LocalDateTime meetingDate) {
			this.meetingDate = meetingDate;
		}

		public String getAgenda() {
			return agenda;
		}

		public void setAgenda(String agenda) {
			this.agenda = agenda;
		}

		public String getProgressNotes() {
			return progressNotes;
		}

		public void setProgressNotes(String progressNotes) {
			this.progressNotes = progressNotes;
		}

		public ConductedBy getConductedBy() {
			return conductedBy;
		}

		public void setConductedBy(ConductedBy conductedBy) {
			this.conductedBy = conductedBy;
		}
	    
	 	
	    
}

