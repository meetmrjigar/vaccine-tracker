package com.vaccine.tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vaccine.tracker.dto.CowinResponseCentersContent;
import com.vaccine.tracker.dto.Session;
import com.vaccine.tracker.util.ToTelegram;
import com.vaccine.tracker.util.ToTelegram_BK;

@Service
public class SessionValidator {

	private static final Logger logger = LoggerFactory.getLogger(SessionValidator.class);
	
	@Autowired
	ToTelegram toTelegram;
	
	@Async
	public void checkForSend(CowinResponseCentersContent cowinResponseCentersContent, Session session) {
		try {
			int cost = 0; 
			StringBuilder msg = new StringBuilder("Slots are now open for Pincode :");
			checkForCost(cowinResponseCentersContent, session, cost, msg);
			toTelegram.send(msg);	
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
				
	}

	private void checkForCost(CowinResponseCentersContent cowinResponseCentersContent, Session session, int cost, StringBuilder msg) {		
		msg.append(cowinResponseCentersContent.getPincode()).append("\n\nBooking Date : ").append(session.getDate()).append("\nCentre : ").append(cowinResponseCentersContent.getName())
		.append("\n\nCapacity : ").append(session.getAvailable_capacity()).append(" (Dose-1: ").append(session.getAvailable_capacity_dose1())
		.append(", Dose-2: ").append(session.getAvailable_capacity_dose2()+")").append("\nVaccine : ").append(session.getVaccine());
		if(session.isAllow_all_age()) {
			msg.append("\nOpen for all age groups");
		}else if(!session.isAllow_all_age() && session.getMax_age_limit()<45 && session.getMax_age_limit()>0){
			msg.append("\nAge Group: ").append(session.getMin_age_limit()).append("-").append(session.getMax_age_limit());
		}else {
			msg.append("\nMin Age: ").append(session.getMin_age_limit());
		}
		msg.append("\n\nType : ").append(cowinResponseCentersContent.getFee_type());
		if(cowinResponseCentersContent.getFee_type().equalsIgnoreCase("Paid")) {			
			msg.append("\nCost : ₹ ").append(cowinResponseCentersContent.getVaccine_fees().get(0).getFee());
		}
		msg.append("\n\nLink : https://selfregistration.cowin.gov.in/\n");			
	}	

}
