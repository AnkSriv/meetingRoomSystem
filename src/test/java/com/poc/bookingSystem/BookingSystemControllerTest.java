package com.poc.bookingSystem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
import com.poc.bookingSystem.entity.RoomDetails;
import com.poc.bookingSystem.inf.IBookingSystemService;


import org.junit.Assert;


@RunWith(PowerMockRunner.class)
public class BookingSystemControllerTest {
	Gson gson = new Gson();
	private static final Long default_roomId =1l;
	private static final String default_roomName = "Meeting Room 1";
	private static final String default_statusBooked = "Booked";
	private static final String default_SuccessMsg = "Success";
	@InjectMocks
    private BookingSystemController bookingSystemController;
    @Mock
    private IBookingSystemService iBookingSystemService;
    
    
	@Test
    public void testGetAllIsNull() throws Exception {
    	ResponseDTO responseDTO = null;  
        PowerMockito.when(bookingSystemController.getAllRooms()).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.getAllRooms();
        Assert.assertNull("Get All Room response is not null",response);
    }
    
    
	@Test
    public void testGetAllIsNotNull() throws Exception {
    	
    	Type listType = new TypeToken<List<RoomDetails>>() {}.getType();
    	List<RoomDetails> roomsList = new ArrayList<RoomDetails>();
    	RoomDetails meetingRoom = new RoomDetails();
    	meetingRoom.setRoomId(default_roomId);
    	meetingRoom.setRoomName(default_roomName);
    	roomsList.add(meetingRoom);
    	
    	ResponseDTO responseDTO = new ResponseDTO();
    	responseDTO.setMsg(default_SuccessMsg);
    	responseDTO.setResponse(roomsList);
    	
        PowerMockito.when(bookingSystemController.getAllRooms()).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.getAllRooms();
        
         String details = gson.toJson(response.getResponse(),listType);
         List<RoomDetails> responseList = gson.fromJson(details, listType);
         Assert.assertEquals(response.getMsg(),"Success");
         Assert.assertEquals(responseList.size(),1);
         Assert.assertEquals(responseList.get(0).getRoomId(),default_roomId);
         
    }
	@Test
    public void testfetchMeetingsByIdIsNull() throws Exception {
    	ResponseDTO responseDTO = null;  
        PowerMockito.when(bookingSystemController.fetchMeetingsById(default_roomId)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.fetchMeetingsById(default_roomId);
        Assert.assertNull("Fetch meeting by id is not null",response);
    }
	@Test
    public void testfetchMeetingsByIdRoomIdNull() throws Exception {
    	ResponseDTO responseDTO = null;  
        PowerMockito.when(bookingSystemController.fetchMeetingsById(default_roomId)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.fetchMeetingsById(null);
        Assert.assertNull("Fetch meeting by id is not null",response);
    }
	@Test
    public void testfetchMeetingsByIdIsNotNull() throws Exception {
		
    	ResponseDTO responseDTO = new ResponseDTO();
    	RoomDetails selectedRoom = new RoomDetails();
    	selectedRoom.setRoomId(default_roomId);
    	selectedRoom.setRoomName(default_roomName);
    	selectedRoom.setFloorNo(1);
    	responseDTO.setMsg(default_SuccessMsg);
    	responseDTO.setResponse(selectedRoom);
    	
        PowerMockito.when(bookingSystemController.fetchMeetingsById(default_roomId)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.fetchMeetingsById(default_roomId);
        String  resultStr = gson.toJson(response.getResponse(), RoomDetails.class);
        RoomDetails resultObj = gson.fromJson(resultStr, RoomDetails.class);
        Assert.assertEquals(resultObj.getRoomId(), default_roomId);
    }
	@Test
    public void testsaveMeetingDetailsIsNull() throws Exception {
    	ResponseDTO responseDTO = null;
    	BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
        PowerMockito.when(bookingSystemController.saveMeetingDetails(bookingRequestDTO)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.saveMeetingDetails(bookingRequestDTO);
        Assert.assertNull("save meeting details is not null",response);
    }
	@Test
    public void testsaveMeetingDetailsIsNotNull() throws Exception {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMsg(default_SuccessMsg);
    	BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
    	bookingRequestDTO.setRoomId(default_roomId);
    	bookingRequestDTO.setBookingStatus(default_statusBooked);
        PowerMockito.when(bookingSystemController.saveMeetingDetails(bookingRequestDTO)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.saveMeetingDetails(bookingRequestDTO);
        Assert.assertEquals(response.getMsg(), "Success");
    }
	@Test
    public void testDeleteMeetingDetailsIsNull() throws Exception {
    	ResponseDTO responseDTO = null;
    	
        PowerMockito.when(bookingSystemController.deleteMeetingDetails(1l)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.deleteMeetingDetails(1l);
        Assert.assertNull("delete Meeting Details is not null",response);
    }
	@Test
    public void testDeleteMeetingDetailsMeetingIdIsNull() throws Exception {
    	ResponseDTO responseDTO = null;
    	Long meetingId = null;
        PowerMockito.when(bookingSystemController.deleteMeetingDetails(1l)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.deleteMeetingDetails(meetingId);
        Assert.assertNull("delete Meeting Details is not null",response);
    }
	@Test
    public void testDeleteMeetingDetailsIsNotNull() throws Exception {
    	ResponseDTO responseDTO = new ResponseDTO();
    	responseDTO.setCode(200);
    	responseDTO.setMsg(default_SuccessMsg);
    	Long meetingId = 1l;
        PowerMockito.when(bookingSystemController.deleteMeetingDetails(meetingId)).thenReturn(responseDTO);
        ResponseDTO response = bookingSystemController.deleteMeetingDetails(meetingId);
        Assert.assertEquals(response.getCode(),200);
    }

}
