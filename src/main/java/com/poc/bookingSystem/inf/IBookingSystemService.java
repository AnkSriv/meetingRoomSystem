package com.poc.bookingSystem.inf;

import java.text.ParseException;

import com.poc.bookingSystem.DTO.BookingRequestDTO;
import com.poc.bookingSystem.DTO.ResponseDTO;
import com.poc.bookingSystem.util.BookingSystemException;

public interface IBookingSystemService {

	ResponseDTO getAllRooms();

	ResponseDTO fetchMeetingsById(Long roomId) throws BookingSystemException;

	ResponseDTO saveMeetingDetails(BookingRequestDTO bookingRequestDTO) throws BookingSystemException, ParseException;

	

	ResponseDTO deleteMeetingDetails(Long meetingId);

}
