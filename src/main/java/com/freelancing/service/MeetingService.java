package com.freelancing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.MeetingRepository;

@Service
public class MeetingService {

	private final MeetingRepository meetingRepository;

	
	@Autowired
	public MeetingService(MeetingRepository meetingRepository) {
		super();
		this.meetingRepository = meetingRepository;
	}
	
	

}
