package com.poc.bookingSystem.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.bookingSystem.entity.MeetingDetails;
import com.poc.bookingSystem.entity.RoomDetails;


@Repository
public interface IMeetingDetailsRepo extends JpaRepository<MeetingDetails, Long>{

	
	String FETCH_BY_PARAM = "SELECT md FROM MEETING_DETAILS md WHERE md.startTime=?1 and"
		+ " md.userInfo.userName=?2 and md.roomDetails.roomId=?3";

	@Query(FETCH_BY_PARAM)
	MeetingDetails getMeetingDetails(Date startTime, String userName, Long roomId);

	List<MeetingDetails> getMeetingDetailsByRoomDetailsRoomId(Long roomId);

	
}
