package com.vaccine.tracker.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import com.vaccine.tracker.dto.CowinResponseCentersContent;
import com.vaccine.tracker.util.CentreUtil;
import com.vaccine.tracker.util.CentreUtil_BK;

@Service
public class CowinResponseProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(CowinResponseProcessor.class);
	
	@Autowired
	SessionValidator sessionValidator;
	
	@Async
	public void processFurther(CowinResponseCentersContent cowinResponseCentersContent) throws IOException{
		cowinResponseCentersContent.getSessions().stream().forEach(session -> {
			if(session.getAvailable_capacity()>0) {
//				if(session.getMin_age_limit()<45 && CentreUtil.updateBelowFortyFive(session.getSession_id(),thread)) {	
//					send = true;
//				}
//				else if(session.getMin_age_limit()>=45 && CentreUtil.updateFortyFivePlus(session.getSession_id(), thread)) {					
//					send= true;										
//				}		
				if(CentreUtil.updateCenters(session.getSession_id())) {
					sessionValidator.checkForSend(cowinResponseCentersContent,session);
				}
			}	else {
				CentreUtil.removeCenter(session.getSession_id());
			}
		});		
	}
	
	@Async
	public void processFurther86(CowinResponseCentersContent cowinResponseCentersContent) throws IOException{
		logger.info("cowinResponseCentersContent 86 :"+cowinResponseCentersContent.getName()+"\t\t"+cowinResponseCentersContent.getSessions().get(0).getAvailable_capacity()+"\n\n");
		cowinResponseCentersContent.getSessions().stream().forEach(session -> {
			if(session.getAvailable_capacity()>0) {
//				if(session.getMin_age_limit()<45 && CentreUtil.updateBelowFortyFive(session.getSession_id(),thread)) {	
//					send = true;
//				}
//				else if(session.getMin_age_limit()>=45 && CentreUtil.updateFortyFivePlus(session.getSession_id(), thread)) {					
//					send= true;										
//				}		
				if(CentreUtil.updateCenters86(session.getSession_id())) {
					sessionValidator.checkForSend(cowinResponseCentersContent,session);
				}
			}	else {
				CentreUtil.removeCenter86(session.getSession_id());
			}
		});		
	}
	
	@Async
	public void processFurther77(CowinResponseCentersContent cowinResponseCentersContent) throws IOException{
		logger.info("cowinResponseCentersContent 77 :"+cowinResponseCentersContent.getName()+"\t\t"+cowinResponseCentersContent.getSessions().get(0).getAvailable_capacity()+"\n\n");		
		cowinResponseCentersContent.getSessions().stream().forEach(session -> {
			if(session.getAvailable_capacity()>0) {
//				if(session.getMin_age_limit()<45 && CentreUtil.updateBelowFortyFive(session.getSession_id(),thread)) {	
//					send = true;
//				}
//				else if(session.getMin_age_limit()>=45 && CentreUtil.updateFortyFivePlus(session.getSession_id(), thread)) {					
//					send= true;										
//				}		
				if(CentreUtil.updateCenters77(session.getSession_id())) {
					sessionValidator.checkForSend(cowinResponseCentersContent,session);
				}
			}	else {
				CentreUtil.removeCenter77(session.getSession_id());
			}
		});		
	}
	
}
