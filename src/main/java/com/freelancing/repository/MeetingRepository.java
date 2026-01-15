package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting,Long>{

}
