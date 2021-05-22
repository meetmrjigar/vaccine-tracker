package com.vaccine.tracker.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusCheckResponse {

	private ArrayList<String> pincodeOne18Plus;
	private ArrayList<String> pincodeOne45Plus;
	private ArrayList<String> pincodeTwo18Plus;
	private ArrayList<String> pincodeTwo45Plus;	
}
