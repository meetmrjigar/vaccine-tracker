package com.vaccine.tracker.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vaccine.tracker.dto.StatusCheckResponse;
import com.vaccine.tracker.repository.StatusRepository;
import com.vaccine.tracker.util.CentreUtil;

@Service
public class SaveRetrieve {
	
	@Autowired
	StatusRepository repo;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	
	public boolean save(Set<String> centres, Set<String> centres86, Set<String> centres77) {
		try {
			String now = LocalDateTime.now().format(formatter);
			if(repo.existsById(1)) {
				Optional<StatusCheckResponse> status = repo.findById(1);
				if(status.isPresent()) {
					
					String centers="";
					if(!CentreUtil.getCenters().isEmpty()) {
						for(String center : CentreUtil.getCenters()) {
							centers+=center+",";
						}
						centers= centers.substring(0, centers.length()-1);					
					}status.get().setCenters(centers);
					
					String centers86="";
					if(!CentreUtil.getCenters86().isEmpty()) {
						for(String center : CentreUtil.getCenters86()) {
							centers86+=center+",";
						}
						centers86= centers86.substring(0, centers86.length()-1);
					}
					status.get().setCenters86(centers86);
					
					String centers77="";
					if(!CentreUtil.getCenters77().isEmpty()) {
						for(String center : CentreUtil.getCenters77()) {
							centers77+=center+",";
						}
						centers77= centers77.substring(0, centers77.length()-1);
					}	
					status.get().setCenters77(centers77);
					
					status.get().setNow(now);
					repo.save(status.get());
					
					System.out.println(centers+"\n"+centers86+"\n"+centers77);
					return true;
				}
			}else {				
				StatusCheckResponse status = new StatusCheckResponse();
				String centers="";
				
				if(!CentreUtil.getCenters().isEmpty()) {
					for(String center : CentreUtil.getCenters()) {
						centers+=center+",";
					}
					centers= centers.substring(0, centers.length()-1);					
				}status.setCenters(centers);
				
				
				String centers86="";
				if(!CentreUtil.getCenters86().isEmpty()) {
					for(String center : CentreUtil.getCenters86()) {
						centers86+=center+",";
					}
					centers86= centers86.substring(0, centers86.length()-1);
				}				
				status.setCenters86(centers86);
				
				String centers77="";
				if(!CentreUtil.getCenters77().isEmpty()) {
					for(String center : CentreUtil.getCenters77()) {
						centers77+=center+",";
					}
					centers77= centers77.substring(0, centers77.length()-1);
				}				
				status.setCenters77(centers77);
				status.setNow(now);
				status.setId(1);
				repo.save(status);
				
				System.out.println(centers+"\n"+centers86+"\n"+centers77);
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("Error saving status : "+e.getLocalizedMessage());
			return false;
		}
		
		return false;
		
	}
	
	public boolean retrieve() {
		try {
			if(repo.existsById(1)) {
				Optional<StatusCheckResponse> status = repo.findById(1);
				if(status.isPresent()) {
					LocalDateTime saved = LocalDateTime.parse(status.get().getNow(), formatter);
					if(saved.getDayOfYear()==LocalDateTime.now().getDayOfYear()) {
						
						List<String> centers=  Arrays.asList(status.get().getCenters().split(","));
						System.out.println("Loaded Centers: "+centers);
						System.out.println(centers);
						CentreUtil.getCenters().addAll(centers);
						System.out.println(CentreUtil.getCenters());
						
						
						List<String> centers86= Arrays.asList(status.get().getCenters86().split(","));
						System.out.println("Loaded Centers86: "+centers86);
						CentreUtil.getCenters86().addAll(centers86);
						System.out.println(centers86);
						System.out.println(CentreUtil.getCenters86());

						
						List<String> centers77= Arrays.asList(status.get().getCenters77().split(","));
						System.out.println("Loaded Centers77: "+centers77);
						CentreUtil.getCenters77().addAll(centers77);
			
						System.out.println(centers77);
						System.out.println(CentreUtil.getCenters77());

						return true;
					}
					else {
						return false;
					}
				}
			}else {
				return false;
			}
		}
		catch(Exception e) {
			System.out.println("Error while retriving stored status : "+e.getLocalizedMessage());
			return false;
		}
		return true;
	}
}
