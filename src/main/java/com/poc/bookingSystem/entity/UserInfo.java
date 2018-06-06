package com.poc.bookingSystem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="USER_DETAILS")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
     private Long userId;
	
	@Column(name="USER_NAME")
	private String userName;

	@OneToMany(mappedBy="userInfo")
	private List<MeetingDetails> meetingDetails;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonIgnore
	public List<MeetingDetails> getMeetingDetails() {
		return meetingDetails;
	}

	public void setMeetingDetails(List<MeetingDetails> meetingDetails) {
		this.meetingDetails = meetingDetails;
	}
	

}
