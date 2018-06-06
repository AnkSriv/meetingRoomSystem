package com.poc.bookingSystem;

import java.text.ParseException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couchbase.client.java.query.Delete;
import com.poc.bookingSystem.DTO.BookingRequestDTO;
import com.poc.bookingSystem.DTO.ResponseDTO;
import com.poc.bookingSystem.inf.IBookingSystemService;
import com.poc.bookingSystem.util.BookingSystemException;

@RestController
public class BookingSystemController {
	
	@Autowired
	IBookingSystemService iBookingSystemService;
	/* fetch all the rooms details present in db*/
	@RequestMapping(value="/rooms",method = RequestMethod.GET) 
	 public ResponseDTO getAllRooms() {
	        return iBookingSystemService.getAllRooms();
	    }
	/* fetch all the rooms details based on room id present in db*/
	@RequestMapping(value="/rooms/{roomId}",method = RequestMethod.GET) 
	 public ResponseDTO fetchMeetingsById(@PathVariable("roomId") Long roomId) throws BookingSystemException {
		ResponseDTO responseDTO = null;
		if(Objects.nonNull(roomId)){
			responseDTO = iBookingSystemService.fetchMeetingsById(roomId);
		}
		 return responseDTO;
	    }
	/* save and update the booking
	 * for save meetingId should */
	@RequestMapping(value="/book", method=RequestMethod.POST)
	 public ResponseDTO saveMeetingDetails(@RequestBody BookingRequestDTO bookingRequestDTO) throws BookingSystemException, ParseException {
		ResponseDTO responseDTO = null;
		if(Objects.nonNull(bookingRequestDTO)){
			responseDTO = iBookingSystemService.saveMeetingDetails(bookingRequestDTO);
		}else{
			responseDTO = new ResponseDTO();
			responseDTO.setError("request body is empty");
		}
		 return responseDTO;
	    }

	
	@RequestMapping(value="/deleteBooking", method=RequestMethod.DELETE)
	 public ResponseDTO deleteMeetingDetails(@RequestParam Long meetingId) throws BookingSystemException {
		ResponseDTO responseDTO = null;
		if(Objects.nonNull(meetingId)){
			responseDTO = iBookingSystemService.deleteMeetingDetails(meetingId);
		}
		
	        return responseDTO;
	    }
	
}

