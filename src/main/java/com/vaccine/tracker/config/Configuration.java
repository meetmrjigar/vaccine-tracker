package com.vaccine.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.vaccine.tracker.component.TerminateBean;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public TerminateBean getTerminateBean() {
		return new TerminateBean();
	}
}


