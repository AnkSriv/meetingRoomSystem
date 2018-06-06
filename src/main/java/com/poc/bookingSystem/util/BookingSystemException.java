package com.poc.bookingSystem.util;

public class BookingSystemException extends Exception {
    
	private String message;
	public BookingSystemException(String message) {
		this.message =  message;
	}
	@Override
	public String toString() {
		return message;
	}

}
