package com.poc.bookingSystem.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.deps.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.EndOfDataDecoderException;
import com.poc.bookingSystem.DTO.BookingRequestDTO;
import com.poc.bookingSystem.DTO.BookingResponseDTO;
import com.poc.bookingSystem.DTO.MeetingResponseDTO;
import com.poc.bookingSystem.DTO.ResponseDTO;
import com.poc.bookingSystem.entity.MeetingDetails;
import com.poc.bookingSystem.entity.RoomDetails;
import com.poc.bookingSystem.entity.UserInfo;
import com.poc.bookingSystem.inf.IBookingSystemService;
import com.poc.bookingSystem.repo.IMeetingDetailsRepo;
import com.poc.bookingSystem.repo.IRoomDetailsRepo;
import com.poc.bookingSystem.repo.IUserInfoRepo;
import com.poc.bookingSystem.util.BookingSystemException;
import com.poc.bookingSystem.util.Utils;

import scala.annotation.meta.setter;

@Service
public class BookingSystemServiceImpl implements IBookingSystemService{
    @Autowired
    IRoomDetailsRepo iRoomDetails;
    
    @Autowired
    IMeetingDetailsRepo iMeetingDetailsRepo;
    
    @Autowired
    IUserInfoRepo iUserInfoRepo;

	 
    
	@Override
	public ResponseDTO getAllRooms() {
		ResponseDTO responseDTO = new ResponseDTO();
		List<RoomDetails> responses =  iRoomDetails.fetchAll();
		if(Objects.isNull(responses)){
			responseDTO.setCode(200);
			responseDTO.setMsg("Error: No Data Exists");
		}else{
			responseDTO.setResponse(responses);
			responseDTO.setCode(200);
			responseDTO.setMsg("Success");
		}
	
		return responseDTO;
	}
	@Override
	public ResponseDTO fetchMeetingsById(Long roomId) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<MeetingResponseDTO> meetingResponseDTOs = new ArrayList<MeetingResponseDTO>();
		try{
		List<MeetingDetails> meetingsInfo =  iMeetingDetailsRepo.getMeetingDetailsByRoomDetailsRoomId(roomId);
	    if(Objects.isNull(meetingsInfo)||meetingsInfo.size()== 0){
	    	responseDTO.setError("No meeting Data available for room Id:"+roomId);
	    	
	    }else{
	    	responseDTO.setCode(200);
	    	responseDTO.setMsg("Success");
	    	for (MeetingDetails meetingDataObj : meetingsInfo) {
	    		MeetingResponseDTO meetingResponseDTO =  new MeetingResponseDTO();
	    		meetingResponseDTO.setMeetingID(meetingDataObj.getMeetingID());
	    		meetingResponseDTO.setRoomId(roomId);
	    		meetingResponseDTO.setMeetingTitle(meetingDataObj.getMeetingTitle());
	    		meetingResponseDTO.setBookingStatus(meetingDataObj.getBookingStatus());
	    		meetingResponseDTO.setUserName(meetingDataObj.getUserInfo().getUserName());
	    		meetingResponseDTO.setStartTime(Utils.convertDateToString(meetingDataObj.getStartTime()));
	    		meetingResponseDTO.setEndTime(Utils.convertDateToString(meetingDataObj.getEndTime()));
	    		meetingResponseDTOs.add(meetingResponseDTO);
			}
	    	responseDTO.setResponse(meetingResponseDTOs);
	    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseDTO;
	}
	@Override
	public ResponseDTO saveMeetingDetails(BookingRequestDTO bookingRequestDTO) throws BookingSystemException, ParseException {
		ResponseDTO responseDTO = new ResponseDTO();
		
		try{
		MeetingDetails meetingDetailObj = null;
		Date startDate = Utils.convertStringToDate(bookingRequestDTO.getStartTime());
	    Date endDate = Utils.convertStringToDate(bookingRequestDTO.getEndTime());
	    RoomDetails roomDetailObj = iRoomDetails.findOne(bookingRequestDTO.getRoomId());
		UserInfo userInfo = iUserInfoRepo.getUserInfoByUserName(bookingRequestDTO.getUserName());
	   if(bookingRequestDTO.getMeetingId()!= null) {
		   meetingDetailObj = iMeetingDetailsRepo.findOne(bookingRequestDTO.getMeetingId());
		   if(Objects.nonNull(meetingDetailObj)){
			   meetingDetailObj.setStartTime(startDate);
			   meetingDetailObj.setEndTime(endDate);
			   roomDetailObj.setBookingStatus(bookingRequestDTO.getBookingStatus());
			   meetingDetailObj.setBookingStatus(bookingRequestDTO.getBookingStatus());
			   meetingDetailObj.setRoomDetails(roomDetailObj);
			   responseDTO.setMsg("Data Updated");
		   }else{
		    meetingDetailObj = new MeetingDetails();
			meetingDetailObj.setMeetingTitle(bookingRequestDTO.getMeetingTitle());
			meetingDetailObj.setStartTime(startDate);
			meetingDetailObj.setEndTime(endDate);
			roomDetailObj.setBookingStatus(bookingRequestDTO.getBookingStatus());
			meetingDetailObj.setRoomDetails(roomDetailObj);
			meetingDetailObj.setBookingStatus(bookingRequestDTO.getBookingStatus());
			meetingDetailObj.setUserInfo(userInfo);
			responseDTO.setMsg("Data Saved Successfully");
	   }
	    MeetingDetails repoResult = iMeetingDetailsRepo.saveAndFlush(meetingDetailObj);
		BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
		bookingResponseDTO.setMeetingId(repoResult.getMeetingID());
		responseDTO.setResponse(bookingResponseDTO);
		}}catch(Exception e){
			responseDTO.setError(e.getMessage());
		}
		return responseDTO;
	}
	@Override
	public ResponseDTO deleteMeetingDetails(Long meetingId) {
		ResponseDTO responseDTO = new ResponseDTO();
		MeetingDetails meetingDetailObj= iMeetingDetailsRepo.findOne(meetingId);
		iMeetingDetailsRepo.delete(meetingDetailObj);
		responseDTO.setMsg("Deleted Successfully");
		return responseDTO;
	}
	

}
