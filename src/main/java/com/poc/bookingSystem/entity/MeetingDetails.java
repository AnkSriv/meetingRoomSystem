package com.poc.bookingSystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name="MEETING_DETAILS")
public class MeetingDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MEETING_ID")
     private Long meetingID;
	
	@Column(name="MEETING_TITLE")
	private String meetingTitle;
	
	@Column(name="START_TIME")
	private Date startTime;
	
	@Column(name="END_TIME")
	private Date endTime;
	
	@Column(name="BOOKING_STATUS")
	private String bookingStatus;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserInfo userInfo;
	
	
	@ManyToOne
	@JoinColumn(name="ROOM_ID")
	private RoomDetails roomDetails;


	public Long getMeetingID() {
		return meetingID;
	}


	public void setMeetingID(Long meetingID) {
		this.meetingID = meetingID;
	}


	public String getMeetingTitle() {
		return meetingTitle;
	}


	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	

	
	public UserInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}


	public RoomDetails getRoomDetails() {
		return roomDetails;
	}


	public void setRoomDetails(RoomDetails roomDetails) {
		this.roomDetails = roomDetails;
	}


	public String getBookingStatus() {
		return bookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
}
