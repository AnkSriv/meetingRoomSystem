package com.poc.bookingSystem.DTO;

public class BookingRequestDTO {
		private Long roomId;
		private Long meetingId;
		private String meetingTitle;
		private String startTime;
		private String endTime;
		private String userName;
		private String bookingStatus;
		
		public Long getRoomId() {
			return roomId;
		}
		public void setRoomId(Long roomId) {
			this.roomId = roomId;
		}
		public String getMeetingTitle() {
			return meetingTitle;
		}
		public void setMeetingTitle(String meetingTitle) {
			this.meetingTitle = meetingTitle;
		}
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
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
		public String getBookingStatus() {
			return bookingStatus;
		}
		public void setBookingStatus(String bookingStatus) {
			this.bookingStatus = bookingStatus;
		}
		public Long getMeetingId() {
			return meetingId;
		}
		public void setMeetingId(Long meetingId) {
			this.meetingId = meetingId;
		}
		
}
