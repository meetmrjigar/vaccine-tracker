package com.vaccine.tracker.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CowinResponseSessionContent  {
	private int center_id;
	private String name;
	private String address;
	private String state_name;
	private String district_name;
	private String block_name;
	private int pincode;
	private String from;
	private String to;
	private int lat;
	private int longg;
	private String fee_type;
	private String session_id;
	private String date;
	private int available_capacity_dose1;
	private int available_capacity_dose2;
	private int available_capacity;
	private int fee;
	private int min_age_limit;
	private String vaccine;
	private ArrayList<String> slots;	
}