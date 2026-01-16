package com.freelancing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.repository.MeetingRepository;
import com.freelancing.service.MeetingService;
@Service
public class MeetingServiceImpl implements MeetingService{

private final MeetingRepository meetingRepository;

	
	@Autowired
	public MeetingServiceImpl(MeetingRepository meetingRepository) {
		super();
		this.meetingRepository = meetingRepository;
	}
}
