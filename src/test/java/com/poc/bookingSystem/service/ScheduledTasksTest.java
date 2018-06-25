package com.poc.bookingSystem.service;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


import com.poc.bookingSystem.DTO.ResponseDTO;
import com.poc.bookingSystem.entity.MeetingDetails;
import com.poc.bookingSystem.entity.RoomDetails;
import com.poc.bookingSystem.repo.IMeetingDetailsRepo;
import com.poc.bookingSystem.repo.IRoomDetailsRepo;
import com.poc.bookingSystem.service.ScheduledTasks;

@RunWith(PowerMockRunner.class)
public class ScheduledTasksTest {

	 @InjectMocks
	 private ScheduledTasks scheduledTasks;
	 
	
	 @Mock
	 private IMeetingDetailsRepo iMeetingDetailsRepo;
	 @Mock
	 private Environment environment; 
	 
	 @Test
	    public void testSendNotificationIsNull() throws Exception {
		 List<MeetingDetails> meetingDetails  = null;
	        PowerMockito.when(iMeetingDetailsRepo.getMeetingDetailsByBookingStatus("Booked")).thenReturn(meetingDetails);
	        scheduledTasks.sendNotification();
	        
	    }
	 @Test
	    public void testcalculateDifferenceIsNotNull() throws Exception {
		
	        long response = scheduledTasks.calculateDifference(new Date(), System.currentTimeMillis());
	        Assert.assertNotNull("Response is not null", response);
	        
	    }
}
