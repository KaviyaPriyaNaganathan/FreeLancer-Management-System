package com.freelancing.dto.response;

import java.time.LocalDateTime;

import com.freelancing.enums.ConductedBy;

public class MeetingResponseDTO {

	
	 	private Long meetingId;
	    
	 	private Long projectId;
	    
	 	private String projectTitle;  
	    
	 	private LocalDateTime meetingDate;
	    
	 	private String agenda;
	    
	 	private String progressNotes;
	    
	 	private ConductedBy conductedBy;

		public Long getMeetingId() {
			return meetingId;
		}

		public void setMeetingId(Long meetingId) {
			this.meetingId = meetingId;
		}

		public Long getProjectId() {
			return projectId;
		}

		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}

		public String getProjectTitle() {
			return projectTitle;
		}

		public void setProjectTitle(String projectTitle) {
			this.projectTitle = projectTitle;
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
