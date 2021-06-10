package com.vaccine.tracker.util;

import java.util.LinkedHashMap;

public class CentreUtil {

	static LinkedHashMap<String,String> pincodeOne45Plus = new LinkedHashMap<>();
	static LinkedHashMap<String,String> pincodeOne18Plus = new LinkedHashMap<>();
	static LinkedHashMap<String,String> pincodeTwo45Plus = new LinkedHashMap<>();
	static LinkedHashMap<String,String> pincodeTwo18Plus = new LinkedHashMap<>();
			
	public static LinkedHashMap<String, String> getPincodeOne45Plus() {
		return pincodeOne45Plus;
	}

	public static LinkedHashMap<String, String> getPincodeOne18Plus() {
		return pincodeOne18Plus;
	}

	public static LinkedHashMap<String, String> getPincodeTwo45Plus() {
		return pincodeTwo45Plus;
	}

	public static LinkedHashMap<String, String> getPincodeTwo18Plus() {
		return pincodeTwo18Plus;
	}

	public static boolean updatePinCodeOne45Plus(String key, String center) {
		if(pincodeOne45Plus.containsKey(key)) {
			return false;
		}
		return pincodeOne45Plus.put(key, center) == null;
	}
	
	public static boolean updatePinCodeOne18Plus(String key, String center) {
		if(pincodeOne18Plus.containsKey(key)) {
			return false;
		}
		return pincodeOne18Plus.put(key, center) == null;
	}
	
	public static boolean updatePinCodeTwo45Plus(String key, String center) {
		if(pincodeTwo45Plus.containsKey(key)) {
			return false;
		}
		return pincodeTwo45Plus.put(key, center) == null;
	}
	
	public static boolean updatePinCodeTwo18Plus(String key, String center) {
		if(pincodeTwo18Plus.containsKey(key)) {
			return false;
		}
		return pincodeTwo18Plus.put(key, center) == null;
	}
	
	public static void clearAllCentersPincodeOne(){
		pincodeOne18Plus.clear();
		pincodeOne45Plus.clear();		
	}
	
	public static void clearAllCentersPincodeTwo(){		
		pincodeTwo18Plus.clear();
		pincodeTwo45Plus.clear();
	}
	
}
