package com.vaccine.tracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vaccine.tracker.dto.StatusCheckResponse;
import com.vaccine.tracker.util.CentreUtil;

@RestController
public class TrackerController {

private static final Logger logger = LoggerFactory.getLogger(TrackerController.class);
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@GetMapping(path ="/check-current-tracking", produces = MediaType.APPLICATION_JSON_VALUE)
	public StatusCheckResponse checkAvailability() {
		logger.info("New check-current-tracking GET request");
		return new StatusCheckResponse(CentreUtil.getPincodeOne18Plus(),
						CentreUtil.getPincodeOne45Plus(), 
						CentreUtil.getPincodeTwo18Plus(), 
						CentreUtil.getPincodeTwo45Plus());
	}
}
