package com.vaccine.tracker.dto;

import java.util.LinkedHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusCheckResponse {

	private LinkedHashMap<String, String> pincodeOne18Plus;
	private LinkedHashMap<String, String> pincodeOne45Plus;
	private LinkedHashMap<String, String> pincodeTwo18Plus;
	private LinkedHashMap<String, String> pincodeTwo45Plus;	
}
