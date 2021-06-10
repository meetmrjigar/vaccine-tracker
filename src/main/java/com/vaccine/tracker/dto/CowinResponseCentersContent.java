package com.vaccine.tracker.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CowinResponseCentersContent {

	private int center_id;
	private String name;
	private String address;
	private String state_name;
	private String district_name;
	private String block_name;
	private int pincode;
	private int lat;
	private int longg;
	private String from;
	private String to;
	private String fee_type;
	private ArrayList<Session> sessions;
	private ArrayList<VaccineFees> vaccine_fees;
}
