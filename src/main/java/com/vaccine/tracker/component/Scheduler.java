package com.vaccine.tracker.component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vaccine.tracker.dto.CowinResponse;
import com.vaccine.tracker.dto.CowinResponseCentersContent;
import com.vaccine.tracker.dto.Session;
import com.vaccine.tracker.dto.VaccineFees;
import com.vaccine.tracker.service.CowinResponseProcessor;
import com.vaccine.tracker.service.SaveRetrieve;
import com.vaccine.tracker.util.CentreUtil_BK;
import com.vaccine.tracker.util.ToTelegram_BK;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Scheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Value("${baseUrl}")
	String baseUrl;
	
	@Value("${baseUrl2}")
	String baseUrl2;
	
	@Autowired
	CowinResponseProcessor cowinResponseProcessor;
	
	@Autowired
	SaveRetrieve saveRetrieve;
	
	List<Integer> pincodeList = new ArrayList<>();
	
	@Value("${pincodes}")
	String pincodes;
	
	static int counter = 0;	
	static int counter86 = 0;	
	static int counter77 = 0;	
	
	
	HttpHeaders headers = new HttpHeaders();
	HttpEntity <String> entity;
	DateTimeFormatter dtf;
	String url;
	String url86;
	String url77;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@PostConstruct
	public void setList() {
		List<String> pinStrArr = Arrays.asList(pincodes.split(","));
		for(String pin : pinStrArr) {
			this.pincodeList.add(Integer.parseInt(pin));
		}
		headers.set("User-Agent", "PostmanRuntime/7.26.1");
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    entity = new HttpEntity<String>(headers);
	    dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	    url = baseUrl+"&date="+dtf.format(LocalDateTime.now());
	    url86 = baseUrl2+"pincode=400086&date="+dtf.format(LocalDateTime.now());	
	    url77 = baseUrl2+"pincode=400077&date="+dtf.format(LocalDateTime.now());
	    if(saveRetrieve.retrieve()) {
	    	System.out.println("Loaded previous saved status");
	    }else {
	    	System.out.println("Previous status not found, hence not loaded");
	    }
	}
	
	@Scheduled(fixedRate = 45000, initialDelay = 10000)
	public void fixedDelaySch() {	    
		try {
			System.out.println(LocalDateTime.now()+"\t\t"+" Ping Mumbai : \t"+(++counter)+"\n");
			CowinResponse response = restTemplate.exchange(url, HttpMethod.GET, entity , CowinResponse.class).getBody();
		    if(response.getCenters().size()>0) {
			    processResponse(response);				   
		    }	      
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
		}				
	}
	
	@Scheduled(fixedRate = 10000, initialDelay = 3000)
	public void fixedDelaySch86() {	    
		try {
			System.out.println(LocalDateTime.now()+"\t\t"+" Ping 86 : \t"+(++counter86)+"\n");
			CowinResponse response = restTemplate.exchange(url86, HttpMethod.GET, entity , CowinResponse.class).getBody();
		    if(response.getCenters().size()>0) {
			    processResponse86(response);				   
		    }	      
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
		}				
	}
	
	@Scheduled(fixedRate = 10000, initialDelay = 7000)
	public void fixedDelaySch77() {	    
		try {
			System.out.println(LocalDateTime.now()+"\t\t"+" Ping 77 : \t"+(++counter77)+"\n");
			CowinResponse response = restTemplate.exchange(url77, HttpMethod.GET, entity , CowinResponse.class).getBody();
		    if(response.getCenters().size()>0) {
			    processResponse77(response);				   
		    }	      
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
		}				
	}

	
	private void processResponse(CowinResponse response) {			
		response.getCenters().stream().forEach(cowinResponseCentersContent -> {
			if(pincodeList.contains(cowinResponseCentersContent.getPincode())
					&& cowinResponseCentersContent.getSessions().size()>0) {
				try {
					cowinResponseProcessor.processFurther(cowinResponseCentersContent);
				} catch (IOException e) {
					logger.error(e.getLocalizedMessage());
				}				
			}
		});		
	}
	
	private void processResponse86(CowinResponse response) {			
		response.getCenters().stream().forEach(cowinResponseCentersContent -> {
			if(cowinResponseCentersContent.getSessions().size()>0) {
				try {
					cowinResponseProcessor.processFurther86(cowinResponseCentersContent);
				} catch (IOException e) {
					logger.error(e.getLocalizedMessage());
				}				
			}
		});		
	}
	
	private void processResponse77(CowinResponse response) {			
		response.getCenters().stream().forEach(cowinResponseCentersContent -> {
			if(cowinResponseCentersContent.getSessions().size()>0) {
				try {
					cowinResponseProcessor.processFurther77(cowinResponseCentersContent);
				} catch (IOException e) {
					logger.error(e.getLocalizedMessage());
				}				
			}
		});		
	}
	
	@SuppressWarnings("unused")
	private void processFurther(CowinResponseCentersContent cowinResponseCentersContent) throws IOException{
		cowinResponseCentersContent.getSessions().stream().forEach(session -> {
			if(session.getAvailable_capacity()>0) {
				Boolean send = false;
				int cost = 0; StringBuilder msg = new StringBuilder("Slots are now open for Pincode: ");
				String key = session.getSession_id();
				if(session.getMin_age_limit()<45 && CentreUtil_BK.updateBelowFortyFive(key)) {	
					send = true;
				}
				else if(session.getMin_age_limit()>=45 && CentreUtil_BK.updateFortyFivePlus(key)) {					
					send= true;										
				}
			if(send) {
				try {
					checkForCost(cowinResponseCentersContent, session, cost, msg);
					ToTelegram_BK.send(msg.toString());
					System.out.println(msg);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage());
				}
			}
				
			}else {
				clearAndRenewCurrentSessionTracking(cowinResponseCentersContent,session);
			}
		});
		
	}

	private void checkForCost(CowinResponseCentersContent cowinResponseCentersContent, Session session, int cost, StringBuilder msg) {		
		msg.append(cowinResponseCentersContent.getPincode()).append("\n\nBooking Date: ").append(session.getDate()).append("\nCentre: ").append(cowinResponseCentersContent.getName())
		.append("\n\nCapacity: ").append(session.getAvailable_capacity()).append("\nAvailable capacity for dose 1: ").append(session.getAvailable_capacity_dose1())
		.append("\nAvailable capacity for dose 2: ").append(session.getAvailable_capacity_dose2()).append("\n\nVaccine: ").append(session.getVaccine())
		.append("\nMin Age Limit: ").append(session.getMin_age_limit()).append("\n\nType: ").append(cowinResponseCentersContent.getFee_type());
		if(cowinResponseCentersContent.getFee_type().equalsIgnoreCase("Paid")) {			
			for(VaccineFees s : cowinResponseCentersContent.getVaccine_fees()) {
				if(s.getVaccine().equals(session.getVaccine())) {
					cost = s.getFee();
					msg.append("\nCost: Rs. ").append(cost);
				}
			}					
		}
		msg.append("\n\nLink: https://selfregistration.cowin.gov.in/");			
	}

	
	private void clearAndRenewCurrentSessionTracking(CowinResponseCentersContent cowinResponseCentersContent, Session session) {
		String key = session.getSession_id();
		if(session.getMin_age_limit()<45) {
			CentreUtil_BK.getCentersBelowFortyFive().remove(key);
		}else {
			CentreUtil_BK.getCentersFortyFivePlus().remove(key);
		}
		
	}	
	
}
