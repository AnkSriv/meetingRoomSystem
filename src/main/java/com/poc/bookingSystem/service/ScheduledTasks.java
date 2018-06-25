package com.poc.bookingSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.poc.bookingSystem.entity.MeetingDetails;
import com.poc.bookingSystem.repo.IMeetingDetailsRepo;
import com.poc.bookingSystem.service.BookingSystemServiceImpl;

@Component
public class ScheduledTasks {
	@Autowired
	private IMeetingDetailsRepo iMeetingDetailsRepo;
	@Autowired
    public JavaMailSender emailSender; 
	
	 @Autowired
	 private Environment environment;
	 
	
	
	    @Scheduled(fixedRate = 60000)
	    public void sendNotification() {
	    	List<MeetingDetails> meetingDetails = iMeetingDetailsRepo.getMeetingDetailsByBookingStatus("Booked");
	    	if(Objects.nonNull(meetingDetails)){
	    	for (MeetingDetails meetingDetails2 : meetingDetails) {
	    		long curr_time = System.currentTimeMillis();
	    		long diff = calculateDifference(meetingDetails2.getStartTime(),curr_time);
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000);
				if(diffHours == 0 && (diffMinutes == 15 && diffMinutes >0)){
					 SimpleMailMessage message = new SimpleMailMessage(); 
				        message.setTo("srivastavaankita53@gmail.com"); 
				        message.setSubject(environment.getProperty("email_subject")+"-"+meetingDetails2.getMeetingTitle()); 
				        message.setText("Meeting will start in "+diffMinutes+ "mins"+ meetingDetails2.getMeetingTitle());
				       emailSender.send(message);
				       System.out.println("Message sent successfully");
				}
			}
	    	}
	    	
	    }
	    @Scheduled(fixedRate = 5000)
	    public void updateBookingStatus() {
	    	List<MeetingDetails> meetingDetails = iMeetingDetailsRepo.getMeetingDetailsByBookingStatus("Booked");
	    	System.out.println("2nd");
	    	for (MeetingDetails meetingDetails2 : meetingDetails) {
	    		long curr_time = System.currentTimeMillis();
	    		long diff = calculateDifference(meetingDetails2.getEndTime(),curr_time);
	    		if(diff<0){
	    			meetingDetails2.setBookingStatus("Available");
	    			MeetingDetails response = iMeetingDetailsRepo.saveAndFlush(meetingDetails2);
	    			 System.out.println("Data updated successfully"+response);
	    		}
	    		
				
			}
	    	
	    	
	    }

		long calculateDifference(Date startTime, long curr_time) {
			long diff = 0;
			if(Objects.nonNull(startTime)&& curr_time != 0l){
			long start_time = startTime.getTime();
		     diff = start_time - curr_time;
			
			}
			return diff;
		}
}
