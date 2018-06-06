package com.poc.bookingSystem.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poc.bookingSystem.DTO.BookingRequestDTO;
import com.poc.bookingSystem.DTO.ResponseDTO;
import com.poc.bookingSystem.entity.MeetingDetails;
import com.poc.bookingSystem.entity.RoomDetails;
import com.poc.bookingSystem.entity.UserInfo;
import com.poc.bookingSystem.repo.IMeetingDetailsRepo;
import com.poc.bookingSystem.repo.IRoomDetailsRepo;
import com.poc.bookingSystem.repo.IUserInfoRepo;
import com.poc.bookingSystem.util.BookingSystemException;
import com.poc.bookingSystem.util.Utils;

@RunWith(PowerMockRunner.class)
public class BookingSystemServiceImplTest {
	Gson gson = new Gson();
	private static final Long default_roomId =1l;
	private static final String default_roomName = "Meeting Room 1";
	private static final String default_statusAvailable = "Available";
	private static final String default_statusBooked = "Booked";
	private static final String default_startTime = "2018-06-05 08:36:33";
	private static final String default_endTime = "2018-06-05 09:00:33";
	private static final String default_SuccessMsg = "Success";
	private static final String default_ErrorMsg = "Error";
	
	@InjectMocks
    private BookingSystemServiceImpl bookingSystemServiceImpl;
	
	@Mock
	private IRoomDetailsRepo mockRoomDetailsRepo;
	 @Mock
	 private IMeetingDetailsRepo mockMeetingDetailsRepo;
	 @Mock
	 private IUserInfoRepo mockUserInfoRepo;
	 
	 @Test
	    public void testGetAllRoomsIsNull() throws Exception {
		 List<RoomDetails> responses  = null;
	        PowerMockito.when(mockRoomDetailsRepo.fetchAll()).thenReturn(responses);
	        ResponseDTO response = bookingSystemServiceImpl.getAllRooms();
	        Assert.assertEquals(response.getCode(), 200);
	    }
	 
	 @Test
	    public void testGetAllRoomsIsNotNull() throws Exception {
		     Type listType = new TypeToken<List<RoomDetails>>() {}.getType();
			 List<RoomDetails> meetingRoomList  = new ArrayList<RoomDetails>();
			 RoomDetails meetingRoom = new RoomDetails();
			 meetingRoom.setRoomId(default_roomId);
			 meetingRoom.setRoomName(default_roomName);
			 meetingRoom.setFloorNo(1);
			 meetingRoomList.add(meetingRoom);
			 
	        PowerMockito.when(mockRoomDetailsRepo.fetchAll()).thenReturn(meetingRoomList);
	        ResponseDTO response = bookingSystemServiceImpl.getAllRooms();
	        String details = gson.toJson(response.getResponse(),listType);
	         List<RoomDetails> responseList = gson.fromJson(details, listType);
	        Assert.assertNotNull("Response is  Empty", response);
	        Assert.assertEquals(responseList.get(0).getRoomId(), default_roomId);
	    }
	   @Test
	    public void testfetchMeetingsByIdIsNull() throws Exception {
		    RoomDetails roomObj  = null;
	        PowerMockito.when(mockRoomDetailsRepo.findOne(default_roomId)).thenReturn(roomObj);
	        ResponseDTO response = bookingSystemServiceImpl.fetchMeetingsById(default_roomId);
	        Assert.assertEquals(response.getError(), "No meeting Data available for room Id:"+default_roomId);
	    }
	   @Test
	    public void testfetchMeetingsByNotExistId() throws Exception {
		    RoomDetails roomObj  = new RoomDetails();
		    roomObj.setRoomId(default_roomId);
		    roomObj.setRoomName(default_roomName);
		    MeetingDetails meetingInfo = new MeetingDetails();
		    meetingInfo.setBookingStatus(default_statusBooked);
		    PowerMockito.when(mockRoomDetailsRepo.findOne(default_roomId)).thenReturn(roomObj);
	        ResponseDTO response = bookingSystemServiceImpl.fetchMeetingsById(2l);
	        Assert.assertNotNull("Fetching meetings info response is null", response);
	    }
	   
	   @Test
	    public void testfetchMeetingsByIdIsNotNull() throws Exception {
		    RoomDetails roomObj  = new RoomDetails();
		    roomObj.setRoomId(default_roomId);
		    roomObj.setRoomName(default_roomName);
		    MeetingDetails meetingInfo = new MeetingDetails();
		    meetingInfo.setBookingStatus(default_statusBooked);
		    PowerMockito.when(mockRoomDetailsRepo.findOne(default_roomId)).thenReturn(roomObj);
	        ResponseDTO response = bookingSystemServiceImpl.fetchMeetingsById(default_roomId);
	        Assert.assertNotNull("Fetching meetings info response is null", response);
	    }
	   @Test()
	    public void testSaveMeetingDetailsIsNull() throws Exception {
		    BookingRequestDTO bookingRequestDTO  = new BookingRequestDTO();
		    MeetingDetails meetingObj = null;
		    RoomDetails roomobj = null;
		    UserInfo userInfoObj = null;
	        PowerMockito.when(mockMeetingDetailsRepo.getMeetingDetails(null, null, default_roomId)).thenReturn(meetingObj);
	        PowerMockito.when(mockRoomDetailsRepo.findOne(default_roomId)).thenReturn(roomobj);
	        PowerMockito.when(mockUserInfoRepo.getUserInfoByUserName(null)).thenReturn(userInfoObj);
	        ResponseDTO response = bookingSystemServiceImpl.saveMeetingDetails(bookingRequestDTO);
	        Assert.assertEquals(response.getResponse(), null);
	        
	    }
	   @Test()
	    public void testSaveMeetingDetailsIsNotNull() throws Exception {
		    BookingRequestDTO bookingRequestDTO  = new BookingRequestDTO();
		    bookingRequestDTO.setRoomId(default_roomId);
		    bookingRequestDTO.setBookingStatus(default_statusBooked);
		    bookingRequestDTO.setUserName("userOne");
		    bookingRequestDTO.setStartTime(default_startTime);
		    bookingRequestDTO.setEndTime(default_endTime);
		    
		    MeetingDetails meetingObj = new MeetingDetails();
		    meetingObj.setMeetingID(1l);
		    meetingObj.setBookingStatus(default_statusAvailable);
		    
		    RoomDetails roomobj = new RoomDetails();
		    roomobj.setRoomId(default_roomId);
		    UserInfo userInfoObj = new UserInfo();
		    userInfoObj.setUserId(1l);
		    
		    Date str_date= Utils.convertStringToDate(bookingRequestDTO.getStartTime());
		  
	        PowerMockito.when(mockMeetingDetailsRepo.getMeetingDetails(str_date,"userOne",default_roomId)).thenReturn(meetingObj);
	        PowerMockito.when(mockRoomDetailsRepo.findOne(default_roomId)).thenReturn(roomobj);
	        PowerMockito.when(mockUserInfoRepo.getUserInfoByUserName(null)).thenReturn(userInfoObj);
	        ResponseDTO response = bookingSystemServiceImpl.saveMeetingDetails(bookingRequestDTO);
	        Assert.assertEquals(response.getCode(),0);
	        Assert.assertNotNull("Response is Null", response);
	        
	    }
	 
	 
}
