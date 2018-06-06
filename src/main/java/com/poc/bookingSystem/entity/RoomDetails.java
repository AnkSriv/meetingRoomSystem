package com.poc.bookingSystem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="ROOM_DETAILS")
public class RoomDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROOM_ID")
     private Long roomId;
	
	@Column(name="ROOM_NAME")
	private String roomName;
	
	@Column(name="BOOKING_STATUS")
	private String bookingStatus;
	
	@Column(name = "FLOOR_NO")
	private Integer floorNo;
	
	
	@OneToMany(mappedBy="roomDetails")
	private List<MeetingDetails> meetingDetails;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	@JsonIgnore
	public List<MeetingDetails> getMeetingDetails() {
		return meetingDetails;
	}

	public void setMeetingDetails(List<MeetingDetails> meetingDetails) {
		this.meetingDetails = meetingDetails;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
}
