package com.olx.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
@Entity
@Table(name="advertises")
public class AdvertisementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private int category;
	private String description;
	private double price;
	private String username;
	private String statusText;
	@Column(name="created_date")
	@JsonFormat(pattern="yyyy-MM-dd",shape= Shape.STRING)
	private  LocalDate  createdDate;
	@JsonFormat(pattern="yyyy-MM-dd",shape= Shape.STRING)
	@Column(name="modified_date")
	private LocalDate modifiedDate;
	@Column(name="Status")
	private int statusId;
	private String categoryText;
	

}
