package com.olx.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

@Data
public class Advertisement {
	private int id;
	private String title;
	private int category;
	private String description;
	private double price;
	private String username;
	private  LocalDate  createdDate;
	private  LocalDate modifiedDate;
	private int statusId;
	private String statusText;
	private String categoryText;
	
	
	
}
