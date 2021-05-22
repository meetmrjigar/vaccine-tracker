package com.vaccine.tracker.util;

import java.io.IOException; 

import org.springframework.web.client.RestTemplate;

public class ToTelegram {
	
	static RestTemplate restTemplate = new RestTemplate();
			
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		ToTelegram.restTemplate = restTemplate;
	}

	public static String send(String msg) throws IOException {
		String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
		
		// Replace the value with your telegram bot API token.
		String apiToken = "Your Bot's Api Token here"; 
		
		// Replace the value with your telegram channel Id [t.me/ChannelId]. Channel should have your bot as admin and should be a public channel. Add '@' as prefix. eg. @YourChannelId
		String chatId = "@Your Public Channel's Id here"; 
		urlString = String.format(urlString, apiToken, chatId, msg);
		
		// Sends the message string to the telegram channel via the bot
		return restTemplate.getForObject(urlString, String.class);
	}
}
