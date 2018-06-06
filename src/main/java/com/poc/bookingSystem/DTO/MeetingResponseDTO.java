package com.poc.bookingSystem.DTO;

public class MeetingResponseDTO {
	
    private Long meetingId;
	private String meetingTitle;
	private String startTime;
	private String endTime;
	private Long roomId;
	private String bookingStatus;
	private String userName;
	public Long getMeetingID() {
		return meetingId;
	}
	public void setMeetingID(Long meetingID) {
		this.meetingId = meetingID;
	}
	public String getMeetingTitle() {
		return meetingTitle;
	}
	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
