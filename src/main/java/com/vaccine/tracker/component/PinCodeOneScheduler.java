package com.vaccine.tracker.component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vaccine.tracker.ToTelegram;

import org.json.*;


@Component
public class PinCodeOneScheduler {
	
	@Value("${pincode1}")
	String pincode;
	
	@Value("${baseUrl}")
	String baseUrl;
	
	static int counter = 0;
	ArrayList<String> centerListUnderFortyFive = new ArrayList<>(); 
	ArrayList<String> centerListOverFortyFive = new ArrayList<>(); 
	
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
			System.out.println("Ping count:"+(++counter)+" "+url+"\n");			
		    String response = restTemplate.exchange(url, HttpMethod.GET, entity , String.class).getBody();
		    parseJson(response);		
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}				
	}

	private void parseJson(String response) {
		String jsonString = response; 
		JSONObject obj = new JSONObject(jsonString);
		JSONArray arr = obj.getJSONArray("sessions");
		for (int i = 0; i < arr.length(); i++)
		{
		    try {			   
			    String name = (String) arr.getJSONObject(i).get("name");
			    String feeType = (String) arr.getJSONObject(i).get("fee_type");
			    String date = (String) arr.getJSONObject(i).get("date");
				Object availableCapacity = arr.getJSONObject(i).get("available_capacity");
				String vaccine = (String) arr.getJSONObject(i).get("vaccine");
				Object minAgeLimit = arr.getJSONObject(i).get("min_age_limit");	
				Object dose1 = arr.getJSONObject(i).get("available_capacity_dose1");	
				Object dose2 = arr.getJSONObject(i).get("available_capacity_dose2");				
				String msg = "Slots are now open for Pincode: "+pincode+": \n\nBooking Date: "+date+"\nCentre: "+name+"\n\nCapacity: "+availableCapacity+"\nAvailable capacity for dose 1: "+dose1+"\nAvailable capacity for dose 2: "+dose2+"\n\nVaccine: "+vaccine+"\nMin Age Limit: "+minAgeLimit+"\n\nType: "+feeType+"\n\n Link: https://selfregistration.cowin.gov.in/";
				int ageLimit = Integer.parseInt(minAgeLimit+"");
				if(!centerListUnderFortyFive.contains(name) && ageLimit<45) {
					ToTelegram.send(msg);
				    System.out.println("--------------------------------");
				    System.out.println(msg);
				    System.out.println("--------------------------------");
				    centerListUnderFortyFive.add(name);				   			    
				}
				if(!centerListOverFortyFive.contains(name) && ageLimit>=45) {
					ToTelegram.send(msg);				  
					System.out.println("--------------------------------");
				    System.out.println(msg);
				    System.out.println("--------------------------------");
				    centerListOverFortyFive.add(name);				   	    
				 }				
			}
			catch (JSONException | IOException e ) {				
				System.out.println("Error: "+e.getLocalizedMessage());
			}
		}
		
	}
	
	@Scheduled(fixedDelay = 300000, initialDelay = 3000)
	public void printCurrentSlots() {
		System.out.println("Under 45 for pincode: "+pincode+": "+centerListUnderFortyFive);
		System.out.println("Above 45 for pincode: "+pincode+": "+centerListOverFortyFive);
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
}
