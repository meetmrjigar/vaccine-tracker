package com.vaccine.tracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vaccine.tracker.dto.StatusCheckResponse;
import com.vaccine.tracker.service.SaveRetrieve;
import com.vaccine.tracker.util.CentreUtil;
import com.vaccine.tracker.util.CentreUtil_BK;

@RestController
public class TrackerController {

private static final Logger logger = LoggerFactory.getLogger(TrackerController.class);
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	SaveRetrieve saveRetrieve;
	
//	@GetMapping(path ="/check-current-tracking", produces = MediaType.APPLICATION_JSON_VALUE)
//	public StatusCheckResponse checkAvailability() {
//		logger.info("New check-current-tracking GET request");
//		return new StatusCheckResponse(CentreUtil_BK.getCentersBelowFortyFive(),
//						CentreUtil_BK.getCentersFortyFivePlus(), CentreUtil_BK.getCentersBelowFortyFive2(), CentreUtil_BK.getCentersFortyFivePlus2());
//	}
	
	@GetMapping(path="/save")
	public ResponseEntity<String> saveCurrentState(){
		if(saveRetrieve.save(CentreUtil.getCenters(), CentreUtil.getCenters86(), CentreUtil.getCenters77())){
			return ResponseEntity.ok("Successfully saved");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured while saving");
		
	}
}