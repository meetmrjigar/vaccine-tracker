package com.vaccine.tracker.component;

import java.io.IOException;  
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vaccine.tracker.dto.CowinResponse;
import com.vaccine.tracker.dto.CowinResponseSessionContent;
import com.vaccine.tracker.util.CentreUtil;
import com.vaccine.tracker.util.ToTelegram;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Scheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Value("${pincode1}")
	String pincode;
	
	@Value("${baseUrl}")
	String baseUrl;
	
	@Value("${msg}")
	String baseMsg;
	
	static int counter = 0;	
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Scheduled(fixedDelay = 6000, initialDelay = 3000)
	public void fixedDelaySch() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "PostmanRuntime/7.26.1");
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	        LocalDateTime now = LocalDateTime.now();	        
	        if(now.getHour()>10) {
	        	now=now.plusDays(1);
	        }        
	        String url = baseUrl+"pincode="+pincode+"&date="+dtf.format(now);
			logger.info("Ping count:"+(++counter)+" "+url+"\n");
		    CowinResponse response = restTemplate.exchange(url, HttpMethod.GET, entity , CowinResponse.class).getBody();
		    if(response.getSessions().size()>0) {
			    processResponse(response);	    	
		    }		    
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
		}				
	}

	private void processResponse(CowinResponse response) {
		response.getSessions().forEach(cowinResponseSessionContent -> {
			if(cowinResponseSessionContent!=null) {
				if(cowinResponseSessionContent.getAvailable_capacity()>0) {
					try {
						processFurther(cowinResponseSessionContent);
					} catch (IOException e) {
						logger.error(e.getLocalizedMessage());
					}
				}
			}
		});
		
	}

	private void processFurther(CowinResponseSessionContent cowinResponseSessionContent) throws IOException{
		//String msg = "Slots are now open for Pincode: "+cowinResponseSessionContent.getPincode()+": \n\nBooking Date: "+cowinResponseSessionContent.getDate()+"\nCentre: "+cowinResponseSessionContent.getName()+"\n\nCapacity: "+cowinResponseSessionContent.getAvailable_capacity()+"\nAvailable capacity for dose 1: "+cowinResponseSessionContent.getAvailable_capacity_dose1()+"\nAvailable capacity for dose 2: "+cowinResponseSessionContent.getAvailable_capacity_dose2()+"\n\nVaccine: "+cowinResponseSessionContent.getVaccine()+"\nMin Age Limit: "+cowinResponseSessionContent.getMin_age_limit()+"\n\nType: "+cowinResponseSessionContent.getFee_type()+"\n\nLink: https://selfregistration.cowin.gov.in/";
		String msg = String.format(baseMsg, cowinResponseSessionContent.getPincode(),cowinResponseSessionContent.getDate(),cowinResponseSessionContent.getName(),cowinResponseSessionContent.getAvailable_capacity(),cowinResponseSessionContent.getAvailable_capacity_dose1(),cowinResponseSessionContent.getAvailable_capacity_dose2(),cowinResponseSessionContent.getVaccine(),cowinResponseSessionContent.getMin_age_limit(),cowinResponseSessionContent.getFee_type());
		if(cowinResponseSessionContent.getMin_age_limit()<45) {
			if(CentreUtil.updatePinCodeOne18Plus(cowinResponseSessionContent.getName())) {
				ToTelegram.send(msg);
				logger.info(msg);
			}
		}
		if(cowinResponseSessionContent.getMin_age_limit()>=45) {
			if(CentreUtil.updatePinCodeOne45Plus(cowinResponseSessionContent.getName())) {
				ToTelegram.send(msg);
				logger.info(msg);
			}
		}
		
	}	
	
}
