package com.vaccine.tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Session {

	private String session_id;
	private String date;
	private int available_capacity;
	private int min_age_limit;
	private int max_age_limit;
	private boolean allow_all_age;
	private String vaccine;
	private int available_capacity_dose1;
	private int available_capacity_dose2;
}
