package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidAdvertisementIDException extends RuntimeException{
	
	private String msg;

	public InvalidAdvertisementIDException(String msg) {
		super();
		this.msg = msg;
	}
	
	public InvalidAdvertisementIDException() {}

	@Override
	public String toString() {
		return "Invalid AdvertisementID: "+this.msg;
	}
	
	
	


}
