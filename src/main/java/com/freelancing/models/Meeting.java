package com.freelancing.models;

import java.time.LocalDateTime;

import com.freelancing.enums.ConductedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "meetings")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingId;
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable =false)
	private  Project project;
	
	@Column(nullable = false)
	private LocalDateTime meetingDate;
	
	@Column(nullable = false)
	private String agenda;
	
	@Column(nullable = false, length= 300)
	private String progressNotes;
	
	@Enumerated(EnumType.STRING)
	private ConductedBy conductedBy;

	public Meeting(Long meetingId, Project project, LocalDateTime meetingDate, String agenda, String progressNotes,
			ConductedBy conductedBy) {
		super();
		this.meetingId = meetingId;
		this.project = project;
		this.meetingDate = LocalDateTime.now();
		this.agenda = agenda;
		this.progressNotes = progressNotes;
		this.conductedBy = conductedBy;
	}

	public Meeting() {
		super();
		this.meetingDate = LocalDateTime.now();
	}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

