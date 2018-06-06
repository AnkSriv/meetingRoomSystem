package com.poc.bookingSystem.DTO;

import java.util.List;

import org.json.JSONObject;

public class ResponseDTO {
 
	private int code;
	private String msg;
	private String error;
	private Object response;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	
}
